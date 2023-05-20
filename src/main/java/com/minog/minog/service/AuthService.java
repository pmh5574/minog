package com.minog.minog.service;

import com.minog.minog.domain.Session;
import com.minog.minog.domain.User;
import com.minog.minog.exception.InvalidSigninInformation;
import com.minog.minog.repository.UserRepository;
import com.minog.minog.request.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {


    private final UserRepository userRepository;

    @Transactional
    public String signin(Login login) {
        User user = userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword())
                .orElseThrow(() -> new InvalidSigninInformation());
        Session session = user.addSession();

        return session.getAccessToken();
    }
}
