package com.test.authenticator.controller;

import com.test.authenticator.controller.dto.BlogCommentsResponse;
import com.test.authenticator.controller.dto.BlogPostsResponse;
import com.test.authenticator.controller.dto.BlogUserResponse;
import com.test.authenticator.service.BlogService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog")
@AllArgsConstructor
@Slf4j
public class BlogController {

  private BlogService blogService;

  @GetMapping("/allUsers")
  public List<BlogUserResponse> getAllBlogUsers() {
    log.info("[GET] [/blog/allUsers] request received");
    List<BlogUserResponse> response = blogService.getAllUsers();
    log.info("[RESPONSE] [/blog/allUsers] = {}", response);
    return response;
  }

  @GetMapping("/allPosts")
  public List<BlogPostsResponse> getAllBlogPosts() {
    log.info("[GET] [/blog/allPosts] request received");
    List<BlogPostsResponse> response = blogService.getAllPosts();
    log.info("[RESPONSE] [/blog/allUsers] = {}", response);
    return response;
  }

  @GetMapping("/allComments")
  public List<BlogCommentsResponse> getAllBlogComments() {
    log.info("[GET] [/blog/allPosts] request received");
    List<BlogCommentsResponse> response = blogService.getAllComments();
    log.info("[RESPONSE] [/blog/allUsers] = {}", response);
    return response;
  }

}
