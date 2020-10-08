package com.example.demo.dto.assember;

import com.example.demo.dto.PostDTO;
import com.example.demo.entity.Post;
import com.example.demo.entity.Usuario;
import com.example.demo.services.UsuarioService;
import com.example.demo.services.impl.AnexoServiceImpl;
import com.example.demo.services.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class PostAssember {
    /**
     * Função que transforma um objeto DTO em um objeto
     * @param dto o objeto dto
     * @return post
     */
    public static Post dtoToEntityModel(PostDTO dto) {
        Post post = new Post();
        System.out.println(dto.getUsuario().getId());
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
