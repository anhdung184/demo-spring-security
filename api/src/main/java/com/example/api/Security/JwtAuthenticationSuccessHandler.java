package com.example.api.Security;

import com.example.api.Model.JwtTokenProvider;
import com.example.api.Model.MyUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtTokenProvider jwtTokenProvider;
    public JwtAuthenticationSuccessHandler(JwtTokenProvider jwtTokenProvider){
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // Get the username from the authentication object
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getDetails();

        // Create a JWT token
        String token = jwtTokenProvider.generateToken(myUserDetails);

        // Add the token to the response header
        response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
    }
}
