package com.example.demo.dto.assember;

import com.example.demo.dto.MensagemDTO;
import com.example.demo.entity.Envio;
import com.example.demo.entity.Mensagem;

public class MensagemAssember {

    public static Mensagem dtoToEntityModel(MensagemDTO dto) {

        Mensagem mensagem = new Mensagem();
        mensagem.setAssunto(dto.getAssunto());
        mensagem.setConteudo(dto.getConteudo());
        mensagem.setAnexo(dto.getAnexo());
        mensagem.setEdicao(dto.isEdicao());
        mensagem.setSpam(dto.isSpam());
        mensagem.setVisualizada(dto.isVisualizada());

        return mensagem;
    }
}
