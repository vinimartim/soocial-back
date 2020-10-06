package com.example.demo.dto;

import com.example.demo.entity.Anexo;
import com.example.demo.entity.Grupo;
import com.example.demo.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    @NotBlank
    private String conteudo;
    private boolean edicao;
    private String privacidade;
    private boolean spam;
    private Usuario usuario;
    private Grupo grupo;
    private boolean denuncia;
    private Anexo anexo;

}
