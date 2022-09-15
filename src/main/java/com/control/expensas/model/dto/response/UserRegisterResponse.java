package com.control.expensas.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Builder
@AllArgsConstructor
@Getter
@Setter
public class UserRegisterResponse {
    private Long id;
    private String email; //Es el username
    private String token;
}
