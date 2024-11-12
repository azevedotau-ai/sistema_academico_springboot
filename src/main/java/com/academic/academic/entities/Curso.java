package com.academic.academic.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Entity
@Data
@Table(name = "cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkCurso;

    private String nome;
    private String codigo;
    private Integer duracaoSemestres;

    @OneToMany(mappedBy = "curso")
    private Set<Disciplina> disciplinas;

    @OneToMany(mappedBy = "curso")
    private Set<Aluno> alunos;
}