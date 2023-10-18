package com.salesianostriana.dam.gradesapi.dto.Alumno;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.gradesapi.modelo.AlumnoView;
import com.salesianostriana.dam.gradesapi.modelo.Asignatura;

public record GetAsignaturaEnAlumnoDTO(
        @JsonView({AlumnoView.AlumnoList02.class})
        Long id,
        @JsonView({AlumnoView.AlumnoList02.class})
        String nombre
)  {

    public static GetAsignaturaEnAlumnoDTO of(Asignatura a){

        return new GetAsignaturaEnAlumnoDTO(
                a.getId(),
                a.getNombre()
        );
    }
}

