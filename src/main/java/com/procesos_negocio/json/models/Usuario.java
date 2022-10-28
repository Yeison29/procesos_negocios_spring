package com.procesos_negocio.json.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
@Data
@NoArgsConstructor
@Entity
@Table(name = "usuarios")

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    @NotBlank(message = "El campo del nombre no debe estar vacio")
    private String  nombre;
    @Column(length = 300, nullable = false)
    private String apellidos;
    @Column(length = 20, nullable = false, unique = true)
    @NotBlank(message = "El documento ya se encuentra registrado en la base de datos")
    private String documento;
    @Column(length = 100)
    private String direccion;
    private Date fecha_nacimiento;
    @Column(length = 20)
    private String telefono;
    @Column(unique = true, length = 100, nullable = false)
    @NotBlank(message = "El campo el correo no debe estar vacio")
    private String correo;
    @NotBlank(message = "El campo la contraseña no debe estar vacio")
    @Column(length = 64, nullable = false)
    private String password;
}
