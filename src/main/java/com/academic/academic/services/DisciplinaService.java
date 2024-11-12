package com.academic.academic.services;

import com.academic.academic.dto.DisciplinaDTO;
import com.academic.academic.entities.Disciplina;
import com.academic.academic.repository.DisciplinaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class DisciplinaService {
    private final DisciplinaRepository disciplinaRepository;

    public DisciplinaService(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }

    public Disciplina cadastrarDisciplina(DisciplinaDTO dto) {
        if (disciplinaRepository.existsByCodigo(dto.getCodigo())) {
            throw new RuntimeException("Já existe uma disciplina com este código");
        }

        Disciplina disciplina = new Disciplina();
        disciplina.setNome(dto.getNome());
        disciplina.setCodigo(dto.getCodigo());
        disciplina.setCreditos(dto.getCreditos());

        if (dto.getPrerequisitosIds() != null && !dto.getPrerequisitosIds().isEmpty()) {
            Set<Disciplina> prerequisitos = dto.getPrerequisitosIds().stream()
                    .map(id -> disciplinaRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Pré-requisito não encontrado: " + id)))
                    .collect(Collectors.toSet());
            disciplina.setPrerequisitos(prerequisitos);
        }

        return disciplinaRepository.save(disciplina);
    }

    public Disciplina atualizarDisciplina(Long pkDisciplina, DisciplinaDTO dto) {
        Disciplina disciplina = disciplinaRepository.findById(pkDisciplina)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        disciplina.setNome(dto.getNome());
        disciplina.setCreditos(dto.getCreditos());

        if (dto.getPrerequisitosIds() != null) {
            Set<Disciplina> prerequisitos = dto.getPrerequisitosIds().stream()
                    .map(preReqId -> disciplinaRepository.findById(preReqId)
                            .orElseThrow(() -> new RuntimeException("Pré-requisito não encontrado: " + preReqId)))
                    .collect(Collectors.toSet());
            disciplina.setPrerequisitos(prerequisitos);
        }

        return disciplinaRepository.save(disciplina);
    }

    public void removerDisciplina(Long pkDisciplina) {
        if (!disciplinaRepository.existsById(pkDisciplina)) {
            throw new RuntimeException("Disciplina não encontrada");
        }
        disciplinaRepository.deleteById(pkDisciplina);
    }

    public List<Disciplina> listarDisciplinas() {
        return disciplinaRepository.findAll();
    }

    public Disciplina buscarDisciplina(Long pkDisciplina) {
        return disciplinaRepository.findById(pkDisciplina)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));
    }
}

