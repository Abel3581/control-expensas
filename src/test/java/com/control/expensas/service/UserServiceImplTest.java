package com.control.expensas.service;

import com.control.expensas.enums.RoleEnum;
import com.control.expensas.model.Role;
import com.control.expensas.model.User;
import com.control.expensas.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;
    private User user;


    @BeforeEach
    void setUp() {
        //Iniciar mockito
        MockitoAnnotations.initMocks(this);


    }

    @Test
    void getUserBy() {
        user = new User(1L,"Abel","Acevedo","email@gmail.com","12345678",28881785,
                new Role(1L,RoleEnum.ROLE_OWNER));
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