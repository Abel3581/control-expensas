package com.control.expensas.service.abstraction;

import com.control.expensas.model.dto.request.UserRegisterRequest;
import com.control.expensas.model.dto.response.UserRegisterResponse;

public interface AuthService {
    UserRegisterResponse register(UserRegisterRequest request);
}
