package com.example.demo.dto;

import com.example.demo.entity.Anexo;
import com.example.demo.entity.Grupo;
import com.example.demo.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    @NotBlank
    private String conteudo;

    @NotBlank
    private String privacidade;

    @NotNull
    private Usuario usuario;
    private Grupo grupo;
    private Anexo anexo;
    private boolean edicao;
    private boolean spam;
    private boolean denuncia;

}
