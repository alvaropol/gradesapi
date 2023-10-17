package com.salesianostriana.dam.gradesapi.dto.Alumno;

import com.salesianostriana.dam.gradesapi.modelo.Alumno;

import java.time.LocalDate;

public record PostAlumnoDTO(
        Long id,
        String nombre,
        String apellidos,
        String email,
        String telefono,
        LocalDate fechaNacimiento

) {

    public static PostAlumnoDTO of(Alumno a){

        return new PostAlumnoDTO(
                a.getId(),
                a.getNombre(),
                a.getApellidos(),
                a.getEmail(),
                a.getTelefono(),
                a.getFechaNacimiento()
        );
    }
}
