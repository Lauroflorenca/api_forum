package alura.forum.api.domain.usuario;

import alura.forum.api.domain.perfil.Perfil;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

public record UsuarioDadosDTO(Long id,
                              String nome,
                              String email,
                              String login,
                              boolean ativo,
                              Collection<? extends GrantedAuthority> perfis ){
    public UsuarioDadosDTO(Usuario novoUsuario) {
        this(novoUsuario.getId(), novoUsuario.getNome(), novoUsuario.getEmail(), novoUsuario.getLogin(), novoUsuario.isAtivo(),
                novoUsuario.getAuthorities());
    }
}
