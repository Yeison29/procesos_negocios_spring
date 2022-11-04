package com.procesos_negocio.json.controllers;

import com.procesos_negocio.json.models.Usuario;

import com.procesos_negocio.json.service.UsuarioService;

import com.procesos_negocio.json.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping(value = "/usuario/{id}")
    public ResponseEntity getUsuario(@PathVariable Long id, @RequestHeader(value = "Authorization") String token){
        if(jwtUtil.getKey(token) == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("TOKEN NO VALLIDO");
        }
        return usuarioService.getUserById(id);
    }

    @PostMapping("/usuario")
    public ResponseEntity  crearUsario(@Valid @RequestBody Usuario usuario){
        return usuarioService.createUser(usuario);
    }
    @GetMapping("/usuarios")
    public ResponseEntity listarUsuario(@RequestHeader(value = "Authorization") String token){
        if(jwtUtil.getKey(token) == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("TOKEN NO VALLIDO");
        }
        return usuarioService.allUsers();
    }
    @GetMapping("/usuarios/{nombre}/{apellidos}")
    public ResponseEntity listarPorNombreApellidos(@PathVariable String nombre,@PathVariable String apellidos,
                                                   @RequestHeader(value = "Authorization") String token){
        if(jwtUtil.getKey(token) == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("TOKEN NO VALLIDO");
        }
        return usuarioService.allUsersByNameAndLastname(nombre,apellidos);
    }
    @GetMapping("/usuarios/apellidos/{apellidos}")
    public ResponseEntity listarPorApellidos(@PathVariable String apellidos,
                                             @RequestHeader(value = "Authorization") String token){
        if(jwtUtil.getKey(token) == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("TOKEN NO VALLIDO");
        }
        return usuarioService.allUsersByLastname(apellidos);
    }
    @GetMapping("/usuarios/nombre/{nombre}")
    public ResponseEntity listarPorNombre(@PathVariable String nombre,
                                          @RequestHeader(value = "Authorization") String token) {
        if(jwtUtil.getKey(token) == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("TOKEN NO VALLIDO");
        }
        return usuarioService.allUsersByName(nombre);
    }
    @PutMapping("/usuario/{id}")
    public ResponseEntity editarUsuario(@PathVariable Long id, @Valid @RequestBody Usuario usuario,
                                        @RequestHeader(value = "Authorization") String token){
        if(jwtUtil.getKey(token) == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("TOKEN NO VALLIDO");
        }
        return usuarioService.editUser(id, usuario);
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable Long id,
                                          @RequestHeader(value = "Authorization") String token){
        if(jwtUtil.getKey(token) == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("TOKEN NO VALLIDO");
        }
        return usuarioService.deleteUser(id);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
