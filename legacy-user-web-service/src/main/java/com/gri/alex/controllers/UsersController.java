package com.gri.alex.controllers;

import com.gri.alex.response.UserResponse;
import com.gri.alex.response.VerifyPasswordResponse;
import com.gri.alex.service.UsersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {

  private final UsersService usersService;

  public UsersController(UsersService usersService) {
    this.usersService = usersService;
  }

  @GetMapping("/{userName}")
  public UserResponse getUser(@PathVariable String userName) {
    return usersService.getUserDetails(userName);
  }

  @PostMapping("/{userName}/verify-password")
  public VerifyPasswordResponse verifyUserPassword(
      @PathVariable String userName,
      @RequestBody String password) {

    VerifyPasswordResponse verifyResponse = new VerifyPasswordResponse(false);
    UserResponse user = usersService.getUserDetails(userName, password);

    if (user != null) {
      verifyResponse.setResult(true);
    }

    return verifyResponse;
  }

}
