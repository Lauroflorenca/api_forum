package alura.forum.api.domain.topico;

import alura.forum.api.domain.resposta.Resposta;
import alura.forum.api.domain.resposta.RespostaJson;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record DadosTopicosDetalhamento(Long id,
                                       String titulo,
                                       String mensagem,
                                       LocalDateTime criacao,
                                       String st_topico,
                                       String curso,
                                       String usuario,
                                       List<RespostaJson> respostas,
                                       RespostaJson solucao
) {

    public DadosTopicosDetalhamento(Topico novoTopico, RespostaJson solucaoCont) {
        this(
                novoTopico.getId(),
                novoTopico.getTitulo(),
                novoTopico.getMensagem(),
                novoTopico.getCriacao(),
                novoTopico.getSt_topico().name(),
                novoTopico.getCurso().getNome(),
                novoTopico.getUsuario().getNome(), new ArrayList<>(), solucaoCont);
    }
}