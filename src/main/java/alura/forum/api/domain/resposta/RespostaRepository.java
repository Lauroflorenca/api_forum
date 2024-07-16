package alura.forum.api.domain.resposta;

import alura.forum.api.domain.topico.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {

    boolean existsByMensagemAndTopico(String mensagem, Topico topico);

    @Query("""
            SELECT r
                FROM Resposta r
                    WHERE r.ativo = true AND r.topico.id = :id AND r.solucao = false
                        ORDER BY r.criacao DESC
            """)
    List<Resposta> obtemRespostasTopico(@Param("id") Long id);

    @Query("""
            SELECT new alura.forum.api.domain.resposta.RespostaJson(
                    r.id,
                    r.mensagem,
                    r.criacao,
                    r.solucao,
                    t.titulo,
                    u.nome
                )
                FROM Resposta r
                JOIN r.topico t
                JOIN r.usuario u
                WHERE
                    r.ativo = true AND r.topico.id = :id AND r.solucao = true AND
                    t.ativo = true AND u.ativo = true
                ORDER BY r.criacao DESC
            """)
    Optional<RespostaJson> obtemSolucaoTopico(@Param("id") Long id);
}
