package com.salesianostriana.dam.gradesapi.dto;

import com.salesianostriana.dam.gradesapi.modelo.Asignatura;

public record GetAsignaturaEnAlumnoDTO(
        Long id,
        String nombre
)  {

    public static GetAsignaturaEnAlumnoDTO of(Asignatura a){

        return new GetAsignaturaEnAlumnoDTO(
                a.getId(),
                a.getNombre()
        );
    }
}

