package br.com.phamtecnologia.apiusuarios.service;

import br.com.phamtecnologia.apiusuarios.Repository.UsuarioRepository;
import br.com.phamtecnologia.apiusuarios.components.CryptoComponent;
import br.com.phamtecnologia.apiusuarios.components.JwtComponent;
import br.com.phamtecnologia.apiusuarios.dtos.AutenticarUsuarioRequest;
import br.com.phamtecnologia.apiusuarios.dtos.AutenticarUsuarioResponse;
import br.com.phamtecnologia.apiusuarios.dtos.CriarUsuarioRequest;
import br.com.phamtecnologia.apiusuarios.dtos.CriarUsuarioResponse;
import br.com.phamtecnologia.apiusuarios.entities.Usuario;
import br.com.phamtecnologia.apiusuarios.exception.AcessoNegadoException;
import br.com.phamtecnologia.apiusuarios.exception.EmailJaCadastradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CryptoComponent cryptoComponent;

    @Autowired
    private JwtComponent jwtComponent;

    public CriarUsuarioResponse criarUsuario(CriarUsuarioRequest request) {

        var usuarioExistente = usuarioRepository.findByEmail(request.email());
        if (usuarioExistente != null) {
            throw new EmailJaCadastradoException();
        }

        var usuario = new Usuario();

        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(cryptoComponent.getSha256(request.senha()));
        usuario.setDataHoraCriacao(LocalDateTime.now());

        usuarioRepository.save(usuario);

        return new CriarUsuarioResponse(
                "Usuário cadastrado com sucesso",
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getDataHoraCriacao()
        );

    }

    public AutenticarUsuarioResponse autenticarUsuario(AutenticarUsuarioRequest request) {

        var senhaCriptografada = cryptoComponent.getSha256(request.senha());

        var usuario = usuarioRepository.findByEmailAndSenha(request.email(), senhaCriptografada);

        if (usuario == null) {
            throw new AcessoNegadoException();
        }

        return new AutenticarUsuarioResponse(
            "Usuário autenticado com sucesso",
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            LocalDateTime.now(),
                jwtComponent.gerarToken(
                        usuario.getId().longValue(),
                        usuario.getNome(),
                        usuario.getEmail()
                )
        );
    }
}
