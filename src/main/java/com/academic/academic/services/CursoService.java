package com.academic.academic.services;

import com.academic.academic.entities.Curso;
import com.academic.academic.entities.Disciplina;
import com.academic.academic.repository.CursoRepository;
import com.academic.academic.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public List<Curso> listarTodosCursos() {
        return cursoRepository.findAll();
    }

    public Curso buscarCursoPorCodigo(String codigo) {
        long testID = Long.parseLong(codigo);
        return cursoRepository.findById(testID)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
    }

    public Curso buscarCursoPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
    }

    public Curso criarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public void adicionarDisciplina(Curso curso, Disciplina disciplina) {
        disciplina.setCurso(curso);
        curso.getDisciplinas().add(disciplina);
        cursoRepository.save(curso);
    }

    public List<Disciplina> listarDisciplinasPorSemestre(Long cursoId, Integer semestre) {
        Curso curso = buscarCursoPorId(cursoId);
        return curso.getDisciplinas().stream()
                .filter(d -> d.getSemestre().equals(semestre))
                .collect(Collectors.toList());
    }
}