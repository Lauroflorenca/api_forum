package alura.forum.api.domain.curso;

import alura.forum.api.domain.Categoria;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CursoDTO(
        Long id,
        @NotNull
        String nome,
        @NotNull
        Categoria categoria
) { }
