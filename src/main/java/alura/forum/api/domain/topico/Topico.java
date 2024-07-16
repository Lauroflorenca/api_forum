package alura.forum.api.domain.topico;

import alura.forum.api.domain.curso.Curso;
import alura.forum.api.domain.resposta.Resposta;
import alura.forum.api.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "Topico")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensagem;

    @Setter
    @Column(name = "dt_criacao", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime criacao;

    @Enumerated(EnumType.STRING)
    private SituacaoTopico st_topico;

    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Setter
    @Column(columnDefinition = "boolean default true")
    private boolean ativo;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Resposta> respostas = new ArrayList<>();

    public Topico(TopicoDTO topicoDTO) {
        this.id = topicoDTO.id();
        this.titulo = topicoDTO.titulo();
        this.mensagem = topicoDTO.mensagem();
        this.criacao = topicoDTO.criacao();
        this.st_topico = topicoDTO.st_topico();
        this.curso = topicoDTO.curso();
        this.usuario = topicoDTO.usuario();
    }

    public Topico(TopicoJson topicoJson, Curso curso, Usuario usuario) {
        this.id = topicoJson.id();
        this.titulo = topicoJson.titulo();
        this.mensagem = topicoJson.mensagem();
        this.criacao = topicoJson.criacao();
        this.st_topico = topicoJson.st_topico();
        this.curso = curso;
        this.usuario = usuario;
    }

    public Topico(Long id, DadosTopicosAtualizar jsonDados, Curso curso, Usuario usuario) {
        this.id = id;
        this.titulo = jsonDados.titulo();
        this.mensagem = jsonDados.mensagem();
        this.criacao = jsonDados.criacao();
        this.usuario = usuario;
        this.st_topico = jsonDados.st_topico();
        this.curso = curso;
    }

    public void atualizaTopico(Topico topico) {
        if (topico.getTitulo() != null) {
            this.titulo = topico.getTitulo();
        }

        if (topico.getMensagem() != null) {
            this.mensagem = topico.getMensagem();
        }

        if (topico.getCriacao() != null) {
            this.criacao = topico.getCriacao();
        }

        if (topico.getSt_topico() != null) {
            this.st_topico = topico.getSt_topico();
        }

        if (topico.getCurso() != null) {
            this.curso = topico.getCurso();
        }
    }

    public void deletaTopico() {
        this.ativo = false;
    }

    public void ativaTopico() {
        this.ativo = true;
    }

    @Override
    public String toString() {
        return "Topico{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", mensagem='" + mensagem + '\'' +
                ", criacao=" + criacao +
                ", st_topico=" + st_topico +
                ", curso=" + curso +
                ", usuario=" + usuario +
                ", ativo=" + ativo +
                ", respostas=" + respostas +
                '}';
    }
}
