package com.control.expensas.service.abstraction;

import com.control.expensas.model.dto.request.LoginRequest;
import com.control.expensas.model.dto.request.UserRegisterRequest;
import com.control.expensas.model.dto.response.LoginResponse;
import com.control.expensas.model.dto.response.UserRegisterResponse;

public interface AuthService {
    UserRegisterResponse save(UserRegisterRequest request);

    LoginResponse login(LoginRequest request);
}
