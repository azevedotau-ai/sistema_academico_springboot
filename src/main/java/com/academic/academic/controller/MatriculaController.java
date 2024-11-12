package com.academic.academic.controller;

import com.academic.academic.entities.*;
import com.academic.academic.entities.*;
import com.academic.academic.services.MatriculaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {
    private final MatriculaService matriculaService;

    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    @PostMapping
    public ResponseEntity<Matricula> realizarMatricula(
            @RequestParam String matriculaAluno,
            @RequestParam Long disciplinaId,
            @RequestParam String semestre) {
        return ResponseEntity.ok(
                matriculaService.realizarMatricula(matriculaAluno, disciplinaId, semestre)
        );
    }

    @PutMapping("/{id}/nota")
    public ResponseEntity<Void> lancarNota(
            @PathVariable Long id,
            @RequestParam Double nota) {
        matriculaService.lancarNota(id, nota);
        return ResponseEntity.ok().build();
    }
}

