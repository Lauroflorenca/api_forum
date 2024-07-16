package alura.forum.api.domain.usuario;


import alura.forum.api.domain.ItemPerfilUsuario;
import alura.forum.api.domain.perfil.Perfil;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Table(name = "usuario")
@Entity(name = "usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String login;
    @Setter
    private String senha;
    @Setter
    @Column(columnDefinition = "boolean default true")
    private boolean ativo;

    public void ativaUsuario(){
        this.ativo = true;
    }

    @Setter
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Item_Perfil_Usuario",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_perfil"))
    private Set<Perfil> perfis = new HashSet<>();


    public void adicionarPerfil(Perfil perfil) {
        this.perfis.add(perfil);
        perfil.getUsuarios().add(this); // Para manter a consistência bidirecional
    }


    public Usuario(UsuarioDTO userDTO) {
        this.id = userDTO.id();
        this.nome = userDTO.nome();
        this.email = userDTO.email();
        this.login = userDTO.login();
        this.senha = userDTO.senha();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return perfis.stream()
                .map(itemPerfil -> new SimpleGrantedAuthority(itemPerfil.getNome()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return ativo;
    }


    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", ativo=" + ativo +
                ", perfis=" + perfis +
                '}';
    }
}
