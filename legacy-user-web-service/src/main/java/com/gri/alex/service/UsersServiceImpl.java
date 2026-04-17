package com.gri.alex.service;

import com.gri.alex.data.UserEntity;
import com.gri.alex.data.UsersRepository;
import com.gri.alex.response.UserResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

  private final UsersRepository usersRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public UsersServiceImpl(UsersRepository usersRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.usersRepository = usersRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @Override
  public UserResponse getUserDetails(String userName) {
    UserResponse response = new UserResponse();

    UserEntity userEntity = usersRepository.findByEmail(userName);
    if (userEntity == null) {
      return response;
    }

    BeanUtils.copyProperties(userEntity, response);

    return response;
  }

  @Override
  public UserResponse getUserDetails(String userName, String password) {
    UserResponse response = null;
    UserEntity userEntity = usersRepository.findByEmail(userName);

    if (userEntity == null) {
      return response;
    }

    if (bCryptPasswordEncoder.matches(password, userEntity.getEncryptedPassword())) {
      System.out.println("--> password matches!!!");

      response = new UserResponse();
      BeanUtils.copyProperties(userEntity, response);
    }

    return response;
  }

}
