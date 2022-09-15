package com.control.expensas.mapper;

import com.control.expensas.model.User;
import com.control.expensas.model.dto.request.UserRegisterRequest;
import com.control.expensas.model.dto.response.UserRegisterResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User entityToDto(UserRegisterRequest request) {
        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .dni(request.getDni())
                .surname(request.getSurname())
                .build();
    }

    public UserRegisterResponse dtoToEntity(User user) {
        return UserRegisterResponse.builder()
                .id(user.getUserId())
                .email(user.getEmail())
                .build();
    }
}
