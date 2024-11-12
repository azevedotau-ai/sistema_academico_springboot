package com.academic.academic.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@Table(name = "alunos")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkAluno;

    private String nome;
    private String matricula;
    private LocalDate dataIngresso;
    private String email;
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "pkCurso")
    private Curso curso;

    @OneToMany(mappedBy = "aluno")
    private Set<Matricula> matriculas;
}