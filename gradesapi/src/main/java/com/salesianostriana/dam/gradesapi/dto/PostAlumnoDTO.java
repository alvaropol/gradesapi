package com.salesianostriana.dam.gradesapi.dto;

import com.salesianostriana.dam.gradesapi.modelo.Alumno;
import com.salesianostriana.dam.gradesapi.modelo.Asignatura;

import java.time.LocalDate;
import java.util.List;

public record PostAlumnoDTO(
        Long id,
        String nombre,
        String apellidos,
        String email,
        String telefono,
        LocalDate fechaNacimiento

) {

    public static PostAlumnoDTO of(Alumno a){

        return new PostAlumnoDTO(a.getId(),
                a.getNombre(),
                a.getApellidos(),
                a.getEmail(),
                a.getTelefono(),
                a.getFechaNacimiento()
        );
    }
}
