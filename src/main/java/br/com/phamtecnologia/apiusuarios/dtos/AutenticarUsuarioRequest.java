package br.com.phamtecnologia.apiusuarios.dtos;

public record AutenticarUsuarioRequest(
        String email,
        String senha
) {
}
