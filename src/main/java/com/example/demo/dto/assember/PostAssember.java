package com.example.demo.dto.assember;

import com.example.demo.dto.PostDTO;
import com.example.demo.entity.Post;
import com.example.demo.entity.Usuario;
import com.example.demo.services.UsuarioService;
import com.example.demo.services.impl.AnexoServiceImpl;
import com.example.demo.services.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class PostAssember {
    @Autowired
    private static AnexoServiceImpl anexoServiceImpl;

    @Autowired
    private static UsuarioServiceImpl usuarioServiceImpl;

    public static Post dtoToEntityModel(PostDTO dto) {
        Post post = new Post();
        System.out.println(dto.getUsuario().getId());
//        Usuario usuario = usuarioServiceImpl.findById(dto.getUsuario().getId());
//        if(usuario != null) {
//            post.setUsuario(usuario);
//        } else {
//            post.setUsuario(null);
//        }
        post.setConteudo(dto.getConteudo());
        post.setUsuario(dto.getUsuario());
        post.setEdicao(dto.isEdicao());
        post.setSpam(dto.isEdicao());
        post.setGrupo(dto.getGrupo());
        post.setPrivacidade(dto.getPrivacidade());
        post.setDenuncia(dto.isDenuncia());
        post.setAnexo(dto.getAnexo());
        return post;
    }
}
