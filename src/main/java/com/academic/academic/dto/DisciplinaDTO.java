package com.academic.academic.dto;

import lombok.Data;

import java.util.Set;

@Data
public class DisciplinaDTO {
    private Long pkDisciplina;
    private String nome;
    private String codigo;
    private Integer creditos;
    private Set<Long> prerequisitosIds;
}
