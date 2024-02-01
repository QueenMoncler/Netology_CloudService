package com.example.netology_cloudservice.service;

import com.example.netology_cloudservice.TestData;
import com.example.netology_cloudservice.jwt.JwtTokenUtil;
import com.example.netology_cloudservice.repository.AuthenticationRepository;
import com.example.netology_cloudservice.repository.AuthenticationRepositoryTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.authentication.AuthenticationManager;


import static com.example.netology_cloudservice.TestData.BEARER_TOKEN_SUBSTRING_7;
import static org.junit.Assert.assertEquals;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AuthenticationServiceTest {

    @InjectMocks
    private AuthenticationService authenticationService;

    @Mock
    private AuthenticationRepository authenticationRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Mock
    private UserService userService;

    @Test
    void login() {
        Mockito.when(userService.loadUserByUsername(TestData.USERNAME_1)).thenReturn(TestData.USER_1);
        Mockito.when(jwtTokenUtil.generateToken(TestData.USER_1)).thenReturn(TestData.TOKEN_1);
        assertEquals(TestData.AUTHENTICATION_RESPONSE, authenticationService.login(TestData.AUTHENTICATION_REQUEST));
        Mockito.verify(authenticationManager, Mockito.times(1)).authenticate(TestData.USERNAME_PASSWORD_AUTHENTICATION_TOKEN);
        Mockito.verify(authenticationRepository, Mockito.times(1)).putTokenAndUsername(TestData.TOKEN_1, TestData.USERNAME_1);
    }

    @Test
    void logout() {
        Mockito.when(authenticationRepository.getUsernameByToken(BEARER_TOKEN_SUBSTRING_7)).thenReturn(TestData.USERNAME_1);
        authenticationService.logout(TestData.BEARER_TOKEN);
        Mockito.verify(authenticationRepository, Mockito.times(1)).getUsernameByToken(TestData.BEARER_TOKEN_SUBSTRING_7);
        Mockito.verify(authenticationRepository, Mockito.times(1)).removeTokenAndUserNameByToken(TestData.BEARER_TOKEN_SUBSTRING_7);
    }
}
