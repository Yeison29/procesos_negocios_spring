package com.procesos_negocio.json.controllers;

import com.procesos_negocio.json.models.Usuario;
import com.procesos_negocio.json.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UsuarioService usuarioService;
    @PostMapping(value = "auth/login")
    public ResponseEntity login(@RequestBody Usuario usuario){
        return usuarioService.login(usuario.getCorreo(), usuario.getPassword());
    }
}
