package com.salesianostriana.dam.gradesapi.servicios;

import com.salesianostriana.dam.gradesapi.modelo.Alumno;
import com.salesianostriana.dam.gradesapi.modelo.Asignatura;
import com.salesianostriana.dam.gradesapi.repositorios.AlumnoRepositorio;
import com.salesianostriana.dam.gradesapi.repositorios.AsignaturaRepositorio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

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

        Asignatura asignatura1 = new Asignatura(1L,"Matematicas",200,"Mates",null);
        Alumno alumno1 = new Alumno(1L,"Luis","Fermin","luis@gmail.com","23984394", LocalDate.of(2000,3,19),null);
        boolean subjectAdded = false;
        
        service.addAsignatura(alumno1.getId(),asignatura1.getId());

        Set<Asignatura> subjects = alumno1.getAsignaturas();

        for(Asignatura a : subjects){
            subjectAdded = a.getId() == 2L;
        }

        assertTrue(subjectAdded);
    }
}