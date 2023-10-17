package com.salesianostriana.dam.gradesapi.dto.Asignatura;

import com.salesianostriana.dam.gradesapi.modelo.ReferenteEvaluacion;

public record GetReferenteEnAsignaturaDTO(

        String codReferente,
        String descripcion
) {

    public static GetReferenteEnAsignaturaDTO of(ReferenteEvaluacion r){
        return new GetReferenteEnAsignaturaDTO(
                r.getCodReferente(),
                r.getDescripcion()
        );
    }
}
