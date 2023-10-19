package com.salesianostriana.dam.gradesapi.dto.Calificacion;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.gradesapi.modelo.CalificacionView;

import java.util.List;

public record GetUnidadAlumnoDTO(

        @JsonView(CalificacionView.CalificacionAlumno.class)
        Long id,
        @JsonView(CalificacionView.CalificacionAlumno.class)
        String nombre,
        @JsonView(CalificacionView.CalificacionAlumno.class)
        String apellidos,
        @JsonView(CalificacionView.CalificacionAlumno.class)
        List<PostCalificacionDTO.CalificacionDTO> calificaciones
) {
}
