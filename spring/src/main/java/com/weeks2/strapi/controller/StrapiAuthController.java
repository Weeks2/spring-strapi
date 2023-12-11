package com.weeks2.strapi.controller;

import com.weeks2.strapi.local.AuthRequest;
import com.weeks2.strapi.local.AuthResponse;
import com.weeks2.strapi.service.StrapiAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/auth")
public class StrapiAuthController {
    @Autowired
    private StrapiAuthService loginService;

    @PostMapping("/local")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest authRequest) {
        return loginService.auth(authRequest);
    }
}
