package com.control.expensas.config.seeder;


import com.control.expensas.enums.RoleEnum;
import com.control.expensas.model.Role;
import com.control.expensas.repository.RoleRepository;
import com.control.expensas.repository.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
public class SeederUsers {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @EventListener
    public void seed(ContextRefreshedEvent event) {
        List<Role> roles = roleRepository.findAll();
        if (roles.isEmpty()) {
            createRoles();
        }
    }

    private void createRoles() {
        createRole(1L, RoleEnum.ROLE_OWNER);
        createRole(2L, RoleEnum.ROLE_DUTY_MANAGER);
        createRole(3L, RoleEnum.ROLE_ADMIN);
    }

    private void createRole(long id, RoleEnum roleEnum) {
        Role role = new Role();
        role.setRoleId(id);
        role.setRoleName(roleEnum);
        roleRepository.save(role);
    }

}
