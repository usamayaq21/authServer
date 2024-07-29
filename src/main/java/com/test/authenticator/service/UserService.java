package com.test.authenticator.service;

import com.test.authenticator.config.JwtUtil;
import com.test.authenticator.controller.dto.*;
import com.test.authenticator.exception.PasswordException;
import com.test.authenticator.model.Password;
import com.test.authenticator.model.User;
import com.test.authenticator.model.repos.PasswordRepo;
import com.test.authenticator.model.repos.UserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

  private final UserRepo userRepo;
  private final PasswordRepo passwordRepo;
  private final JwtUtil jwtUtil;

  public SignupResponse insertUserInfo(SignupRequest request) {
    User user = userRepo.save(buildUser(request));
    Password password = passwordRepo.save(buildPassword(request, user));
    log.info("userId = [] and passworId = [] save successfully", user.getUserId(),
        password.getPasswordId());
    return buildResponse(user, password);
  }

  private SignupResponse buildResponse(User user, Password password) {
    Password responsePassword = new Password();
    responsePassword.setPasswordHash(password.getPasswordHash());
    return SignupResponse.builder().passwordInfo(responsePassword).userInfo(user)
        .token(jwtUtil.generateToken(user)).build();
  }

  private Password buildPassword(SignupRequest request, User user) {
    Password password = new Password();
    password.setPasswordHash(generatePasswordHash(request.getPassword()));
    password.setUserId(user);
    return password;
  }

  private String generatePasswordHash(String password) {
    String pepper = "pepper"; // secret key used by password encoding
    int iterations = 200000;  // number of hash iteration
    int hashWidth = 256;      // hash width in bits

    Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder(pepper, iterations,
        hashWidth);
    pbkdf2PasswordEncoder.setEncodeHashAsBase64(true);
    String encodedPassword = pbkdf2PasswordEncoder.encode(password);
    return encodedPassword;
  }

  private boolean checkPassword(String rawPassword, String encodedPassword) {
    String pepper = "pepper"; // secret key used by password encoding
    int iterations = 200000;  // number of hash iteration
    int hashWidth = 256;      // hash width in bits

    Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder(pepper, iterations,
        hashWidth);
    pbkdf2PasswordEncoder.setEncodeHashAsBase64(true);
    return pbkdf2PasswordEncoder.matches(rawPassword, encodedPassword);
  }

  private User buildUser(SignupRequest request) {
    User user = new User();
    user.setUserFirstName(request.getFirstName());
    user.setUserLastName(request.getLastName());
    user.setUsername(request.getUsername());
    return user;
  }

  public LoginResponse login(LoginRequest request) {
    Optional<User> user = userRepo.findByUsername(request.getUser());
    if (!user.isPresent()) {
      throw new RuntimeException("User not found");
    }
    Optional<Password> password = passwordRepo.findTopByUserId(user.get());
    if (password.isPresent() && !checkPassword(request.getPassword(),
        password.get().getPasswordHash())) {
      throw new PasswordException();
    }
    return LoginResponse.builder().token(jwtUtil.generateToken(user.get())).build();
  }


  public UserResponse getUserInfo(Long userId) {
    Optional<User> user = userRepo.findById(userId);
    if (!user.isPresent()) {
      throw new RuntimeException("User not found");
    }
    return buildUserResponse(user.get());
  }

  private UserResponse buildUserResponse(User user) {
    UserResponse response = new UserResponse();
    response.setFirstName(user.getUserFirstName());
    response.setLastName(user.getUserLastName());
    return response;
  }
}
