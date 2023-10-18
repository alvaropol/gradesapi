package com.salesianostriana.dam.gradesapi.dto.Instrumento;

import java.time.LocalDateTime;
import java.util.List;

public record PostInstrumentoDTO(
        LocalDateTime fecha,
        String nombre,
        String contenidos,
        Long idAsignatura,
        List<String> referentes
) {

}
