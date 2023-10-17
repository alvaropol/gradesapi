package com.salesianostriana.dam.gradesapi.dto.Asignatura;

import com.salesianostriana.dam.gradesapi.modelo.Asignatura;

public record GetAsignaturaDTO(

        Long id,
        String nombre,
        int horas,
        String descripcion,
        int numReferentes

) {

    public static GetAsignaturaDTO of(Asignatura a){
        return new GetAsignaturaDTO(
                a.getId(),
                a.getNombre(),
                a.getHoras(),
                a.getDescripcion().isEmpty() ? "Sin descripción" : a.getDescripcion(),
                a.getReferentes().size()
        );
    }
}
