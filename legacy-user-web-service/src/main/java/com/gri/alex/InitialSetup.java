package com.gri.alex;

import com.gri.alex.data.UserEntity;
import com.gri.alex.data.UsersRepository;
import javax.transaction.Transactional;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class InitialSetup {

  private final UsersRepository usersRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  public InitialSetup(UsersRepository usersRepository,
                      BCryptPasswordEncoder passwordEncoder) {
    this.usersRepository = usersRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @EventListener
  @Transactional
  public void onApplicationEvent(ApplicationReadyEvent event) {
    UserEntity user = new UserEntity(
        1L,
        "qswe3mg84mfjtu",
        "alex",
        "developer",
        "alex@test.com",
        passwordEncoder.encode("123qwe"),
        "",
        false);

    usersRepository.save(user);
  }
}
