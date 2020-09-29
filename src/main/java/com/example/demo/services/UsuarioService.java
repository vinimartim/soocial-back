package com.example.demo.services;

import com.example.demo.entity.Usuario;
import com.example.demo.exception.RegradeNegocioException;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioService {
    
    @Autowired
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

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

    public Usuario findById(Long id) {

        return repository
                .findById(id)
                .orElseThrow(() -> new RegradeNegocioException("Usuário não encontrado"));
    }

    public Usuario findByUsername(String username) {

        Usuario usuario = repository.findByUsername(username);

        if(usuario == null) {
            throw new RegradeNegocioException("Não foi encontrado nenhum usuário com esse username");
        }

        return usuario;
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    //public List<Usuario> findSeguidoresById(Long id) { return repository.findSeguidoresById(id); }
}