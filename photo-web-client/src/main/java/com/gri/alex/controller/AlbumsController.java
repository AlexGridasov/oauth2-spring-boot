package com.gri.alex.controller;

import com.gri.alex.dto.AlbumDto;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlbumsController {

  @GetMapping("/albums")
  public String getAlbums(Model model) {
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
