package com.example.demo.dto;

import com.example.demo.entity.Anexo;
import com.example.demo.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensagemDTO {
    private String assunto;
    private String conteudo;
    private Anexo anexo;

    //username
    private Usuario destinatario;
    private Usuario remetente;
    private boolean edicao;
    private boolean visualizada;
    private boolean spam;
}
