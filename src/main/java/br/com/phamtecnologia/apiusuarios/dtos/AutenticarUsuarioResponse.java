package br.com.phamtecnologia.apiusuarios.dtos;

import java.time.LocalDateTime;

public record AutenticarUsuarioResponse(
        String mensagem,
        Integer idUsuario,
        String nome,
        String email,
        LocalDateTime dataHoraAcesso,
        String token
) {
}
