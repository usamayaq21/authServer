package com.test.authenticator.controller.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class SignupRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
