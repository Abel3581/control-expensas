package com.control.expensas.service.unitTestin;

import com.control.expensas.config.utils.JwtUtil;
import com.control.expensas.enums.RoleEnum;
import com.control.expensas.mapper.UserMapper;
import com.control.expensas.model.Role;
import com.control.expensas.model.User;
import com.control.expensas.model.dto.request.UserRegisterRequest;
import com.control.expensas.repository.UserRepository;
import com.control.expensas.service.UserServiceImpl;
import com.control.expensas.service.abstraction.RoleService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    private RoleService roleService;
    @InjectMocks
    private UserServiceImpl userService;
    @InjectMocks
    private UserMapper userMapper;

    @InjectMocks
    private JwtUtil jwtUtil;

    private Role role ;
    private User user;

    @BeforeEach
    void setUp() {
        //Iniciar mockito
        MockitoAnnotations.initMocks(this);

        user = new User(1L,"Abel","Acevedo","email@gmail.com","12345678",28881785,
                new Role(1L,RoleEnum.ROLE_OWNER));

    }
    @AfterEach
    void tearDown(){}

    @Test
    void getUserBy() {

        when(userRepository.findByEmail("email@gmail.com")).thenReturn(user);
        assertNotNull(userService.getUserBy("email@gmail.com"));
    }
    @Test
    void getUserByEmailNull() {
        //setup
        Exception exception = assertThrows(UsernameNotFoundException.class
        ,() ->{
        //check
            userService.getUserBy("test@gmail.com");
                });

        Assertions.assertEquals("User not found", exception.getMessage());
    }

    @Test
    void save() {

    }

    @Test
    void login() {

    }


}