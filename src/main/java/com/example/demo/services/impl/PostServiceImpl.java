package com.example.demo.services.impl;

import com.example.demo.entity.Anexo;
import com.example.demo.entity.Grupo;
import com.example.demo.entity.Post;
import com.example.demo.entity.Usuario;
import com.example.demo.exception.RegradeNegocioException;
import com.example.demo.repository.PostRepository;
import com.example.demo.services.PostService;
import com.example.demo.utils.classificador.Classificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository repository;

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @Autowired
    private AnexoServiceImpl anexoServiceImpl;

    @Autowired
    private Classificador classificador; // Carrega o dataset

    public Post save(Post entity) throws Exception {
        entity.setSpam(classificador.classificar(entity.getConteudo()));
        Usuario usuario = usuarioServiceImpl.findById(entity.getUsuario().getId());
        entity.setUsuario(usuario);
        return repository.save(entity);
    }

    public void delete(Post entity) {
        repository.delete(entity);
    }

    public boolean existsById(Long id) { return repository.existsById(id); }

    public Post findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new RegradeNegocioException("Post não encontrado"));
    }

    public List<Post> findAll() {
        return repository.findAll();
    }

    public List<Post> findBySeguidores(List<Long> seguidores, Sort dataPostagem) {
        return repository.findBySeguidores(seguidores, dataPostagem);
    }

    public List<Post> findByUsuario(Usuario usuario, Sort dataPostagem) {
        return repository.findByUsuario(usuario, dataPostagem);
    }

    public List<Post> findAllByGrupo(Grupo grupo) {
        return repository.findAllByGrupo(grupo);
    }
}