package alura.forum.api.domain.topico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    public Topico cadastrarTopico(Topico topico) {

        ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();

        topico.setCriacao(localDateTime);
        topico.setAtivo(true);

        return topicoRepository.save(topico);
    }

}