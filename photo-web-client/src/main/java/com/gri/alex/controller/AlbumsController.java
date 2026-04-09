package com.gri.alex.controller;

import com.gri.alex.dto.AlbumDto;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class AlbumsController {

  private final OAuth2AuthorizedClientService oauth2ClientService;
  private final RestTemplate restTemplate;

  public AlbumsController(OAuth2AuthorizedClientService oauth2ClientService,
                          RestTemplate restTemplate) {
    this.oauth2ClientService = oauth2ClientService;
    this.restTemplate = restTemplate;
  }

  @GetMapping("/albums")
  public String getAlbums(Model model,
                          @AuthenticationPrincipal OidcUser principal) {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;

    OAuth2AuthorizedClient oauthAuthorizedClient =
        oauth2ClientService.loadAuthorizedClient(
            oauthToken.getAuthorizedClientRegistrationId(),
            oauthToken.getName());
    String jwtAccessToken = oauthAuthorizedClient.getAccessToken().getTokenValue();
    System.out.println("-> JWT Access Token: " + jwtAccessToken);

    System.out.println("-> Principal: " + principal);
    OidcIdToken idToken = principal.getIdToken();
    String tokenValue = idToken.getTokenValue();
    System.out.println("-> ID Token: " + tokenValue);


    ResponseEntity<List<AlbumDto>> responseEntity = restTemplate.exchange(
        "http://localhost:8082/albums",
        HttpMethod.GET,
        new HttpEntity<>(new HttpHeaders() {{
          setBearerAuth(jwtAccessToken);
        }}),
        new ParameterizedTypeReference<>() {}
    );

    model.addAttribute("albums", responseEntity.getBody());

    return "albums";
  }
}
