package com.minog.minog.repository;

import com.minog.minog.domain.Session;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SessionRepository extends CrudRepository<Session, Long> {
    Optional<Session> findByAccessToken(String accessToken);
}
