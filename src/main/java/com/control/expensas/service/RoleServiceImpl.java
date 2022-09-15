package com.control.expensas.service;

import com.control.expensas.enums.RoleEnum;
import com.control.expensas.model.Role;
import com.control.expensas.repository.RoleRepository;
import com.control.expensas.service.abstraction.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    @Override
    public Role findBy(RoleEnum roleEnum) {
        return roleRepository.findByRoleName(roleEnum);
    }
}
