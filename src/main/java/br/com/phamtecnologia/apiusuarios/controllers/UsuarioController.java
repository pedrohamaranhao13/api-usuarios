package br.com.phamtecnologia.apiusuarios.controllers;

import br.com.phamtecnologia.apiusuarios.dtos.CriarUsuarioRequest;
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
        catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
