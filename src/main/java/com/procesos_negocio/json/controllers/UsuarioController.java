package com.procesos_negocio.json.controllers;

import com.procesos_negocio.json.models.Usuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class UsuarioController {
    @GetMapping(value = "/usuario/{id}")
    public Usuario getUsuario(@PathVariable Long id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Yeison Danuil");
        usuario.setApellidos("Ascanio Ascanio");
        usuario.setFecha_nacimiento(new Date(2001,9,29));
        usuario.setDocumento("1005074695");
        usuario.setDireccion("KDX-105");
        usuario.setTelefono("3122092826");
        return usuario;
    }
}
