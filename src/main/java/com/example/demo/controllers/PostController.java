package com.example.demo.controllers;

import com.example.demo.entity.Post;
import com.example.demo.services.PostService;

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
@RequestMapping("/api/post")
public class PostController {
    
    private PostService service;

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
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Post add(@RequestBody Post post) {
        return service.save(post);
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public Post update(@PathVariable(value = "id") Long id, @RequestBody Post post) {
        return service
            .findById(id)
            .map(postExistente -> {
                post.setId(postExistente.getId());
                service.save(post);
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
}