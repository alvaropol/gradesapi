package com.salesianostriana.dam.gradesapi.repositorios;

import com.salesianostriana.dam.gradesapi.dto.Alumno.PostAlumnoDTO;
import com.salesianostriana.dam.gradesapi.dto.Asignatura.PostAsignaturaDTO;
import com.salesianostriana.dam.gradesapi.modelo.Alumno;
import com.salesianostriana.dam.gradesapi.modelo.Asignatura;
import com.salesianostriana.dam.gradesapi.servicios.AlumnoServicio;
import com.salesianostriana.dam.gradesapi.servicios.AsignaturaServicio;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class InitData {

    private final AlumnoServicio servicioAlumno;
    private final AsignaturaServicio servicioAsignatura;


    @PostConstruct
    public void init(){

        Alumno a1 = Alumno.builder()
                .nombre("Ale")
                .apellidos("Romero García")
                .email("ale@gmail.com")
                .telefono("987654321")
                .fechaNacimiento(LocalDate.of(2000, 2,12))
                .build();

        Asignatura asignatura1 = Asignatura.builder()
                        .horas(212)
                        .nombre("Base de Datos")
                        .descripcion("Asignatura de Base de Datos")
                        .build();

        servicioAlumno.save(PostAlumnoDTO.of(a1));

        servicioAsignatura.save(PostAsignaturaDTO.of(asignatura1));
    }
}
