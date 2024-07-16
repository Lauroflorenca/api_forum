package alura.forum.api.domain.resposta;

import alura.forum.api.domain.Categoria;
import alura.forum.api.domain.curso.Curso;
import alura.forum.api.domain.curso.CursoDTO;
import alura.forum.api.domain.topico.SituacaoTopico;
import alura.forum.api.domain.topico.Topico;
import alura.forum.api.domain.topico.TopicoDTO;
import alura.forum.api.domain.topico.TopicoRepository;
import alura.forum.api.domain.usuario.Usuario;
import alura.forum.api.domain.usuario.UsuarioDTO;
import alura.forum.api.domain.usuario.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class RespostaRepositoryTest {

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deve retornar a resposta marcada com solução")
    void obtemSolucaoTopico() {
        RespostaJson resposta = cadastraResposta();

        Optional<RespostaJson> respostaConsulta = respostaRepository.obtemSolucaoTopico(topicoRepository.findFirstByOrderByIdDesc().get().getId());
        if(respostaConsulta.isPresent()){
            assertThat(respostaConsulta.get()).isEqualTo(resposta);
        }else{
            System.out.println("Algo deu errado ao tentar obter a resposta!");
        }
    }

    private Usuario cadastraUsuario(String email, String login){
        Usuario usuario = new Usuario(new UsuarioDTO(null, "teste", email, login, "123456"));
        usuario.ativaUsuario();
        return em.persist(usuario);
    }

    private Curso cadastraCurso(){
        Curso curso = new Curso(new CursoDTO(null, "curso teste", Categoria.BACK));
        return em.persist(curso);
    }

    private Topico cadastraTopico(){
        Usuario usuario = cadastraUsuario("teste@gmail.com", "tester");
        Curso curso = cadastraCurso();
        Topico topico = new Topico(new TopicoDTO(null, "topico teste", "mensagem teste", null, SituacaoTopico.ABERTO, curso, usuario));
        topico.ativaTopico();
        return em.persist(topico);
    }

    private RespostaJson cadastraResposta(){
        Usuario usuario = cadastraUsuario("teste1@gmail.com", "tester1");
        Topico topico = cadastraTopico();
        Resposta resposta = new Resposta(new RespostaDTO(null, "solucao teste", null, true, Long.getLong("1"), Long.getLong("2"), true), topico, usuario, null);
        resposta.definirSolucao();
        em.persist(resposta);
        return new RespostaJson(resposta);
    }
}