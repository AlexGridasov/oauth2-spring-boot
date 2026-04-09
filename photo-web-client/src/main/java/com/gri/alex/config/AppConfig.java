package com.gri.alex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

  @Bean
  public WebClient webClient(ClientRegistrationRepository clientRegistrationrepository,
                             OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository) {

    ServletOAuth2AuthorizedClientExchangeFilterFunction oAuth2ExchangeFunction =
        new ServletOAuth2AuthorizedClientExchangeFilterFunction(
            clientRegistrationrepository,
            oAuth2AuthorizedClientRepository);

    oAuth2ExchangeFunction.setDefaultOAuth2AuthorizedClient(true);

    return WebClient.builder()
        .apply(oAuth2ExchangeFunction.oauth2Configuration())
        .build();
  }
}
