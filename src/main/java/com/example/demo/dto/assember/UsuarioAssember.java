package com.example.demo.dto.assember;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.entity.Usuario;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UsuarioAssember {

    public static Usuario dtoToEntityModel(UsuarioDTO dto) {

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setSobrenome(dto.getSobrenome());
        usuario.setUsername(dto.getUsername());
        usuario.setSenha(dto.getSenha());
        usuario.setEmail(dto.getEmail());
        usuario.setGenero(dto.getGenero());
        usuario.setTelefone(dto.getTelefone());
        usuario.setDataNascimento(dto.getDataNascimento());
        usuario.setRoles(dto.getRoles());
        usuario.setStatusCadastro(dto.isStatusCadastro());

        return usuario;
    }
}
