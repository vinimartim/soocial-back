package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Post;
import com.example.demo.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostService {

    @Autowired
    private PostRepository repository;

    public Post save(Post entity) {
        return repository.save(entity);
    }

    public void delete(Post entity) {
        repository.delete(entity);
    }

    public Optional<Post> findById(Long id) {
        return repository.findById(id);
    }

    public List<Post> findAll() {
        return repository.findAll();
    }
    
}