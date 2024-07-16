package alura.forum.api.domain.topico;

import alura.forum.api.domain.curso.Curso;
import alura.forum.api.domain.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


public record TopicoDTO(
        Long id,
        @NotNull
        String titulo,
        @NotNull
        String mensagem,
        LocalDateTime criacao,
        @NotNull
        SituacaoTopico st_topico,
        @NotNull
        Curso curso,
        @NotNull
        Usuario usuario
) {
}
