package com.example.demo.repository;

import com.example.demo.entity.Grupo;
import com.example.demo.entity.Post;

import com.example.demo.entity.Usuario;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>{

    @Query(" select p from Post p where p.usuario.id in (:usuarios) and p.grupo = null")
    List<Post> findBySeguidores(@Param("usuarios") List<Long> usuarios, Sort dataPostagem);
    @Query(" select p from Post p where p.usuario = :usuario and p.grupo = null")
    List<Post> findByUsuario(Usuario usuario, Sort dataPostagem);
    List<Post> findAllByGrupo(Grupo grupo);
}