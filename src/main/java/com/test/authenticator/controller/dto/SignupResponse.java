package com.test.authenticator.controller.dto;

import com.test.authenticator.model.Password;
import com.test.authenticator.model.User;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class SignupResponse {
    private User userInfo;
    private Password passwordInfo;
    private String token;
}
