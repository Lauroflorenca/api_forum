package alura.forum.api.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UsuarioDTO(
        Long id,
        @NotNull
        String nome,
        @NotNull
        @Email
        String email,
        @NotNull
        String login,
        @NotNull
        String senha
) { }
