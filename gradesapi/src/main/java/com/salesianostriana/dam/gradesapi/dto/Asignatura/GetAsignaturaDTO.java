package com.salesianostriana.dam.gradesapi.dto.Asignatura;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.gradesapi.modelo.Asignatura;
import com.salesianostriana.dam.gradesapi.modelo.AsignaturaView;
import com.salesianostriana.dam.gradesapi.modelo.ReferenteEvaluacion;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public record GetAsignaturaDTO(

        @JsonView({AsignaturaView.AsignaturaList01.class, AsignaturaView.AsignaturaList02.class})
        Long id,
        @JsonView({AsignaturaView.AsignaturaList01.class, AsignaturaView.AsignaturaList02.class})
        String nombre,
        @JsonView({AsignaturaView.AsignaturaList01.class, AsignaturaView.AsignaturaList02.class})
        int horas,
        @JsonView({AsignaturaView.AsignaturaList01.class, AsignaturaView.AsignaturaList02.class})
        String descripcion,
        @JsonView(AsignaturaView.AsignaturaList01.class)
        int numReferentes,

        @JsonView(AsignaturaView.AsignaturaList02.class)
        List<GetReferenteEnAsignaturaDTO> referentes

) {
    public static GetAsignaturaDTO of(Asignatura a) {

        List<ReferenteEvaluacion> referentes = a.getReferentes();
        if (referentes != null) {
            return new GetAsignaturaDTO(
                    a.getId(),
                    a.getNombre(),
                    a.getHoras(),
                    a.getDescripcion().isEmpty() ? "Sin descripción" : a.getDescripcion(),
                    a.getReferentes().size(),
                    referentes.stream()
                            .map(GetReferenteEnAsignaturaDTO::of)
                            .collect(Collectors.toList())
            );
        }else{
            return new GetAsignaturaDTO(
                    a.getId(),
                    a.getNombre(),
                    a.getHoras(),
                    a.getDescripcion().isEmpty() ? "Sin descripción" : a.getDescripcion(),
                    0,
                    Collections.emptyList()
            );
        }
    }
}
