package com.academic.academic.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "matriculas")
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkMatricula;

    @ManyToOne
    private Aluno aluno;

    @ManyToOne
    private Disciplina disciplina;
    private String semestre;
    private LocalDateTime dataMatricula;
    private Double notaFinal;
    private String status; // ATIVA, CONCLUIDA, CANCELADA
}
