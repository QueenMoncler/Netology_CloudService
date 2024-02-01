package com.example.netology_cloudservice.service;



import com.example.netology_cloudservice.dto.request.AuthenticationRequest;
import com.example.netology_cloudservice.dto.response.AuthenticationResponse;
import com.example.netology_cloudservice.jwt.JwtTokenUtil;
import com.example.netology_cloudservice.repository.AuthenticationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class AuthenticationService {

    private AuthenticationRepository authenticationRepository;
    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private UserService userService;

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        final String username = authenticationRequest.getLogin();
        final String password = authenticationRequest.getPassword();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        final UserDetails userDetails = userService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);
        authenticationRepository.putTokenAndUsername(token, username);
        log.info("User {} authentication. JWT: {}", username, token);
        return new AuthenticationResponse(token);
    }

    public void logout(String authToken) {
        final String token = authToken.substring(7);
        final String username = authenticationRepository.getUsernameByToken(token);
        log.info("User {} logout. JWT is disabled.", username);
        authenticationRepository.removeTokenAndUserNameByToken(token);
    }
}
