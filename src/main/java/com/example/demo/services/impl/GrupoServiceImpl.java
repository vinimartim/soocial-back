package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.Grupo;
import com.example.demo.entity.Usuario;
import com.example.demo.exception.RegradeNegocioException;
import com.example.demo.repository.GrupoRepository;

import com.example.demo.services.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GrupoServiceImpl implements GrupoService {

    @Autowired
    private GrupoRepository repository;

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;
    
    public Grupo save(Grupo entity) {
        return repository.save(entity);
    }

    public Grupo update(Grupo entity) {
        // Checa se o administrador que deseja adiciona já é um membro do grupo
        for(Usuario u : entity.getAdministradores()) {
            if(!repository.existsByMembros(u)) {
                throw new RegradeNegocioException("Para cadastrar um administrador ele precisa antes ser um membro do grupo");
            }
        }
        return repository.save(entity);
    }

    public boolean existsById(Long id) { return repository.existsById(id); }

    public void delete(Grupo entity) {
        repository.delete(entity);
    }

    public Grupo findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new RegradeNegocioException("Grupo não encontrado"));
    }

    public List<Grupo> findAll() {
        return repository.findAll();
    }

    public List<Grupo> findByDono(Usuario dono) { return repository.findByDono(dono); }

    public List<Grupo> findByMembros(Usuario usuario) { return repository.findByMembros(usuario); }

    public List<Usuario> findMembrosByGrupo(Grupo grupo) {
        List<Usuario> membros = new ArrayList<>();
        repository
                .findMembrosByGrupo(grupo)
                .forEach(usuario -> membros.add(usuarioServiceImpl.findById(usuario)));

        return membros;
    }

    public List<Usuario> findAdmsByGrupo(Grupo grupo) {
        List<Usuario> membros = new ArrayList<>();
        repository
                .findAdmsByGrupo(grupo)
                .forEach(usuario -> membros.add(usuarioServiceImpl.findById(usuario)));

        return membros;
    }
}