package com.example.demo.services.impl;

import com.example.demo.entity.Seguidores;
import com.example.demo.entity.Usuario;
import com.example.demo.exception.RegradeNegocioException;
import com.example.demo.repository.SeguidoresRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioServiceImpl extends UsuarioService {
    
    @Autowired
    private final UsuarioRepository repository;
    
    @Autowired
    private SeguidoresRepository seguidoresRepository;

    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario save(Usuario entity) {
        Usuario usuarioExistente = repository.findByUsername(entity.getUsername());
        Usuario usuario;

        if(usuarioExistente != null && !usuarioExistente.equals(entity)) {
            throw new RegradeNegocioException("Já existe um usuário cadastrado com esse username");
        }

        usuario = repository.save(entity);

        Seguidores seguidores = new Seguidores();
        seguidores.setEstaSeguindo(entity);
        seguidores.setQuemSegue(entity);
        seguidoresRepository.save(seguidores);

        return usuario;
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
    public boolean existsById(Long id) { return repository.existsById(id); }
}