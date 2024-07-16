package alura.forum.api.controller;


import alura.forum.api.domain.ValidacaoException;
import alura.forum.api.domain.curso.Curso;
import alura.forum.api.domain.resposta.Resposta;
import alura.forum.api.domain.resposta.RespostaDTO;
import alura.forum.api.domain.resposta.RespostaJson;
import alura.forum.api.domain.resposta.RespostaRepository;
import alura.forum.api.domain.topico.Topico;
import alura.forum.api.domain.topico.TopicoJson;
import alura.forum.api.domain.topico.TopicoRepository;
import alura.forum.api.domain.topico.TopicoService;
import alura.forum.api.domain.usuario.Usuario;
import alura.forum.api.domain.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestController
@RequestMapping("/respostas")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Respostas", description = "Operações relacionadas as respostas")
public class RespostaController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RespostaRepository respostaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<RespostaJson> cadastrarResposta(@RequestBody @Valid RespostaDTO respostaDTO) {

        if (respostaDTO.topico() == null || !topicoRepository.existsById(respostaDTO.topico())) {
            throw new ValidacaoException("Tópico inválido ou não encontrado!");
        }

        if (respostaDTO.usuario() == null || !usuarioRepository.existsById(respostaDTO.usuario())) {
            throw new ValidacaoException("Usuário inválido ou não encontrado!");
        }

        Topico topico = topicoRepository.getReferenceById(respostaDTO.topico());
        if (respostaDTO.mensagem() == null || respostaRepository.existsByMensagemAndTopico(respostaDTO.mensagem(), topico)) {
            throw new ValidacaoException("A resposta já existe para este tópico!");
        }

        if (topico.getSt_topico().toString().toUpperCase().equals("FECHADO")) {
            throw new ValidacaoException("Esse tópico já possui uma solução!");
        }


        Usuario usuario = usuarioRepository.findById(respostaDTO.usuario()).get();

        ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
        var criacao = zonedDateTime.toLocalDateTime();

        Resposta novaResposta = new Resposta(respostaDTO, topico, usuario, criacao);
        var retornoJson = respostaRepository.save(novaResposta);

        RespostaJson retorno = new RespostaJson(retornoJson);

        return ResponseEntity.ok(retorno);
    }


}
