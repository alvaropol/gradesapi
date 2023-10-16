package com.salesianostriana.dam.gradesapi.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.gradesapi.modelo.Alumno;
import com.salesianostriana.dam.gradesapi.modelo.AlumnoView;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record GetAlumnoDTO(

    @JsonView({AlumnoView.AlumnoList01.class, AlumnoView.AlumnoList02.class})
        Long id,
    @JsonView({AlumnoView.AlumnoList01.class, AlumnoView.AlumnoList02.class})
        String nombre,
    @JsonView({AlumnoView.AlumnoList01.class, AlumnoView.AlumnoList02.class})
        String apellidos,
    @JsonView({AlumnoView.AlumnoList01.class, AlumnoView.AlumnoList02.class})
        String email,
    @JsonView({AlumnoView.AlumnoList01.class, AlumnoView.AlumnoList02.class})
        String telefono,
    @JsonView({AlumnoView.AlumnoList01.class, AlumnoView.AlumnoList02.class})
        LocalDate fechaNacimiento,

    @JsonView({AlumnoView.AlumnoList01.class})
    int cantidadAsignaturas,

    @JsonView({AlumnoView.AlumnoList02.class})
    List<GetAsignaturaEnAlumnoDTO> asignaturas



) {

    public static GetAlumnoDTO of (Alumno a){
           return new GetAlumnoDTO(
                   a.getId(),
                   a.getNombre(),
                   a.getApellidos(),
                   a.getEmail(),
                   a.getTelefono(),
                   a.getFechaNacimiento(),
                   a.getAsignaturas().size(),
                   a.getAsignaturas().stream()
                           .map(GetAsignaturaEnAlumnoDTO::of)
                           .collect(Collectors.toList())

           );
    }
}
