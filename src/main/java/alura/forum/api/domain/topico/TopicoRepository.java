package alura.forum.api.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloAndMensagemAndAtivoTrue(String titulo, String mensagem);

    boolean existsByTituloAndMensagemAndIdNotAndAtivoTrue(String titulo, String mensagem, Long id);

    Page<Topico> findAllByAtivoTrue(Pageable paginacao);

    Optional<Topico> findFirstByOrderByIdDesc();
}
