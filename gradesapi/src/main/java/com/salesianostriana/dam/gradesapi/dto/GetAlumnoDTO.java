package com.salesianostriana.dam.gradesapi.dto;

import com.salesianostriana.dam.gradesapi.modelo.Alumno;

import java.time.LocalDate;

public record GetAlumnoDTO(

        Long id,
        String nombre,
        String apellidos,
        String email,
        String telefono,
        LocalDate fechaNacimiento,
        int cantidadAsignaturas


) {

    public static GetAlumnoDTO of (Alumno a){
           return new GetAlumnoDTO(
                   a.getId(),
                   a.getNombre(),
                   a.getApellidos(),
                   a.getEmail(),
                   a.getTelefono(),
                   a.getFechaNacimiento(),
                   a.getAsignaturas().size()
           );
    }
}
