package com.control.expensas.service.integration.user;

import com.control.expensas.config.utils.JwtUtil;
import com.control.expensas.enums.RoleEnum;
import com.control.expensas.model.User;
import com.control.expensas.model.dto.request.UserRegisterRequest;
import com.control.expensas.model.dto.response.UserRegisterResponse;
import com.control.expensas.service.integration.AbstractBaseIntegrationTest;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRegisterIntegrationTest extends AbstractBaseIntegrationTest {

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void saveSuccessfully(){
        when(roleService.findBy(eq(RoleEnum.ROLE_OWNER))).thenReturn(stubRole(RoleEnum.ROLE_OWNER));
        when(userRepository.save(isA(User.class))).thenReturn(stubUser(RoleEnum.ROLE_OWNER));

      UserRegisterRequest request = new UserRegisterRequest();
      request.setName("abel");
      request.setSurname("acevedo");
      request.setPassword("12345678");
      request.setEmail("email@gmail.com");
      request.setDni(28881785);
      request.setRole(1);

      HttpEntity<UserRegisterRequest> entity = new HttpEntity<>(request, this.headers);

      ResponseEntity<UserRegisterResponse> response = this.restTemplate.exchange(
              createURLWithPort("/auth/register"), HttpMethod.POST, entity, UserRegisterResponse.class);

      Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
      Assert.assertEquals(Objects.requireNonNull(response.getBody()).getEmail(), request.getEmail());
      Assert.assertTrue(jwtUtil.isValidToken(response.getBody().getToken()));
  }

}
