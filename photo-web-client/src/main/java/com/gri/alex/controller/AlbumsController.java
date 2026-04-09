package com.gri.alex.controller;

import com.gri.alex.dto.AlbumDto;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
public class AlbumsController {

  private final WebClient webClient;

  public AlbumsController(WebClient webClient) {
    this.webClient = webClient;
  }

  @GetMapping("/albums")
  public String getAlbums(Model model) {

    List<AlbumDto> albums = webClient.get()
        .uri("http://localhost:8082/albums")
        .retrieve()
        .bodyToMono(new ParameterizedTypeReference<List<AlbumDto>>(){})
        .block();

    model.addAttribute("albums", albums);

    return "albums";
  }
}
