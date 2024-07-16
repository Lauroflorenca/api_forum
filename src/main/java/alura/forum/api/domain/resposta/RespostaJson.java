package alura.forum.api.domain.resposta;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RespostaJson(
        Long id,

        String mensagem,

        LocalDateTime criacao,

        Boolean solucao,

        String topico,

        String usuario

) {
    public RespostaJson(Resposta retornoJson) {
        this(
                retornoJson.getId() != null ? retornoJson.getId() : null,
                retornoJson.getMensagem() != null ? retornoJson.getMensagem() : null,
                retornoJson.getCriacao() != null ? retornoJson.getCriacao() : null,
                retornoJson.getSolucao() != null ? retornoJson.getSolucao() : null,
                retornoJson.getTopico() != null ? retornoJson.getTopico().getTitulo() : null,
                retornoJson.getUsuario() != null ? retornoJson.getUsuario().getNome() : null
        );
    }

    public RespostaJson(Long id, String mensagem, LocalDateTime criacao, Boolean solucao, String topico, String usuario) {
        this.id = id;
        this.mensagem = mensagem;
        this.criacao = criacao;
        this.solucao = solucao;
        this.topico = topico;
        this.usuario = usuario;
    }

    public RespostaJson(RespostaJson retornoJson) {
        this(retornoJson.id(), retornoJson.mensagem(),
                retornoJson.criacao(), retornoJson.solucao(),
                retornoJson.topico(), retornoJson.usuario());
    }
}
