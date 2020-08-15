package com.example.demo.controllers;

import com.example.demo.entity.Usuario;
import com.example.demo.services.UsuarioService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private UsuarioService service;
    
    @GetMapping("{id}")
    @ResponseStatus(OK)
    public Usuario get(@PathVariable(value = "id") Long id) {
        return service
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Usuário não encontrado"));
    }

    @GetMapping
    @ResponseStatus(OK)
    public List<Usuario> getAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Usuario add(@RequestBody Usuario usuario) {
        return service.save(usuario);
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public Usuario update(@PathVariable(value = "id") Long id, @RequestBody Usuario usuario) {
        return service
            .findById(id)
            .map(usuarioExistente -> {
                usuario.setId(usuarioExistente.getId());
                service.save(usuario);
                return usuarioExistente;
            })
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Usuário não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT )
    public void delete(@PathVariable(value = "id") Long id) {
        service
            .findById(id)
            .map(usuario -> {
                service.delete(usuario);
                return usuario;
            })
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Usuário não encontrado"));
    }
}