package com.procesos_negocio.json.controllers;

import com.procesos_negocio.json.models.Usuario;
import com.procesos_negocio.json.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping(value = "/usuario/{id}")
    public ResponseEntity getUsuario(@PathVariable Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isPresent()){
            return new ResponseEntity(usuario, HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/usuario")
    public ResponseEntity  crearUsario(@RequestBody Usuario usuario){
        try{
            usuarioRepository.save(usuario);
            return new ResponseEntity(usuario,HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/usuarios")
    public ResponseEntity listarUsuario(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        if(usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(usuarios,HttpStatus.OK);
    }
    @GetMapping("/usuarios/{nombre}/{apellidos}")
    public ResponseEntity listarPorNombreApellidos(@PathVariable String nombre,@PathVariable String apellidos){
        List<Usuario> usuarios = usuarioRepository.findAllByNombreAndApellidos(nombre, apellidos);
        if(usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(usuarios,HttpStatus.OK);
    }
    @GetMapping("/usuarios/{apellidos}")
    public ResponseEntity listarPorApellidos(@PathVariable String apellidos){
        List<Usuario> usuarios = usuarioRepository.findAllByApellidos(apellidos);
        if(usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(usuarios,HttpStatus.OK);
    }
    @PutMapping("/usuario/{id}")
    public ResponseEntity editarUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        Usuario usuarioBD =  usuarioRepository.findById(id).get();
        try {
            usuarioBD.setNombre(usuario.getNombre());
            usuarioBD.setApellidos(usuario.getApellidos());
            usuarioBD.setDireccion(usuario.getDireccion());
            usuarioBD.setDocumento(usuario.getDocumento());
            usuarioBD.setFecha_nacimiento(usuario.getFecha_nacimiento());
            usuarioBD.setTelefono(usuario.getTelefono());
            usuarioRepository.save(usuarioBD);
            return new ResponseEntity(usuarioBD, HttpStatus.OK);
        }catch (Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable Long id){
        Optional<Usuario> usuarioBD = usuarioRepository.findById(id);
        if(usuarioBD.isPresent()) {
            usuarioRepository.delete(usuarioBD.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
