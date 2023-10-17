package com.salesianostriana.dam.gradesapi.servicios;

import com.salesianostriana.dam.gradesapi.dto.Alumno.PostAlumnoDTO;
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


    public Alumno save(PostAlumnoDTO nuevo) {

        Alumno alumno = new Alumno();
        alumno.setId(nuevo.id());
        alumno.setNombre(nuevo.nombre());
        alumno.setApellidos(nuevo.apellidos());
        alumno.setEmail(nuevo.email());
        alumno.setTelefono(nuevo.telefono());
        alumno.setFechaNacimiento(nuevo.fechaNacimiento());

        return repositorio.save(alumno);
    }
}
