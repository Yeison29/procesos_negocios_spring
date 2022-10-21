package com.procesos_negocio.json.controllers;

import com.procesos_negocio.json.models.Usuario;

import com.procesos_negocio.json.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/usuario/{id}")
    public ResponseEntity getUsuario(@PathVariable Long id){
        return usuarioService.getUserById(id);
    }

    @PostMapping("/usuario")
    public ResponseEntity  crearUsario(@RequestBody Usuario usuario){
        return usuarioService.createUser(usuario);
    }
    @GetMapping("/usuarios")
    public ResponseEntity listarUsuario(){
        return usuarioService.allUsers();
    }
    @GetMapping("/usuarios/{nombre}/{apellidos}")
    public ResponseEntity listarPorNombreApellidos(@PathVariable String nombre,@PathVariable String apellidos){
        return usuarioService.allUsersByNameAndLastname(nombre,apellidos);
    }
    @GetMapping("/usuarios/apellidos/{apellidos}")
    public ResponseEntity listarPorApellidos(@PathVariable String apellidos){
        return usuarioService.allUsersByLastname(apellidos);
    }
    @GetMapping("/usuarios/nombre/{nombre}")
    public ResponseEntity listarPorNombre(@PathVariable String nombre) {
        return usuarioService.allUsersByName(nombre);
    }
    @PutMapping("/usuario/{id}")
    public ResponseEntity editarUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
    return usuarioService.editUser(id, usuario);
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable Long id){
    return usuarioService.deleteUser(id);
    }
}
