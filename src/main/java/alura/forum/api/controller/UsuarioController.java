package alura.forum.api.controller;

import alura.forum.api.domain.ValidacaoException;
import alura.forum.api.domain.usuario.*;
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

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Usuário", description = "Operações relacionadas aos usuários")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioDadosDTO> cadastrarUsuario(@RequestBody @Valid UsuarioDTO usuarioDto) {
        if (usuarioDto.login() == null || usuarioRepository.existsByLogin(usuarioDto.login())) {
            throw new ValidacaoException("Username não encontrado ou já em uso!");
        }

        Usuario novoUsuario = usuarioService.cadastrarUsuario(new Usuario(usuarioDto));
        UsuarioDadosDTO novoUser = new UsuarioDadosDTO(novoUsuario);

        return ResponseEntity.ok(novoUser);
    }
}
