package com.control.expensas.service.integration;


import com.control.expensas.enums.RoleEnum;
import com.control.expensas.model.Role;
import com.control.expensas.model.User;
import com.control.expensas.repository.UserRepository;
import com.control.expensas.service.abstraction.RoleService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;

public class AbstractBaseIntegrationTest {

    protected TestRestTemplate restTemplate = new TestRestTemplate();
    protected HttpHeaders headers = new HttpHeaders();
    @MockBean
    protected UserRepository userRepository;
    @MockBean
    protected AuthenticationManager authenticationManager;
    @MockBean
    protected RoleService roleService;
    @LocalServerPort
    private int port;

    protected String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    protected void login(String role) {
        String jwt = SecurityTestConfig.createToken("johnny@gmail.com", role);
        headers.set("Authorization", jwt);
    }

    protected Role stubRole(RoleEnum name){
        Role role = new Role();
        role.setRoleId(1L);
        role.setRoleName(name);
        return role;
    }

    protected User stubUser(RoleEnum name ) {
        User user = new User();
        user.setUserId(1L);
        user.setName("abel");
        user.setSurname("acevedo");
        user.setPassword("12345678");
        user.setEmail("email@gmail.com");
        user.setDni(28881785);
        user.setRole(stubRole(name));
        return user;
    }
}