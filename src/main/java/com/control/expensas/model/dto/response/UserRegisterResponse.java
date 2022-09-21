package com.control.expensas.model.dto.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRegisterResponse {
    private Long id;
    private String email; //Es el username
    private String token;
}
