package com.salesianostriana.dam.gradesapi.dto.Calificacion;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.gradesapi.modelo.Calificacion;
import com.salesianostriana.dam.gradesapi.modelo.CalificacionView;

import java.util.List;

public record PostCalificacionDTO(


        Long idInstrumento,

        Long idAlumno,

        List<CalificacionDTO> calificaciones
) {


        public record CalificacionDTO(
                @JsonView(CalificacionView.PostCalificacion.class)
                Long id,
                @JsonView(CalificacionView.PostCalificacion.class)
                String codReferente,
                @JsonView(CalificacionView.PostCalificacion.class)
                String descripcion,
                @JsonView(CalificacionView.PostCalificacion.class)
                double calificacion
        ) {

                public static CalificacionDTO of(Calificacion calificacion){
                        return new CalificacionDTO(
                                calificacion.getId(),
                                calificacion.getReferente().getCodReferente(),
                                calificacion.getReferente().getDescripcion(),
                                calificacion.getCalificacion()
                        );
                }

        }


}
