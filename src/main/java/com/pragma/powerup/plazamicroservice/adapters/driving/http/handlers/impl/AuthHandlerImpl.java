package com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.LoginRequestDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers.IAuthHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class AuthHandlerImpl implements IAuthHandler {

    private final RestTemplate restTemplate;
    private final String authApiUrl;

    public AuthHandlerImpl(RestTemplate restTemplate, @Value("${auth.api.url}") String authApiUrl)  {
        this.restTemplate = restTemplate;
        this.authApiUrl = authApiUrl;
    }

    @Override
    public String login(LoginRequestDto loginRequestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestBody = "{\"userDni\":\"" + loginRequestDto
                .getUserDni() + "\",\"password\":\"" + loginRequestDto.getPassword() + "\"}";
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate
                .postForEntity(authApiUrl + "/auth/login", request, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return null;
        }
    }
    @Override
    public String refresh(@Valid String jwtResponseDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestBody = "{\"token\":\"" + jwtResponseDto + "\"}";
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate
                .postForEntity(authApiUrl + "/auth/refresh", request, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return null;
        }
    }
}