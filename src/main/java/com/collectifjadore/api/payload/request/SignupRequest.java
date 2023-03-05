package com.collectifjadore.api.payload.request;


import jakarta.validation.constraints.Email;

import java.util.Set;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SignupRequest {
    private String id;
    @NotBlank
    @Size(max = 200)
    private String username;

    @NotBlank
    @Size(max = 200)
    @Email
    private String email;

    private Set<String> roles;

    @NotBlank
    @Size(max = 200)
    private String password;

    @NotBlank
    @Size(max = 200)
    private String firstName;

    @NotBlank
    @Size(max = 200)
    private String lastName;


}
