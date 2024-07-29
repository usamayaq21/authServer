package com.test.authenticator.service;

import com.test.authenticator.config.BlogsConfigs;
import com.test.authenticator.controller.dto.BlogCommentsResponse;
import com.test.authenticator.controller.dto.BlogPostsResponse;
import com.test.authenticator.controller.dto.BlogUserResponse;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@AllArgsConstructor
public class BlogService {
//  @Value("")

  private RestTemplate restTemplate;
  private BlogsConfigs blogsConfigs;

  public List<BlogUserResponse> getAllUsers() {
    log.info("URL => ", blogsConfigs.getUsersUrl());
    ResponseEntity<BlogUserResponse[]> blogUserResponses = restTemplate.getForEntity(
        blogsConfigs.getUsersUrl(), BlogUserResponse[].class);
    List<BlogUserResponse> responseList = Arrays.asList(blogUserResponses.getBody());
    log.info("Response for blogsUsers => ", responseList);
    return responseList;
  }

  public List<BlogCommentsResponse> getAllComments() {
    ResponseEntity<BlogCommentsResponse[]> blogCommentsResponses = restTemplate.getForEntity(
        blogsConfigs.getCommentsUrl(), BlogCommentsResponse[].class);
    List<BlogCommentsResponse> responseList = Arrays.asList(blogCommentsResponses.getBody());
    log.info("Response for blogsComments => ", responseList);
    return responseList;
  }

  public List<BlogPostsResponse> getAllPosts() {
    ResponseEntity<BlogPostsResponse[]> blogPostResponses = restTemplate.getForEntity(
        blogsConfigs.getPostUrl(), BlogPostsResponse[].class);
    List<BlogPostsResponse> responseList = Arrays.asList(blogPostResponses.getBody());
    log.info("Response for blogsComments => ", responseList);
    return responseList;
  }
}
