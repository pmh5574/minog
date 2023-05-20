package com.minog.minog.service;

import com.minog.minog.domain.User;
import com.minog.minog.exception.InvalidSigninInformation;
import com.minog.minog.repository.UserRepository;
import com.minog.minog.request.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {


    private final UserRepository userRepository;

    @Transactional
    public void signin(Login login) {
        User user = userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword())
                .orElseThrow(() -> new InvalidSigninInformation());
        user.addSession();
    }
}
