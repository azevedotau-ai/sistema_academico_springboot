package com.academic.academic.services;

import com.academic.academic.entities.*;
import com.academic.academic.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class MatriculaService {
    private final MatriculaRepository matriculaRepository;
    private final AlunoRepository alunoRepository;
    private final DisciplinaRepository disciplinaRepository;

    public MatriculaService(MatriculaRepository matriculaRepository,
                            AlunoRepository alunoRepository,
                            DisciplinaRepository disciplinaRepository) {
        this.matriculaRepository = matriculaRepository;
        this.alunoRepository = alunoRepository;
        this.disciplinaRepository = disciplinaRepository;
    }

    public Matricula realizarMatricula(String matriculaAluno, Long pkDisciplina, String semestre) {
        Aluno aluno = alunoRepository.findByMatricula(matriculaAluno)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        Disciplina disciplina = disciplinaRepository.findById(pkDisciplina)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        // Verificar precedências
        if (!verificarPrecedencias(aluno, disciplina)) {
            throw new RuntimeException("Pré-requisitos não atendidos");
        }

        Matricula matricula = new Matricula();
        matricula.setAluno(aluno);
        matricula.setDisciplina(disciplina);
        matricula.setSemestre(semestre);
        matricula.setStatus("ATIVA");

        return matriculaRepository.save(matricula);
    }

    private boolean verificarPrecedencias(Aluno aluno, Disciplina disciplina) {
        if (disciplina.getPrerequisitos() == null || disciplina.getPrerequisitos().isEmpty()) {
            return true;
        }

        Set<Disciplina> prerequisitos = disciplina.getPrerequisitos();
        List<Matricula> disciplinasConcluidas = matriculaRepository
                .findByAlunoAndStatus(aluno, "CONCLUIDA");

        return prerequisitos.stream()
                .allMatch(prereq -> disciplinasConcluidas.stream()
                        .anyMatch(m -> m.getDisciplina().equals(prereq) && m.getNotaFinal() >= 6.0));
    }

    public void lancarNota(Long pkMatricula, Double nota) {
        Matricula matricula = matriculaRepository.findById(pkMatricula)
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));

        matricula.setNotaFinal(nota);
        matricula.setStatus("CONCLUIDA");

        matriculaRepository.save(matricula);
    }
}
