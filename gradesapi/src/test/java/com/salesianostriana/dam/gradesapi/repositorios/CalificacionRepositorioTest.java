package com.salesianostriana.dam.gradesapi.repositorios;

import com.salesianostriana.dam.gradesapi.modelo.Calificacion;
import com.salesianostriana.dam.gradesapi.modelo.Instrumento;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CalificacionRepositorioTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    CalificacionRepositorio repo;

    @Test
    void findByInstrumentoId(){

        Instrumento i = entityManager.persist(Instrumento.builder().nombre("Instrumento 1").build());

        entityManager.persist(Calificacion.builder()
                        .calificacion(9.5)
                        .instrumento(i)
                        .build());

        List<Calificacion> result = repo.findByInstrumentoId(1L);

        assertEquals(1, result.size());
        assertEquals(9.5, result.get(0).getCalificacion());
        assertEquals("Instrumento 1", result.get(0).getInstrumento().getNombre());
    }

}