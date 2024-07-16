package alura.forum.api.controller;

import alura.forum.api.domain.ValidacaoException;
import alura.forum.api.domain.curso.Curso;
import alura.forum.api.domain.curso.CursoRepository;
import alura.forum.api.domain.resposta.Resposta;
import alura.forum.api.domain.resposta.RespostaJson;
import alura.forum.api.domain.resposta.RespostaRepository;
import alura.forum.api.domain.topico.*;
import alura.forum.api.domain.usuario.Usuario;
import alura.forum.api.domain.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Tópicos", description = "Operações relacionadas aos tópicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private RespostaRepository respostaRepository;


    @PostMapping
    @Transactional
    public ResponseEntity<TopicoJson> cadastrarTopico(@RequestBody @Valid TopicoJson topicoJson) {
        System.out.println(topicoJson.curso());

        if (topicoJson.curso() == null || !cursoRepository.existsById(topicoJson.curso())) {
            throw new ValidacaoException("Curso inválido ou não encontrado!");
        }

        if (topicoJson.usuario() == null || !usuarioRepository.existsById(topicoJson.usuario())) {
            throw new ValidacaoException("Usuário inválido ou não encontrado!");
        }

        // Verifica se não existe titulo e mensagem no banco
        if ( (topicoJson.titulo() != null && topicoJson.mensagem() != null )
                && topicoRepository.existsByTituloAndMensagemAndAtivoTrue(topicoJson.titulo(), topicoJson.mensagem())) {
            throw new ValidacaoException("O tópico já existe!");
        }

        Curso curso = cursoRepository.findById(topicoJson.curso()).get();
        Usuario usuario = usuarioRepository.findById(topicoJson.usuario()).get();
        
        Topico topico = new Topico(topicoJson, curso, usuario);
        Topico novoTopico = topicoService.cadastrarTopico(topico);

        TopicoJson retorno = new TopicoJson(novoTopico);

        return ResponseEntity.ok(retorno);
    }


    @GetMapping
    public Page<DadosTopicosListagem> listar(@PageableDefault(size = 10, sort = {"criacao"}, direction = Sort.Direction.ASC) Pageable paginacao) {
        return topicoRepository.findAllByAtivoTrue(paginacao).map(DadosTopicosListagem::new);
    }

    @PutMapping("/{id}")
    @Transactional
    public void atualizar(@PathVariable Long id, @RequestBody @Valid DadosTopicosAtualizar jsonDados) {

        if (id == null || !topicoRepository.existsById(id)) {
            throw new ValidacaoException("Tópico inválido ou não encontrado!");
        }

        if (jsonDados.curso() == null || !cursoRepository.existsById(jsonDados.curso())) {
            throw new ValidacaoException("Curso inválido ou não encontrado!");
        }

        String situacaoTopico = String.valueOf(jsonDados.st_topico());
        if (situacaoTopico == null || !SituacaoTopico.isValid(situacaoTopico)) {
            throw new ValidacaoException("Situação tópico inválido ou não encontrado!");
        }

        if ((jsonDados.titulo() != null && jsonDados.mensagem() != null) &&
                topicoRepository.existsByTituloAndMensagemAndIdNotAndAtivoTrue(jsonDados.titulo(), jsonDados.mensagem(), id)) {
            throw new ValidacaoException("O tópico já existe!");
        }

        //DADOS DO JSON
        Curso curso = cursoRepository.findById(jsonDados.curso()).get();
        //DADOS DO BANCO
        Topico topico = topicoRepository.getReferenceById(id);

        Topico topicoAtualizar = new Topico(id, jsonDados, curso, topico.getUsuario());
        topico.atualizaTopico(topicoAtualizar);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleta(@PathVariable Long id) {

        if (id == null || !topicoRepository.existsById(id)) {
            throw new ValidacaoException("Tópico inválido ou não encontrado!");
        }

        //DADOS DO BANCO
        Topico topico = topicoRepository.getReferenceById(id);
        topico.deletaTopico();
    }


    @GetMapping("/{id}")
    public ResponseEntity<DadosTopicosDetalhamento> detalhamento(@PathVariable Long id) {

        if (id == null || !topicoRepository.existsById(id)) {
            throw new ValidacaoException("Tópico inválido ou não encontrado!");
        }

        //SOLUCAO
        Optional<RespostaJson> solucao = respostaRepository.obtemSolucaoTopico(id);
        if(solucao.isPresent()){
            //TOPICO
            DadosTopicosDetalhamento retorno = new DadosTopicosDetalhamento(topicoRepository.getReferenceById(id), solucao.get());

            //RESPOSTAS
            retorno.respostas().addAll(respostaRepository.obtemRespostasTopico(id).stream().map(RespostaJson::new).toList());

            return ResponseEntity.ok(retorno);
        }else{

            //TOPICO
            DadosTopicosDetalhamento retorno = new DadosTopicosDetalhamento(topicoRepository.getReferenceById(id), new RespostaJson(new Resposta()));

            //RESPOSTAS
            retorno.respostas().addAll(respostaRepository.obtemRespostasTopico(id).stream().map(RespostaJson::new).toList());

            return ResponseEntity.ok(retorno);
        }

    }

}