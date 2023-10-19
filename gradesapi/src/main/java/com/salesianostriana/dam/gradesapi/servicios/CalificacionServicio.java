package com.salesianostriana.dam.gradesapi.servicios;

import com.salesianostriana.dam.gradesapi.dto.Calificacion.GetAlumnoEnCalificacionDTO;
import com.salesianostriana.dam.gradesapi.dto.Calificacion.GetCalificacionDTO;
import com.salesianostriana.dam.gradesapi.dto.Calificacion.GetUnidadAlumnoDTO;
import com.salesianostriana.dam.gradesapi.dto.Calificacion.PostCalificacionDTO;
import com.salesianostriana.dam.gradesapi.modelo.Alumno;
import com.salesianostriana.dam.gradesapi.modelo.Calificacion;
import com.salesianostriana.dam.gradesapi.modelo.Instrumento;
import com.salesianostriana.dam.gradesapi.repositorios.CalificacionRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalificacionServicio {

    private final CalificacionRepositorio repositorio;
    private final InstrumentoServicio instrumentoService;
    private final AlumnoServicio alumnoService;


    public Optional<Calificacion> findById(Long id){return repositorio.findById(id);}

    public GetCalificacionDTO addCalificaciones(PostCalificacionDTO nuevo) {
        Instrumento instrumento = instrumentoService.findById(nuevo.idInstrumento()).orElse(null);
        Alumno alumno = alumnoService.findById(nuevo.idAlumno()).orElse(null);

        if (alumno == null || instrumento == null) {
            return null;
        }

        List<Calificacion> calificaciones = nuevo.calificaciones().stream()
                .filter(calificacionDTO -> instrumento.getReferentes().stream()
                        .anyMatch(referente -> referente.getCodReferente().equals(calificacionDTO.codReferente())))
                .map(calificacionDTO -> {
                    Calificacion calificacion = new Calificacion();
                    calificacion.setInstrumento(instrumento);
                    calificacion.setAlumno(alumno);
                    calificacion.setReferente(instrumento.getReferentes().stream()
                            .filter(referente -> referente.getCodReferente().equals(calificacionDTO.codReferente()))
                            .findFirst()
                            .orElse(null));
                    calificacion.setCalificacion(calificacionDTO.calificacion());
                    repositorio.save(calificacion);
                    return calificacion;
                })
                .toList();

        return new GetCalificacionDTO(
                instrumento.getId(),
                instrumento.getNombre(),
                calificaciones.stream()
                        .map(PostCalificacionDTO.CalificacionDTO::of)
                        .collect(Collectors.toList())
        );
    }

    public GetAlumnoEnCalificacionDTO getCalificacionesByInstrumento(Long idInstrumento) {
        List<Calificacion> calificaciones = repositorio.findByInstrumentoId(idInstrumento);

        Optional<Instrumento> instrumento = instrumentoService.findById(idInstrumento);

        Instrumento ins = instrumento.get();

        List<GetUnidadAlumnoDTO> resultado = calificaciones.stream()
                .collect(Collectors.groupingBy(Calificacion::getAlumno))
                .values()
                .stream()
                .map(calificacionesAlumno -> {
                    Alumno alumno = calificacionesAlumno.get(0).getAlumno();
                    return new GetUnidadAlumnoDTO(
                            alumno.getId(),
                            alumno.getNombre(),
                            alumno.getApellidos(),
                            calificacionesAlumno.stream()
                                    .map(PostCalificacionDTO.CalificacionDTO::of)
                                    .collect(Collectors.toList())
                    );
                })
                .collect(Collectors.toList());

        return new GetAlumnoEnCalificacionDTO(
                ins.getId(),
                ins.getNombre(),
                resultado
        );
    }
}




