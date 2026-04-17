package com.gri.alex.service;

import com.gri.alex.response.UserResponse;

public interface UsersService {

  UserResponse getUserDetails(String userName, String password);

  UserResponse getUserDetails(String userName);
}
