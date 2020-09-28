package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Post;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.PostRepository;

import com.example.demo.utils.classificador.Classificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostService {

    @Autowired
    private PostRepository repository;

//    @Autowired
//    private Classificador classificador;

    public Post save(Post entity) throws Exception{
        Classificador classificador = new Classificador();
        String conteudoPost = entity.getConteudo();
        double[] classificada = classificador.classificar(conteudoPost);

        if(classificada[0] > classificada[2]) {
            entity.setSpam(false);
        } else {
            entity.setSpam(true);
        }

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

    public List<Post> findByUsuario(Usuario usuario) { return repository.findByUsuario(usuario); }
}