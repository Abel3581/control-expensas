package com.control.expensas.service.integration.user;

import com.control.expensas.enums.RoleEnum;
import com.control.expensas.model.dto.request.LoginRequest;
import com.control.expensas.model.dto.response.LoginResponse;
import com.control.expensas.service.integration.AbstractBaseIntegrationTest;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserLoginIntegrationTest extends AbstractBaseIntegrationTest {

    @Test
    public void loginSuccessfully(){
        when(authenticationManager.authenticate(any())).thenReturn(null);
        when(userRepository.findByEmail(eq("email@gmail.com"))).thenReturn(stubUser(RoleEnum.ROLE_OWNER));

        LoginRequest request = new LoginRequest();
        request.setEmail("email@gmail.com");
        request.setPassword("12345678");
        HttpEntity<LoginRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<LoginResponse> response = restTemplate.exchange(
                createURLWithPort("/auth/login"), HttpMethod.POST,entity, LoginResponse.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals("email@gmail.comm", Objects.requireNonNull(response.getBody()).getEmail());
    }
}
