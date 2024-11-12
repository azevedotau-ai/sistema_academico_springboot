package com.academic.academic.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AlunoDTO {
    private Long pkAluno;
    private String nome;
    private String matricula;
    private LocalDate dataIngresso;
    private String email;
    private String telefone;
}