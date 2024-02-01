package com.example.netology_cloudservice.repository;

import com.example.netology_cloudservice.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.*;


public class AuthenticationRepositoryTest {

    private AuthenticationRepository authenticationRepository;

    private final Map<String, String> testTokensAndUsernames = new ConcurrentHashMap<>();


    @BeforeEach
    void setUp() {
        authenticationRepository = new AuthenticationRepository();
        authenticationRepository.putTokenAndUsername(TestData.TOKEN_1, TestData.USERNAME_1);
        testTokensAndUsernames.clear();
        testTokensAndUsernames.put(TestData.TOKEN_1, TestData.USERNAME_1);
    }

    @Test
    void putTokenAndUsername() {
        String beforePut = authenticationRepository.getUsernameByToken(TestData.TOKEN_2);
        assertNull(beforePut);
        authenticationRepository.putTokenAndUsername(TestData.TOKEN_2, TestData.USERNAME_2);
        String afterPut = authenticationRepository.getUsernameByToken(TestData.TOKEN_2);
        assertEquals(TestData.USERNAME_2, afterPut);
    }

    @Test
    void removeTokenAndUsernameByToken() {
        String beforeRemove = authenticationRepository.getUsernameByToken(TestData.TOKEN_1);
        assertNotNull(beforeRemove);
        authenticationRepository.removeTokenAndUserNameByToken(TestData.TOKEN_1);
        String afterRemove = authenticationRepository.getUsernameByToken(TestData.TOKEN_1);
        assertNull(afterRemove);
    }

    @Test
    void getUsernameByToken() {
        assertEquals(testTokensAndUsernames.get(TestData.TOKEN_1), authenticationRepository.getUsernameByToken(TestData.TOKEN_1));
    }

}
