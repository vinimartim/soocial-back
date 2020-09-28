package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "grupo")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column
    private String nome;

    @Column
    private String descricao;

    @Column
    private String privacidade;

    @ManyToOne
    @JoinColumn(name = "dono")
    private Usuario dono;

    @OneToMany
    @JoinTable(
            name = "grupo_admins",
            joinColumns = @JoinColumn(name = "grupo_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "admin_id", referencedColumnName = "id")
    )
    private List<Usuario> administradores;

    @ManyToMany
    @JoinTable(
            name = "grupo_membros",
            joinColumns = @JoinColumn(name = "grupo_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "membro_id", referencedColumnName = "id")
    )
    private List<Usuario> membros;
}