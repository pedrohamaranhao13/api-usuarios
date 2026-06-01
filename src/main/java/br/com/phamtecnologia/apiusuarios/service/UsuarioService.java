package br.com.phamtecnologia.apiusuarios.service;

import br.com.phamtecnologia.apiusuarios.Repository.UsuarioRepository;
import br.com.phamtecnologia.apiusuarios.components.CryptoComponent;
import br.com.phamtecnologia.apiusuarios.dtos.CriarUsuarioRequest;
import br.com.phamtecnologia.apiusuarios.dtos.CriarUsuarioResponse;
import br.com.phamtecnologia.apiusuarios.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CryptoComponent cryptoComponent;

    public CriarUsuarioResponse criarUsuario(CriarUsuarioRequest request) {

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
}
