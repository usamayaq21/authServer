package com.test.authenticator.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class BlogCommentsResponse {

  private Integer id;
  @JsonProperty("post_id")
  private Integer postId;
  private String name;
  private String email;
  private String body;


}
