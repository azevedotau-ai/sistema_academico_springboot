package com.academic.academic.repository;

import com.academic.academic.entities.Aluno;
import com.academic.academic.entities.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {



    List<Matricula> findByAluno(Aluno aluno);
        // Buscar matrículas por aluno e semestre
        List<Matricula> findByAlunoAndSemestre(Aluno aluno, String semestre);

        // Buscar matrículas por aluno e status
        List<Matricula> findByAlunoAndStatus(Aluno aluno, String status);

        // Buscar todas as matrículas de um aluno
        List<Matricula> findAllByAluno(Aluno aluno);

        // Buscar matrículas ativas de um aluno
        List<Matricula> findByAlunoAndStatusOrderBySemestreDesc(Aluno aluno, String status);

        // Buscar histórico completo do aluno ordenado por semestre
        @Query("SELECT m FROM Matricula m WHERE m.aluno = :pkAluno ORDER BY m.semestre DESC")
        List<Matricula> findHistoricoByAluno(@Param("pkAluno") Aluno aluno);

        // Verificar se aluno já está matriculado na disciplina no semestre
        boolean existsByAlunoAndDisciplinaIdAndSemestre(Aluno pkAluno, Long pkDisciplina, String semestre);

        // Contar número de matrículas ativas do aluno no semestre
        @Query("SELECT COUNT(m) FROM Matricula m WHERE m.aluno = :pkAluno AND m.semestre = :semestre AND m.status = 'ATIVA'")
        long countMatriculasAtivasNoSemestre(@Param("pkAluno") Aluno aluno, @Param("semestre") String semestre);

}
