package com.procesos_negocio.json.repository;

import com.procesos_negocio.json.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
