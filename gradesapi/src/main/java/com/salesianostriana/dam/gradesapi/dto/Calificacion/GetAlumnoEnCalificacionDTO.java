package com.salesianostriana.dam.gradesapi.dto.Calificacion;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.gradesapi.modelo.CalificacionView;

import java.util.List;

public record GetAlumnoEnCalificacionDTO(

        @JsonView(CalificacionView.CalificacionAlumno.class)
        Long idInstrumento,
        @JsonView(CalificacionView.CalificacionAlumno.class)
        String nombre,
        @JsonView(CalificacionView.CalificacionAlumno.class)
        List<GetUnidadAlumnoDTO> alumnos
) {
}
