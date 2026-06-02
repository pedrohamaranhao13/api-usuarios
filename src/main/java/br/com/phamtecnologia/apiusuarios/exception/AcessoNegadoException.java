package br.com.phamtecnologia.apiusuarios.exception;

public class AcessoNegadoException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Acesso negado. Credenciais inválidas.";
    }
}
