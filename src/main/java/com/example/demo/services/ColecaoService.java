package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Colecao;
import com.example.demo.repository.ColecaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ColecaoService {
    
    @Autowired
    private ColecaoRepository repository;

    public Colecao save(Colecao entity) {
        return repository.save(entity);
    }

    public void delete(Colecao entity) {
        repository.delete(entity);
    }

    public Optional<Colecao> findById(Long id) {
        return repository.findById(id);
    }

    public List<Colecao> findAll() {
        return repository.findAll();
    }
}