package com.salesianostriana.dam.gradesapi.dto.Asignatura;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.gradesapi.modelo.AsignaturaView;
import com.salesianostriana.dam.gradesapi.modelo.InstrumentoView;
import com.salesianostriana.dam.gradesapi.modelo.ReferenteEvaluacion;

public record GetReferenteEnAsignaturaDTO(

        @JsonView({AsignaturaView.AsignaturaList02.class, InstrumentoView.Instrumento03.class,InstrumentoView.Instrumento01.class})
        String codReferente,
        @JsonView({AsignaturaView.AsignaturaList02.class,InstrumentoView.Instrumento03.class,InstrumentoView.Instrumento01.class})
        String descripcion
) {

    public static GetReferenteEnAsignaturaDTO of(ReferenteEvaluacion r){
        return new GetReferenteEnAsignaturaDTO(
                r.getCodReferente(),
                r.getDescripcion()
        );
    }
}
