package alura.forum.api.domain.topico;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosTopicosAtualizar(
        String titulo,
        String mensagem,
        LocalDateTime criacao,
        SituacaoTopico st_topico,
        Long curso
) {
}
