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

    public Post save(Post entity) throws Exception{
        Usuario usuario = usuarioServiceImpl.findById(entity.getUsuario().getId());
        entity.setUsuario(usuario);

        if(entity.getAnexo() != null) {
            Anexo anexo = anexoServiceImpl.findById(entity.getAnexo().getId());
            entity.setAnexo(anexo);
        }

        Classificador classificador = new Classificador();
        String conteudoPost = entity.getConteudo();
        double[] classificada = classificador.classificar(conteudoPost);

        entity.setSpam(!(classificada[0] > classificada[2]));

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

    public List<Post> findByUsuario(Usuario usuario, Sort dataPostagem) {
        return repository.findByUsuario(usuario, dataPostagem);
    }

    public List<Post> findAllByGrupo(Grupo grupo) {
        return repository.findAllByGrupo(grupo);
    }
}