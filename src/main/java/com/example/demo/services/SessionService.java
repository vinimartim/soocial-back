package com.example.demo.services;

import com.example.demo.entity.Session;
import com.example.demo.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionService {

    @Autowired
    private SessionRepository repository;

    public Session findByToken(String token) {
        return repository.findByToken(token);
    }

    public Session save(Session entity) {
        return repository.save(entity);
    }
}
