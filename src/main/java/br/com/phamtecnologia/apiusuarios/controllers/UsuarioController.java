package br.com.phamtecnologia.apiusuarios.controllers;

import br.com.phamtecnologia.apiusuarios.dtos.AutenticarUsuarioRequest;
import br.com.phamtecnologia.apiusuarios.dtos.CriarUsuarioRequest;
import br.com.phamtecnologia.apiusuarios.exception.AcessoNegadoException;
import br.com.phamtecnologia.apiusuarios.exception.EmailJaCadastradoException;
import br.com.phamtecnologia.apiusuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("criar")
    public ResponseEntity<?> criar(@RequestBody CriarUsuarioRequest request) {

        try {
            var response = usuarioService.criarUsuario(request);
            return ResponseEntity.status(201).body(response);
        }
        catch (EmailJaCadastradoException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("autenticar")
    public ResponseEntity<?> autenticar(@RequestBody AutenticarUsuarioRequest request) {

        try {
            var response = usuarioService.autenticarUsuario(request);

            return ResponseEntity.status(200).body(response);
        }
        catch (AcessoNegadoException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }

    }

}
