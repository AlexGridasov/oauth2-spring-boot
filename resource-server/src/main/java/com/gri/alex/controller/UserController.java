package com.gri.alex.controller;

import com.gri.alex.response.UserDto;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UserController {

  @GetMapping("/status/check")
  public String status() {
    return "Working ...";
  }

  @PreAuthorize("hasAuthority('ROLE_developer') or #id == #jwt.subject")
  // @Secured("ROLE_developer")
  @DeleteMapping(path = "/{id}")
  public String deleteUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
    return String.format("Deleted user with id `%s` and JWT subject `%s`", id, jwt.getSubject());
  }

  @PostAuthorize("returnObject.userId == #jwt.subject")
  @GetMapping(path = "/{id}")
  public UserDto getUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
    return new UserDto(jwt.getClaim("given_name"), jwt.getClaim("family_name"), jwt.getSubject());
  }
}
