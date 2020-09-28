package com.example.demo.repository;

import com.example.demo.entity.Post;

import com.example.demo.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>{
    List<Post> findByUsuario(Usuario usuario);
}