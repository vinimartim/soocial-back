package com.example.demo.dto.assember;

import com.example.demo.dto.PostDTO;
import com.example.demo.entity.Post;
import com.example.demo.entity.Usuario;
import com.example.demo.services.impl.AnexoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class PostAssember {
    @Autowired
    private static AnexoServiceImpl anexoServiceImpl;


    public static Post dtoToEntityModel(PostDTO dto) {
        Post post = new Post();
        post.setConteudo(dto.getConteudo());
        post.setUsuario(dto.getUsuario());
        Usuario usuario = dto.getUsuario();
        post.setUsuario(usuario);
        post.setEdicao(dto.isEdicao());
        post.setSpam(dto.isEdicao());
        post.setGrupo(dto.getGrupo());
        post.setPrivacidade(dto.getPrivacidade());
        return post;
    }
}
