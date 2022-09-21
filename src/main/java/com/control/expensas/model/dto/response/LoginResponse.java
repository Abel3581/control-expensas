package com.control.expensas.model.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class LoginResponse {

    private String email;
    private String token;
}
