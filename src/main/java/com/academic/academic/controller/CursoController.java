package com.academic.academic.controller;

import com.academic.academic.entities.Curso;
import com.academic.academic.entities.Disciplina;
import com.academic.academic.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<Curso>> listarCursos() {
        return ResponseEntity.ok(cursoService.listarTodosCursos());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Curso> buscarCurso(@PathVariable String codigo) {
        return ResponseEntity.ok(cursoService.buscarCursoPorCodigo(codigo));
    }

    @PostMapping
    public ResponseEntity<Curso> criarCurso(@RequestBody Curso curso) {
        return ResponseEntity.ok(cursoService.criarCurso(curso));
    }

    @PostMapping("/{cursoId}/disciplinas")
    public ResponseEntity<Void> adicionarDisciplina(
            @PathVariable Long pkCurso,
            @RequestBody Disciplina disciplina) {
        Curso curso = cursoService.buscarCursoPorId(pkCurso);
        cursoService.adicionarDisciplina(curso, disciplina);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{cursoId}/disciplinas")
    public ResponseEntity<List<Disciplina>> listarDisciplinas(@PathVariable Long pkCurso, @RequestParam(required = false) Integer semestre) {
        if (semestre != null) {
            return ResponseEntity.ok(cursoService.listarDisciplinasPorSemestre(pkCurso, semestre));
        } else {
            Curso curso = cursoService.buscarCursoPorId(pkCurso);
            return ResponseEntity.ok(new ArrayList<>(curso.getDisciplinas()));
        }
    }
}
