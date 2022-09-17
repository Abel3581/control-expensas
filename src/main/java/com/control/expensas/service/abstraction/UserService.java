package com.control.expensas.service.abstraction;

import com.control.expensas.model.User;

public interface UserService {

    User getUserBy(String email);
}
