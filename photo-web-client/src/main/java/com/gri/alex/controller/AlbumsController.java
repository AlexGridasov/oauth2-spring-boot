package com.gri.alex.controller;

import com.gri.alex.dto.AlbumDto;
import java.util.List;
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

@Controller
public class AlbumsController {

  OAuth2AuthorizedClientService oauth2ClientService;

  public AlbumsController(OAuth2AuthorizedClientService oauth2ClientService) {
    this.oauth2ClientService = oauth2ClientService;
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

    AlbumDto album1 = new AlbumDto();
    album1.setAlbumId("1");
    album1.setAlbumTitle("Album 1");
    album1.setAlbumUrl("http://localhost:8082/albums/1");

    AlbumDto album2 = new AlbumDto();
    album2.setAlbumId("2");
    album2.setAlbumTitle("Album 2");
    album2.setAlbumUrl("http://localhost:8082/albums/2");

    model.addAttribute("albums", List.of(album1, album2));

    return "albums";
  }
}
