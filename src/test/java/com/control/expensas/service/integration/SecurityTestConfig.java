package com.control.expensas.service.integration;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SecurityTestConfig {

    private static final String SECRET_KEY = "3c1dab2b9a676e66b332d2deef2129b0d775bcb8";
    private static final String BEARER_TOKEN = "Bearer %s";
    private static final String AUTHORITIES = "authorities";

    public static String createToken(String subject, String role) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(role);

        String token = Jwts.builder()
                .claim(AUTHORITIES,
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes(StandardCharsets.UTF_8)).compact();
        return String.format(BEARER_TOKEN, token);
    }

    public String generateToken(UserDetails userDetails) {
        String token = Jwts.builder()
                .claim(AUTHORITIES, getFirstAuthority(userDetails))
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(40).toInstant()))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes(StandardCharsets.UTF_8))
                .compact();
        return String.format(BEARER_TOKEN, token);
    }

    private String getFirstAuthority(UserDetails userDetails) {
        Optional<? extends GrantedAuthority> authority = userDetails.getAuthorities()
                .stream()
                .findFirst();

        if (authority.isEmpty()) {
            throw new IllegalArgumentException("User must have one authority.");
        }

        return authority.get().getAuthority();
    }
}