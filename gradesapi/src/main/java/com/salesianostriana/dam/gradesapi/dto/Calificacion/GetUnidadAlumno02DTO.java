package com.salesianostriana.dam.gradesapi.dto.Calificacion;

import java.util.List;

public record GetUnidadAlumno02DTO(
        Long id,
        String nombre,
        String apellidos,
        List<GetCalificacionesInstrumentoDTO> calificaciones
) {
}