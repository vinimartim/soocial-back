package com.example.demo.controllers;

import com.example.demo.entity.Post;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.services.UsuarioService;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;
    
    @GetMapping("{id}")
    @ResponseStatus(OK)
    public Usuario get(@PathVariable(value = "id") Long id) {
        return service
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Usuário não encontrado"));
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
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

    @GetMapping("/seguidores/{id}")
    public ResponseEntity<List<Usuario>> getPosts(@PathVariable(value = "id") Long id) {
        List<Usuario> seguindo = service.findSeguidoresById(id);

        if(seguindo != null) {
            return ResponseEntity.ok(seguindo);
        }

        return ResponseEntity.notFound().build();

    }

//    @PostMapping("/salvar-seguidor")
//    public Usuario salvarSeguindo(@RequestBody Usuario usuario) {
//
//    }
}