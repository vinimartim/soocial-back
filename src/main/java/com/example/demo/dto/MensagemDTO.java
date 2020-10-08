package com.example.demo.dto;

import com.example.demo.entity.Anexo;
import com.example.demo.entity.Envio;
import com.example.demo.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensagemDTO {

    @NotBlank
    private String assunto;

    @NotBlank
    private String conteudo;

    private Anexo anexo;
    private Envio envio;
    private boolean edicao;
    private boolean visualizada;
    private boolean spam;
}
