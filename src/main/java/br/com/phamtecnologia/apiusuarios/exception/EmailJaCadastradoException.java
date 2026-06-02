package br.com.phamtecnologia.apiusuarios.exception;

public class EmailJaCadastradoException extends RuntimeException {

    @Override
    public String getMessage() {
        return "O e-mail informado já está cadastrado. Tente outro.";
    }
}
