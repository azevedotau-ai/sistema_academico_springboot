package com.academic.academic.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Entity
@Data
@Table(name = "disciplinas")
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkDisciplina;

    private String nome;
    private String codigo;
    private Integer creditos;
    private Integer semestre; // Semestre recomendado no curso

    @ManyToOne
    @JoinColumn(name = "pkCurso")
    private Curso curso;

    @ManyToMany
    private Set<Disciplina> prerequisitos;
}