package com.salesianostriana.dam.gradesapi.dto.Instrumento;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.gradesapi.modelo.Asignatura;
import com.salesianostriana.dam.gradesapi.modelo.InstrumentoView;

public record GetAsignaturaEnInstrumentoDTO(

        @JsonView(InstrumentoView.Instrumento03.class)
        Long id,
        @JsonView(InstrumentoView.Instrumento03.class)
        String nombre
) {

    public static GetAsignaturaEnInstrumentoDTO of(Asignatura a){
        return new GetAsignaturaEnInstrumentoDTO(
                a.getId(),
                a.getNombre()
        );
    }
}
