package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String conteudo;

    @Column(columnDefinition = "TINYINT default 0", insertable = false, updatable = true)
    private boolean edicao;

    @Column(columnDefinition = "TINYINT default 0", insertable = false, updatable = true)
    private boolean spam;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anexo_id")
    private Anexo anexo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Se o grupo for 0, significa que o post é da timeline
    @ManyToOne
    @JoinColumn(name = "grupo")
    private Grupo grupo;
}