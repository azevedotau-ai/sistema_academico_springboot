package com.academic.academic.controller;

import com.academic.academic.dto.AlunoDTO;
import com.academic.academic.entities.Aluno;
import com.academic.academic.entities.Disciplina;
import com.academic.academic.entities.Matricula;
import com.academic.academic.services.AlunoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {
    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public ResponseEntity<Aluno> cadastrarAluno(@RequestBody AlunoDTO alunoDTO) {
        return ResponseEntity.ok(alunoService.cadastrarAluno(alunoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizarAluno(
            @PathVariable Long id,
            @RequestBody AlunoDTO alunoDTO) {
        return ResponseEntity.ok(alunoService.atualizarAluno(id, alunoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerAluno(@PathVariable Long pkAluno) {
        alunoService.removerAluno(pkAluno);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listarAlunos() {
        return ResponseEntity.ok(alunoService.listarAlunos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarAluno(@PathVariable Long pkAluno) {
        return ResponseEntity.ok(alunoService.buscarAluno(pkAluno));
    }

    @GetMapping("/{id}/disciplinas-cursadas")
    public ResponseEntity<List<Disciplina>> listarDisciplinasCursadas(@PathVariable Long pkAluno) {
        return ResponseEntity.ok(alunoService.listarDisciplinasCursadas(pkAluno));
    }

    @GetMapping("/{id}/historico")
    public ResponseEntity<List<Matricula>> historicoAluno(@PathVariable Long pkAluno) {
        return ResponseEntity.ok(alunoService.historicoAluno(pkAluno));
    }
}
