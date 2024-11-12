package com.academic.academic.config;


import com.academic.academic.entities.Curso;
import com.academic.academic.entities.Disciplina;
import com.academic.academic.repository.CursoRepository;
import com.academic.academic.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Component
public class InitialDataLoader implements CommandLineRunner {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Override
    @Transactional
    public void run(String... args) {
        if (cursoRepository.count() == 0) {
            carregarCursos();
        }
    }

    private void carregarCursos() {
        // Curso de Engenharia Informática
        Curso engInformatica = new Curso();
        engInformatica.setNome("Engenharia Informática");
        engInformatica.setCodigo("EI");
        engInformatica.setDuracaoSemestres(10);

        // Curso de Ciência de Dados
        Curso engenhariaPetroleo = new Curso();
        engenhariaPetroleo.setNome("Engenharia de Petroléo");
        engenhariaPetroleo.setCodigo("EP");
        engenhariaPetroleo.setDuracaoSemestres(10);

        // Curso de Redes de Computadores
        Curso engenhariaTelecom = new Curso();
        engenhariaTelecom.setNome("Engenharia de Telecomunicações");
        engenhariaTelecom.setCodigo("ET");
        engenhariaTelecom.setDuracaoSemestres(10);





        // Disciplinas do 1º Ano
        Disciplina programacao1 = new Disciplina();
        programacao1.setNome("Programação I");
        programacao1.setCodigo("EI101");
        programacao1.setCreditos(6);
        programacao1.setSemestre(1);
        programacao1.setCurso(engInformatica);

        Disciplina algebra = new Disciplina();
        algebra.setNome("Álgebra Linear");
        algebra.setCodigo("EI102");
        algebra.setCreditos(6);
        algebra.setSemestre(1);
        algebra.setCurso(engInformatica);

        Disciplina programacao2 = new Disciplina();
        programacao2.setNome("Programação II");
        programacao2.setCodigo("EI103");
        programacao2.setCreditos(6);
        programacao2.setSemestre(2);
        programacao2.setCurso(engInformatica);
        programacao2.setPrerequisitos(new HashSet<>(Arrays.asList(programacao1)));



        // Disciplinas Petróleo
        Disciplina estatistica = new Disciplina();
        estatistica.setNome("Estatística");
        estatistica.setCodigo("CD101");
        estatistica.setCreditos(6);
        estatistica.setSemestre(1);
        estatistica.setCurso(engenhariaPetroleo);

        Disciplina producao = new Disciplina();
        producao.setNome("Fundamentos de Produção");
        producao.setCodigo("CD102");
        producao.setCreditos(6);
        producao.setSemestre(1);
        producao.setCurso(engenhariaPetroleo);


        // Disciplinas de Telecomunicações
        Disciplina fundamentosRedes = new Disciplina();
        fundamentosRedes.setNome("Fundamentos de Redes");
        fundamentosRedes.setCodigo("RC101");
        fundamentosRedes.setCreditos(6);
        fundamentosRedes.setSemestre(1);
        fundamentosRedes.setCurso(engenhariaTelecom);

        Disciplina segurancaRedes = new Disciplina();
        segurancaRedes.setNome("Segurança em Redes");
        segurancaRedes.setCodigo("RC102");
        segurancaRedes.setCreditos(6);
        segurancaRedes.setSemestre(2);
        segurancaRedes.setCurso(engenhariaTelecom);
        segurancaRedes.setPrerequisitos(new HashSet<>(Arrays.asList(fundamentosRedes)));

        // Salvando cursos
        List<Curso> cursos = Arrays.asList(engInformatica, engenhariaTelecom, engenhariaPetroleo);
        cursoRepository.saveAll(cursos);

        // Salvando disciplinas
        List<Disciplina> disciplinas = Arrays.asList(
                programacao1, algebra, programacao2,
                estatistica, producao,
                fundamentosRedes, segurancaRedes
        );
        disciplinaRepository.saveAll(disciplinas);

        System.out.println("Dados iniciais carregados com sucesso!");
        System.out.println("Cursos carregados: " + cursoRepository.count());
        System.out.println("Disciplinas carregadas: " + disciplinaRepository.count());
    }
}