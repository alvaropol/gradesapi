package com.salesianostriana.dam.gradesapi.dto.Asignatura;

import com.salesianostriana.dam.gradesapi.modelo.Asignatura;
import com.salesianostriana.dam.gradesapi.modelo.ReferenteEvaluacion;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public record PostAsignaturaDTO(

        Long id,
        String nombre,

        int horas,
        String descripcion,

        List<GetReferenteEnAsignaturaDTO> referentes

) {

    public static PostAsignaturaDTO of (Asignatura a){

        List<ReferenteEvaluacion> referentes = a.getReferentes();

        if (referentes != null) {
            return new PostAsignaturaDTO(
                    a.getId(),
                    a.getNombre(),
                    a.getHoras(),
                    a.getDescripcion().isEmpty() ? "Sin descripción" : a.getDescripcion(),
                    referentes.stream()
                            .map(GetReferenteEnAsignaturaDTO::of)
                            .collect(Collectors.toList())
            );
        } else {
            return new PostAsignaturaDTO(
                    a.getId(),
                    a.getNombre(),
                    a.getHoras(),
                    a.getDescripcion().isEmpty() ? "Sin descripción" : a.getDescripcion(),
                    Collections.emptyList()
            );
        }
    }
}
