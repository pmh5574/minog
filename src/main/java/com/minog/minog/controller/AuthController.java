package com.minog.minog.controller;

import com.minog.minog.domain.TokenInfo;
import com.minog.minog.request.Login;
import com.minog.minog.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/login")
    public TokenInfo login(@RequestBody Login login) {
        log.info("controller login = {}", login);
        return authService.signin2(login);
    }
}
