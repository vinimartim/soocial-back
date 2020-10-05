package com.example.demo.dto.assember;

import com.example.demo.dto.MensagemDTO;
import com.example.demo.entity.Envio;
import com.example.demo.entity.Mensagem;

import javax.persistence.EntityManager;

public class MensagemAssember {

    private static EntityManager entityManager;

    public static Mensagem dtoToEntityModel(MensagemDTO dto) {

        Mensagem mensagem = new Mensagem();
        mensagem.setAssunto(dto.getAssunto());
        mensagem.setConteudo(dto.getConteudo());
        mensagem.setEdicao(dto.isEdicao());
        mensagem.setSpam(dto.isSpam());
        mensagem.setEnvio(dto.getEnvio());
        mensagem.setVisualizada(dto.isVisualizada());

        return mensagem;
    }
}
