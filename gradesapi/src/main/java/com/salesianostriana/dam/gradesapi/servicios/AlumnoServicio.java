package com.salesianostriana.dam.gradesapi.servicios;

import com.salesianostriana.dam.gradesapi.modelo.Alumno;
import com.salesianostriana.dam.gradesapi.repositorios.AlumnoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlumnoServicio {

    private final AlumnoRepositorio repositorio;

    public List<Alumno> findAll(){
        return repositorio.findAll();
    }

    public Optional<Alumno> findById(Long id){
        return repositorio.findById(id);
    }

}
