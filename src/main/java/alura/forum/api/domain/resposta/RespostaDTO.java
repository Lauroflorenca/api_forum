package alura.forum.api.domain.resposta;

import alura.forum.api.domain.topico.Topico;
import alura.forum.api.domain.usuario.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RespostaDTO(
        Long id,

        @NotNull
        String mensagem,

        LocalDateTime criacao,

        Boolean solucao,
        @NotNull
        Long topico,
        @NotNull
        Long usuario,

        boolean ativo
) {
}
