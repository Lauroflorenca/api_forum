package alura.forum.api.domain.perfil;


import alura.forum.api.domain.usuario.Usuario;
import lombok.*;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Table(name = "Perfil")
@Entity(name = "Perfil")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Setter
    @ManyToMany(mappedBy = "perfis",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Set<Usuario> usuarios = new HashSet<>();


    public void adicionarUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
        usuario.getPerfis().add(this); // Adiciona o idioma atual à lista de idiomas do livro
    }

}


