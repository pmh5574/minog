package com.minog.minog.controller;

import com.minog.minog.domain.User;
import com.minog.minog.exception.InvalidSigninInformation;
import com.minog.minog.repository.UserRepository;
import com.minog.minog.request.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;

    @PostMapping("/auth/login")
    public User login(@RequestBody Login login) {
        // json 아이디/비밀번호
        log.info("login >> {}", login);

        // DB에서 조회
        User user = userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword())
                .orElseThrow(() -> new InvalidSigninInformation());

        return user;
    }
}
