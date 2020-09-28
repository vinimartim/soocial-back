package com.example.demo.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Role implements GrantedAuthority {

    @Id
    private String nome;

    @ManyToMany(mappedBy = "roles")
    private List<Usuario> usuarios;

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Usuario> getUsuarios() {
        return this.usuarios;
    }

    @Override
    public String getAuthority() {
        return this.nome;
    }
}
