package com.example.demo.dto;

import com.example.demo.entity.Anexo;
import com.example.demo.entity.Usuario;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostDTO {

    private String conteudo;
    private boolean edicao;
    private Anexo anexo;
    private Usuario usuario;

}
