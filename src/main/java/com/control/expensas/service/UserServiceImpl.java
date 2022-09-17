package com.control.expensas.service;

import com.control.expensas.config.utils.JwtUtil;
import com.control.expensas.enums.RoleEnum;
import com.control.expensas.exception.InvalidCredentialsException;
import com.control.expensas.exception.UserAlreadyExistException;
import com.control.expensas.mapper.UserMapper;
import com.control.expensas.model.Role;
import com.control.expensas.model.User;
import com.control.expensas.model.dto.request.LoginRequest;
import com.control.expensas.model.dto.request.UserRegisterRequest;
import com.control.expensas.model.dto.response.LoginResponse;
import com.control.expensas.model.dto.response.UserRegisterResponse;
import com.control.expensas.repository.UserRepository;
import com.control.expensas.service.abstraction.AuthService;
import com.control.expensas.service.abstraction.RoleService;
import com.control.expensas.service.abstraction.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImpl implements AuthService, UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final RoleService roleService;
    private final UserMapper userMapper;

    private AuthenticationManager authenticationManager;
    @Override
    public UserRegisterResponse save(UserRegisterRequest request) {
        if(userRepository.findByEmail(request.getEmail()) != null)
            throw new UserAlreadyExistException("Email address: " + request.getEmail() + " is already being used.");
        User user = userMapper.entityToDto(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Role role ;
        if(request.getRole() == 1){
           role = roleService.findBy(RoleEnum.ROLE_OWNER);
           user.setRole(role);
        }
        if(request.getRole() == 2){
            role = roleService.findBy(RoleEnum.ROLE_DUTY_MANAGER);
            user.setRole(role);
        }
        User userCreate = userRepository.save(user);
        UserRegisterResponse response = userMapper.dtoToEntity(userCreate);
        response.setToken(jwtUtil.generateToken(userCreate));
        return response;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        authenticate(request);
        User user = getUserBy(request.getEmail());
        LoginResponse response = userMapper.dtoLoginResponse(user);
        response.setToken(jwtUtil.generateToken(user));
        return response;
    }

    public User getUserBy(String email) {
        User user = userRepository.findByEmail(email);
        if(user == null)
            throw new UsernameNotFoundException("User not found");
        return user;
    }

    private void authenticate(LoginRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            ));
        }catch (Exception e){
            throw new InvalidCredentialsException("Invalid email or password.");
        }
    }


}
