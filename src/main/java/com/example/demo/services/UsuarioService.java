package com.example.demo.services;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository repository;

    public UsuarioService() {}

    public Usuario save(Usuario entity) {
        return repository.save(entity);
    }
    
    public void delete(Usuario entity) {
        repository.delete(entity);
    }

    public Optional<Usuario> findById(Long id) {
        return repository.findById(id);
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }
}