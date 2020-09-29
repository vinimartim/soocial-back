package com.example.demo.services;

import com.example.demo.entity.Usuario;
import com.example.demo.exception.RegradeNegocioException;
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

        Usuario usuarioExistente = repository.findByUsername(entity.getUsername());

        if(usuarioExistente != null && !usuarioExistente.equals(entity)) {
            throw new RegradeNegocioException("Já existe um usuário cadastrado com esse username");
        }

        return repository.save(entity);
    }

    public Usuario update(Usuario entity) {
        return repository.save(entity);
    }
    
    public void delete(Usuario entity) { repository.delete(entity); }

    public Optional<Usuario> findById(Long id) {
        return repository.findById(id);
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario findByUsername(String username) { return repository.findByUsername(username); }

    public List<Usuario> findSeguidoresById(Long id) { return repository.findSeguidoresById(id); }
}