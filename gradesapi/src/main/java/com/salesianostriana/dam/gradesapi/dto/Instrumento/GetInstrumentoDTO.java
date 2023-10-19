package com.salesianostriana.dam.gradesapi.dto.Instrumento;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.gradesapi.dto.Asignatura.GetReferenteEnAsignaturaDTO;
import com.salesianostriana.dam.gradesapi.modelo.*;

import java.time.LocalDateTime;
import java.util.List;

public record GetInstrumentoDTO(

        @JsonView({InstrumentoView.Instrumento01.class,InstrumentoView.Instrumento02.class, InstrumentoView.Instrumento03.class})
        Long id,

        @JsonView({InstrumentoView.Instrumento01.class,InstrumentoView.Instrumento02.class, InstrumentoView.Instrumento03.class})
        LocalDateTime fecha,

        @JsonView({InstrumentoView.Instrumento01.class,InstrumentoView.Instrumento02.class, InstrumentoView.Instrumento03.class})
        String nombre,

        @JsonView({InstrumentoView.Instrumento01.class,InstrumentoView.Instrumento02.class, InstrumentoView.Instrumento03.class})
        String contenidos,

        @JsonView(InstrumentoView.Instrumento01.class)
        Long idAsignatura,

        @JsonView(InstrumentoView.Instrumento03.class)
        GetAsignaturaEnInstrumentoDTO asignatura,

        @JsonView({InstrumentoView.Instrumento03.class,InstrumentoView.Instrumento01.class})
        List<GetReferenteEnAsignaturaDTO> referentes,

        @JsonView(InstrumentoView.Instrumento02.class)
        int numeroReferentes

) {

    public static GetInstrumentoDTO of(Instrumento i){
        return new GetInstrumentoDTO(
                i.getId(),
                i.getFecha(),
                i.getNombre(),
                i.getContenidos().isEmpty() ? "Sin contenidos" : i.getContenidos(),
                i.getAsignatura().getId(),
                GetAsignaturaEnInstrumentoDTO.of(i.getAsignatura()),
                i.getReferentes().stream()
                        .map(r -> new GetReferenteEnAsignaturaDTO(
                                r.getCodReferente(),
                                r.getDescripcion()
                        )).toList(),
                i.getReferentes().size()
        );
    }
}
