package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Grupo;
import com.example.demo.repository.GrupoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GrupoService {

    @Autowired
    private GrupoRepository repository;
    
    public Grupo save(Grupo entity) {
        return repository.save(entity);
    }

    public void delete(Grupo entity) {
        repository.delete(entity);
    }

    public Optional<Grupo> findById(Long id) {
        return repository.findById(id);
    }

    public List<Grupo> findAll() {
        return repository.findAll();
    }
}