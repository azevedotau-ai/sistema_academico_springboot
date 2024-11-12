package com.academic.academic.services;

import com.academic.academic.dto.AlunoDTO;
import com.academic.academic.entities.Aluno;
import com.academic.academic.entities.Disciplina;
import com.academic.academic.entities.Matricula;
import com.academic.academic.repository.AlunoRepository;
import com.academic.academic.repository.MatriculaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AlunoService {
    private final AlunoRepository alunoRepository;
    private final MatriculaRepository matriculaRepository;

    public AlunoService(AlunoRepository alunoRepository, MatriculaRepository matriculaRepository) {
        this.alunoRepository = alunoRepository;
        this.matriculaRepository = matriculaRepository;
    }

    public Aluno cadastrarAluno(AlunoDTO dto) {
        if (alunoRepository.findByMatricula(dto.getMatricula()).isPresent()) {
            throw new RuntimeException("Já existe um aluno com esta matrícula");
        }

        Aluno aluno = new Aluno();
        atualizarDadosAluno(aluno, dto);

        return alunoRepository.save(aluno);
    }

    public Aluno atualizarAluno(Long pkAluno, AlunoDTO dto) {
        Aluno aluno = alunoRepository.findById(pkAluno)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        atualizarDadosAluno(aluno, dto);
        return alunoRepository.save(aluno);
    }

    private void atualizarDadosAluno(Aluno aluno, AlunoDTO dto) {
        aluno.setNome(dto.getNome());
        aluno.setMatricula(dto.getMatricula());
        aluno.setEmail(dto.getEmail());
        aluno.setTelefone(dto.getTelefone());
    }

    public void removerAluno(Long pkAluno) {
        if (!alunoRepository.existsById(pkAluno)) {
            throw new RuntimeException("Aluno não encontrado");
        }
        alunoRepository.deleteById(pkAluno);
    }

    public List<Aluno> listarAlunos() {
        return alunoRepository.findAll();
    }

    public Aluno buscarAluno(Long pkAluno) {
        return alunoRepository.findById(pkAluno)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }

    public List<Disciplina> listarDisciplinasCursadas(Long pkAluno) {
        Aluno aluno = buscarAluno(pkAluno);
        return matriculaRepository.findByAlunoAndStatus(aluno, "CONCLUIDA")
                .stream()
                .map(Matricula::getDisciplina)
                .collect(Collectors.toList());
    }

    public List<Matricula> historicoAluno(Long pkAluno) {
        Aluno aluno = buscarAluno(pkAluno);
        return matriculaRepository.findHistoricoByAluno(aluno);
    }

    public List<Matricula> matriculasAtivasAluno(Long pkAluno) {
        Aluno aluno = buscarAluno(pkAluno);
        return matriculaRepository.findByAlunoAndStatusOrderBySemestreDesc(aluno, "ATIVA");
    }

    public long contarMatriculasAtivasNoSemestre(Long pkAluno, String semestre) {
        Aluno aluno = buscarAluno(pkAluno);
        return matriculaRepository.countMatriculasAtivasNoSemestre(aluno, semestre);
    }
}
