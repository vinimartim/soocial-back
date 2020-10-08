package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column
    private LocalDateTime dataPostagem;

    @Column
    private boolean edicao;

    @Column
    private boolean spam;

    @Column
    private boolean denuncia;

    @Column
    private String privacidade;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "anexo_id")
    private Anexo anexo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Se o grupo for null, significa que o post é da timeline
    @ManyToOne
    @JoinColumn(name = "grupo")
    private Grupo grupo;

    @PrePersist
    public void setDataPostagem() {
        this.dataPostagem = LocalDateTime.now();
    }
}