package com.example.demo.dto.assember;

import com.example.demo.dto.PostDTO;
import com.example.demo.entity.Post;

public class PostAssember {

    public static Post dtoToEntityModel(PostDTO dto) {

        Post post = new Post();
        post.setConteudo(dto.getConteudo());
        post.setUsuario(dto.getUsuario());
        post.setEdicao(dto.isEdicao());
        post.setSpam(dto.isEdicao());
        post.setAnexo(dto.getAnexo());
        return post;
    }
}
