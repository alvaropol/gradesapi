package com.salesianostriana.dam.gradesapi.dto.Instrumento;

import com.salesianostriana.dam.gradesapi.modelo.Asignatura;

public record GetAsignaturaEnInstrumentoDTO(

        Long id,
        String nombre
) {

    public static GetAsignaturaEnInstrumentoDTO of(Asignatura a){
        return new GetAsignaturaEnInstrumentoDTO(
                a.getId(),
                a.getNombre()
        );
    }
}
