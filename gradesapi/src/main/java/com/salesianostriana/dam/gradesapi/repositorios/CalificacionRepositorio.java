package com.salesianostriana.dam.gradesapi.repositorios;

import com.salesianostriana.dam.gradesapi.modelo.Alumno;
import com.salesianostriana.dam.gradesapi.modelo.Calificacion;
import com.salesianostriana.dam.gradesapi.modelo.Instrumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CalificacionRepositorio extends JpaRepository<Calificacion, Long> {
    List<Calificacion> findByInstrumentoId(Long idInstrumento);
    List<Calificacion> findByAlumnoId(Long idAlumno);

    @Query("SELECT c FROM Calificacion c " +
            "WHERE c.instrumento.id = :idAsignatura " +
            "AND c.referente.codReferente = :codReferente")
    List<Calificacion> findByInstrumentoReferentesCodReferente(@Param("idAsignatura") Long idAsignatura, @Param("codReferente") String codReferente);


}
