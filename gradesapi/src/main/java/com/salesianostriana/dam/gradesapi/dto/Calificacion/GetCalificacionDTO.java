package com.salesianostriana.dam.gradesapi.dto.Calificacion;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.gradesapi.modelo.Calificacion;
import com.salesianostriana.dam.gradesapi.modelo.CalificacionView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record GetCalificacionDTO(
        @JsonView(CalificacionView.PostCalificacion.class)
        Long idInstrumento,
        @JsonView(CalificacionView.PostCalificacion.class)
        String nombre,
        @JsonView(CalificacionView.PostCalificacion.class)
        List<PostCalificacionDTO.CalificacionDTO> calificaciones



) {


    public static GetCalificacionDTO of(Calificacion calificacion) {
        return new GetCalificacionDTO(
                calificacion.getInstrumento().getId(),
                calificacion.getInstrumento().getNombre(),
                calificacion.getInstrumento().getReferentes().stream()
                        .filter(referenteEvaluacion -> referenteEvaluacion.getCodReferente().equals(calificacion.getReferente().getCodReferente()))
                        .map(referenteEvaluacion -> PostCalificacionDTO.CalificacionDTO.of(calificacion))
                        .collect(Collectors.toList())
        );
    }

}