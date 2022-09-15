package com.control.expensas.service.abstraction;

import com.control.expensas.enums.RoleEnum;
import com.control.expensas.model.Role;

public interface RoleService {
    Role findBy(RoleEnum roleEnum);
}
