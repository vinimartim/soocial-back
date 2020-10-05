package com.example.demo.dto.assember;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.entity.Role;
import com.example.demo.entity.Usuario;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class UsuarioAssember {

    public static Usuario dtoToEntityModel(UsuarioDTO dto) {

        Usuario usuario = new Usuario();
        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setNome("ROLE_ADMIN");
        roles.add(role);
        usuario.setNome(dto.getNome());
        usuario.setSobrenome(dto.getSobrenome());
        usuario.setUsername(dto.getUsername());
        usuario.setSenha(dto.getSenha());
        usuario.setEmail(dto.getEmail());
        usuario.setGenero(dto.getGenero());
        usuario.setTelefone(dto.getTelefone());
        usuario.setDataNascimento(dto.getDataNascimento());
        usuario.setRoles(roles);
        usuario.setStatusCadastro(dto.isStatusCadastro());
        return usuario;
    }
}
