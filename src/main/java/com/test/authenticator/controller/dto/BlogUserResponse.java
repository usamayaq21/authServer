package com.test.authenticator.controller.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BlogUserResponse {

  private Integer id;
  private String name;
  private String email;
  private String gender;
  private String status;

}
