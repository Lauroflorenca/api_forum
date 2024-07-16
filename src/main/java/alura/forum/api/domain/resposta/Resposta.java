package alura.forum.api.domain.resposta;

import alura.forum.api.domain.topico.Topico;
import alura.forum.api.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Table(name = "Resposta")
@Entity(name = "Resposta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Resposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensagem;

    @Column(name = "dt_criacao", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime criacao;

    private Boolean solucao;

    @ManyToOne
    @JoinColumn(name = "id_topico")
    private Topico topico;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(columnDefinition = "boolean default true")
    private boolean ativo;


    public void definirSolucao(){
        this.solucao = true;
    }

    public Resposta(RespostaDTO respostaDTO, Topico topico, Usuario usuario, LocalDateTime criacao){
        this.mensagem = respostaDTO.mensagem();
        this.criacao = criacao;
        this.topico = topico;
        this.usuario = usuario;
        this.ativo = true;
        this.solucao = false;
    }

}
