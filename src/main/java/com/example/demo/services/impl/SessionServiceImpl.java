package com.example.demo.services.impl;

import com.example.demo.entity.Session;
import com.example.demo.exception.RegradeNegocioException;
import com.example.demo.repository.SessionRepository;
import com.example.demo.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository repository;

    public Session findByToken(String token) {
        return repository
                .findByToken(token)
                .orElseThrow(() -> new RegradeNegocioException("Token não encontrado"));
    }

    public Session save(Session entity) {
        return repository.save(entity);
    }
}
