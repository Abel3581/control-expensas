package com.control.expensas.model.dto.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
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
