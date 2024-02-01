package com.example.netology_cloudservice.service;

import com.example.netology_cloudservice.exception.UnauthorizedException;
import com.example.netology_cloudservice.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;


import static com.example.netology_cloudservice.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private Repository userRepository;

    @BeforeEach
    void setUp() {
        Mockito.when(userRepository.findByUsername(USERNAME_1)).thenReturn(USER_1);
    }

    @Test
    void loadUserByUsername() {
        assertEquals(USER_1, userService.loadUserByUsername(USERNAME_1));
    }

    @Test
    void loadUserByUsernameUnauthorized() {
        assertThrows(UnauthorizedException.class, () -> userService.loadUserByUsername(USERNAME_2));
    }
}
