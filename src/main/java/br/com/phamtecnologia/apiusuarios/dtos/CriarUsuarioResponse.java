package br.com.phamtecnologia.apiusuarios.dtos;

import java.time.LocalDateTime;

public record CriarUsuarioResponse(
        String mensagem,
        Integer idUsuario,
        String nome,
        String email,
        LocalDateTime dataHoraCadastro
) {
}
