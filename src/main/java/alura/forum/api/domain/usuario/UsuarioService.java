package alura.forum.api.domain.usuario;

import alura.forum.api.domain.ItemPerfilUsuario;
import alura.forum.api.domain.ValidacaoException;
import alura.forum.api.domain.perfil.Perfil;
import alura.forum.api.domain.perfil.PerfilDTO;
import alura.forum.api.domain.perfil.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario cadastrarUsuario(Usuario usuario) {
        // Criptografar a senha antes de salvar
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        usuario.setAtivo(true);

        // Add perfil ao usuario
        Perfil perfilUser = perfilRepository.findById(Long.valueOf(2)).get();
        usuario.adicionarPerfil(perfilUser);

        return usuarioRepository.save(usuario);
    }
}