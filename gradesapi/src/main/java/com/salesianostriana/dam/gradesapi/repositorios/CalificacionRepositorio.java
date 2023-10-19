package com.salesianostriana.dam.gradesapi.repositorios;

import com.salesianostriana.dam.gradesapi.modelo.Alumno;
import com.salesianostriana.dam.gradesapi.modelo.Calificacion;
import com.salesianostriana.dam.gradesapi.modelo.Instrumento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalificacionRepositorio extends JpaRepository<Calificacion, Long> {

    List<Calificacion> findByInstrumentoId(Long idInstrumento);


}
