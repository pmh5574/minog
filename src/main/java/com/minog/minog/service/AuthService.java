package com.minog.minog.service;

import com.minog.minog.config.JwtTokenProvider;
import com.minog.minog.domain.Session;
import com.minog.minog.domain.TokenInfo;
import com.minog.minog.domain.User;
import com.minog.minog.exception.InvalidSigninInformation;
import com.minog.minog.repository.UserRepository;
import com.minog.minog.request.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static org.springframework.security.core.userdetails.User.builder;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

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
        log.info("user.getPassword() = {}", user.getPassword());

        return builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(Collections.emptyList())
                .build();
    }

    @Transactional
    public TokenInfo signin2(Login login) {
        log.info("tokenInfo = testset");
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        log.info("authentication = {}", authentication);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        log.info("tokenInfo = {}", tokenInfo);

        return tokenInfo;
    }
}
