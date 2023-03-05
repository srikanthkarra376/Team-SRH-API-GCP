package com.collectifjadore.api.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPassword {
    private String oldPassword;
    private String newPassword;
    private String username;
}
