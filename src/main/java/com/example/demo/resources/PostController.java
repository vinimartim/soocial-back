package com.example.demo.controllers;

import com.example.demo.entity.Post;
import com.example.demo.entity.Usuario;
import com.example.demo.services.PostService;

import com.example.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.http.HttpStatus.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService service;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("{id}")
    @ResponseStatus(OK)
    public Post get(@PathVariable(value = "id") Long id) {
        return service
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Post não encontrado"));
    }

    @GetMapping
    @ResponseStatus(OK)
    public List<Post> getAll() {
        return service.findAll().stream().sorted().collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Post add(@RequestBody Post post) throws Exception {
        return service.save(post);
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public Post update(@PathVariable(value = "id") Long id, @RequestBody Post post) {
        return service
            .findById(id)
            .map(postExistente -> {
                post.setId(postExistente.getId());
                try {
                    service.save(post);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return postExistente;
            })
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Post não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable(value = "id") Long id) {
        service
            .findById(id)
            .map(post -> {
                service.delete(post);
                return post;
            })
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Post não encontrado"));
    }

    @GetMapping("usuario/{id}")
    public List<Post> getAllByUsuario(@PathVariable(value = "id") Long id) {
        Usuario usuario = usuarioService
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Post não encontrado"));

        List<Post> postArrayList = service.findByUsuario(usuario);
        Collections.reverse(postArrayList);
        return postArrayList;
    }
}