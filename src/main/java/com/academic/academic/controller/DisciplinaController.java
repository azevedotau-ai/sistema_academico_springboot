package com.academic.academic.controller;

import com.academic.academic.dto.DisciplinaDTO;
import com.academic.academic.entities.Disciplina;
import com.academic.academic.services.DisciplinaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {
    private final DisciplinaService disciplinaService;

    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    @PostMapping
    public ResponseEntity<Disciplina> cadastrarDisciplina(@RequestBody DisciplinaDTO disciplinaDTO) {
        return ResponseEntity.ok(disciplinaService.cadastrarDisciplina(disciplinaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> atualizarDisciplina(
            @PathVariable Long id,
            @RequestBody DisciplinaDTO disciplinaDTO) {
        return ResponseEntity.ok(disciplinaService.atualizarDisciplina(id, disciplinaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerDisciplina(@PathVariable Long id) {
        disciplinaService.removerDisciplina(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Disciplina>> listarDisciplinas() {
        return ResponseEntity.ok(disciplinaService.listarDisciplinas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> buscarDisciplina(@PathVariable Long id) {
        return ResponseEntity.ok(disciplinaService.buscarDisciplina(id));
    }
}