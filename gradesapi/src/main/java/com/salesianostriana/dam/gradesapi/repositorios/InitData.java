package com.salesianostriana.dam.gradesapi.repositorios;

import com.salesianostriana.dam.gradesapi.dto.PostAlumnoDTO;
import com.salesianostriana.dam.gradesapi.modelo.Alumno;
import com.salesianostriana.dam.gradesapi.servicios.AlumnoServicio;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class InitData {

    private final AlumnoServicio servicioAlumno;


    @PostConstruct
    public void init(){

        Alumno a1 = Alumno.builder()
                .nombre("Ale")
                .apellidos("Romero García")
                .email("ale@gmail.com")
                .telefono("987654321")
                .fechaNacimiento(LocalDate.of(2000, 2,12))
                .build();

        servicioAlumno.save(PostAlumnoDTO.of(a1));
    }
}
