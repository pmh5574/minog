package com.minog.minog.service;

import com.minog.minog.domain.Session;
import com.minog.minog.domain.User;
import com.minog.minog.exception.InvalidSigninInformation;
import com.minog.minog.repository.UserRepository;
import com.minog.minog.request.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;

    @Transactional
    public String signin(Login login) {
        User user = userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword())
                .orElseThrow(() -> new InvalidSigninInformation());
        Session session = user.addSession();

        return session.getAccessToken();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new InvalidSigninInformation());

        if (user == null) {
            return null;
        }

        log.info("user :: = {}", user.getEmail());
        log.info("user :: = {}", user.getId());
        log.info("user :: = {}", user.getPassword());

        return null;
    }
}
