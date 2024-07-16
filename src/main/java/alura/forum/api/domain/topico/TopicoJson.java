package alura.forum.api.domain.topico;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TopicoJson(
        Long id,
        @NotNull
        String titulo,
        @NotNull
        String mensagem,
        LocalDateTime criacao,
        @NotNull
        SituacaoTopico st_topico,
        @NotNull
        Long curso,
        @NotNull
        Long usuario,


        String cursoNome,
        String usuarioNome
) {
        public TopicoJson(Topico novoTopico) {
                this(novoTopico.getId(), novoTopico.getTitulo(),
                novoTopico.getMensagem(), novoTopico.getCriacao(), novoTopico.getSt_topico(),
                        novoTopico.getCurso().getId(), novoTopico.getUsuario().getId(),
                                novoTopico.getCurso().getNome(), novoTopico.getUsuario().getNome());
        }

        public TopicoJson(long id, String titulo, String mensagem, SituacaoTopico situacaoTopico, long curso, long usuario) {
                this(id, titulo, mensagem, null, situacaoTopico, curso, usuario , "", "" );
        }
}
