package com.control.expensas.model.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
public class LoginRequest {
    @Email(message = "Must be a valid email.")
    @NotBlank(message = "Email cannot be null or empty.")
    private String email;

    @Size(min = 6, max = 8, message = "Password must be between 6 and 8 characters.")
    @NotBlank(message = "Password cannot be null or empty.")
    private String password;
}
