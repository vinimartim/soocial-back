package com.example.demo.dto.assember;

import com.example.demo.dto.GrupoDTO;
import com.example.demo.entity.Grupo;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GrupoAssember {

    public static Grupo dtoToEntityModel(GrupoDTO dto) {
        Grupo grupo = new Grupo();
        grupo.setNome(dto.getNome());
        grupo.setPrivacidade(dto.getPrivacidade());
        grupo.setDescricao(dto.getDescricao());
        grupo.setDono(dto.getDono());
        grupo.setMembros(dto.getMembros());
        grupo.setAdministradores(dto.getAdministradores());
        return grupo;
    }
}
