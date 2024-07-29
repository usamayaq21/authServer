package com.test.authenticator.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class BlogPostsResponse {

  private Integer id;
  @JsonProperty("user_id")
  private Integer userId;
  private String title;
  private String body;

}
