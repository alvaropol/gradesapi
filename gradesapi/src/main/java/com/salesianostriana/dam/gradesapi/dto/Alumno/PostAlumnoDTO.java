package com.salesianostriana.dam.gradesapi.dto.Alumno;

import com.salesianostriana.dam.gradesapi.modelo.Alumno;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PostAlumnoDTO(
        @NotNull
        Long id,
        @NotEmpty(message = "{postalumnodto.nombre.notempty}")
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
