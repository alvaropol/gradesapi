package com.salesianostriana.dam.gradesapi.dto.Asignatura;

import com.salesianostriana.dam.gradesapi.modelo.Asignatura;

public record PostAsignaturaDTO(

        Long id,
        String nombre,
        int horas,
        String descripcion

) {

    public static PostAsignaturaDTO of (Asignatura a){
        return new PostAsignaturaDTO(
                a.getId(),
                a.getNombre(),
                a.getHoras(),
                a.getDescripcion()
        );
    }
}
