package com.procesos_negocio.json.service;

import com.procesos_negocio.json.models.Usuario;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UsuarioService {
    ResponseEntity<Usuario> getUserById(Long Id);
    ResponseEntity <Usuario> createUser(Usuario usuario);
    ResponseEntity <List<Usuario>> allUsers();
    ResponseEntity <List<Usuario>> allUsersByNameAndLastname(String nombre, String apellidos);
    ResponseEntity <List<Usuario>> allUsersByLastname(String apellidos);
    ResponseEntity <List<Usuario>> allUsersByName(String nombre);
    ResponseEntity <Usuario> editUser(Long Id, Usuario usuario);
    ResponseEntity <Usuario> deleteUser(Long Id);
    ResponseEntity login(String correo, String password);
}
