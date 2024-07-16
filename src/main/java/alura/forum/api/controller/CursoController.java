package alura.forum.api.controller;

import alura.forum.api.domain.curso.Curso;
import alura.forum.api.domain.curso.CursoDTO;
import alura.forum.api.domain.curso.CursoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Curso", description = "Operações relacionadas aos cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    @Transactional
    public ResponseEntity<Curso> cadastrarUsuario(@RequestBody @Valid CursoDTO cursoDTO) {
        Curso curso = new Curso(cursoDTO);
        Curso novoCurso = cursoService.cadastrarCurso(curso);
        return ResponseEntity.ok(novoCurso);
    }
}