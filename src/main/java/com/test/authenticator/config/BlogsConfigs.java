package com.test.authenticator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties(prefix = "blog")
@Configuration
public class BlogsConfigs {

  private String usersUrl;
  private String postUrl;
  private String commentsUrl;

}
