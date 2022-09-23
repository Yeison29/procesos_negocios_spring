package com.procesos_negocio.json.controllers;

import com.procesos_negocio.json.models.Usuario;
import com.procesos_negocio.json.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping(value = "/usuario/{id}")
    public Optional<Usuario> getUsuario(@PathVariable Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        return usuario;
    }

    @PostMapping("/usuario")
    public Usuario  crearUsario(@RequestBody Usuario usuario){
        usuarioRepository.save(usuario);
        return usuario;
    }
    @GetMapping("/usuarios")
    public List<Usuario> listarUsuario(){
        return usuarioRepository.findAll();
    }
    @GetMapping("/usuarios/{nombre}/{apellidos}")
    public List<Usuario> listarPorNombreApellidos(@PathVariable String nombre,@PathVariable String apellidos){
        return usuarioRepository.findAllByNombreAndApellidos(nombre, apellidos);
    }
    @GetMapping("/usuarios/{apellidos}")
    public List<Usuario> listarPorApellidos(@PathVariable String apellidos){
        return usuarioRepository.findAllByApellidos(apellidos);
    }
}
