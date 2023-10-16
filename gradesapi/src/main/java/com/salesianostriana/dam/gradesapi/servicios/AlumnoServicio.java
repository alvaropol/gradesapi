package com.salesianostriana.dam.gradesapi.servicios;

import com.salesianostriana.dam.gradesapi.dto.PostAlumnoDTO;
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

    public Alumno save(PostAlumnoDTO nuevo){

        Alumno a = new Alumno();
        a.setNombre(nuevo.nombre());
        a.setApellidos(nuevo.apellidos());
        a.setEmail(nuevo.email());
        a.setTelefono(nuevo.telefono());
        a.setFechaNacimiento(nuevo.fechaNacimiento());

        return repositorio.save(a);
    }
}
