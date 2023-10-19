package com.salesianostriana.dam.gradesapi.dto.Calificacion;

import java.util.List;

public record GetAlumnoEnCalificacion02DTO(
        Long idAsignatura,
        String codReferente,
        List<GetUnidadAlumno02DTO> alumnos
) {
}