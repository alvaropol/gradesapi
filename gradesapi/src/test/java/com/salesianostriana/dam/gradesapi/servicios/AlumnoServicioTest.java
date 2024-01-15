package com.salesianostriana.dam.gradesapi.servicios;

import com.salesianostriana.dam.gradesapi.modelo.Alumno;
import com.salesianostriana.dam.gradesapi.modelo.Asignatura;
import com.salesianostriana.dam.gradesapi.repositorios.AlumnoRepositorio;
import com.salesianostriana.dam.gradesapi.repositorios.AsignaturaRepositorio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlumnoServicioTest {

    @Mock
    private AlumnoRepositorio repo;
    @Mock
    private AsignaturaRepositorio repoAsignatura;
    @InjectMocks
    private AlumnoServicio service;
    @Test
    void addAsignatura() {
        Asignatura asignatura1 = new Asignatura(1L, "Matematicas", 200, "Mates", null);
        Set<Asignatura> asignaturas = new HashSet<Asignatura>();

        Alumno alumno1 = Alumno.builder().id(1L).nombre("Luis").apellidos("Fermin").asignaturas(asignaturas).build();

        when(repo.findById(alumno1.getId())).thenReturn(Optional.of(alumno1));
        when(repoAsignatura.findById(asignatura1.getId())).thenReturn(Optional.of(asignatura1));

        service.addAsignatura(alumno1.getId(), asignatura1.getId());

        assertTrue(alumno1.getAsignaturas().contains(asignatura1));
        assertEquals(1, alumno1.getAsignaturas().size());
    }
}