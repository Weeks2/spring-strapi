package com.weeks2.strapi.service;

import com.weeks2.strapi.local.AuthRequest;
import com.weeks2.strapi.local.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StrapiAuthService {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${api.auth}")
    String authApi;

    public ResponseEntity<AuthResponse> auth(AuthRequest authRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return restTemplate.postForEntity(authApi, new HttpEntity<>(
                String.format("{\"identifier\": \"%s\", \"password\": \"%s\"}",
                        authRequest.getIdentifier(),
                        authRequest.getPassword()),
                headers),
                AuthResponse.class);
    }
}
