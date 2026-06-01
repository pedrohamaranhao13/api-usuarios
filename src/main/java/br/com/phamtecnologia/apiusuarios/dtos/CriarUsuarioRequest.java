package br.com.phamtecnologia.apiusuarios.dtos;

public record CriarUsuarioRequest(
        String nome,
        String email,
        String senha
) {
}
