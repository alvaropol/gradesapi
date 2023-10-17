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


    public Alumno save(PostAlumnoDTO nuevo) {

        Alumno alumnoExistente = null;

        if (nuevo.id() != null) {
            Optional<Alumno> alumnoOptional = repositorio.findById(nuevo.id());

            if (alumnoOptional.isPresent()) {
                alumnoExistente = alumnoOptional.get();
            }
        }

        if (alumnoExistente != null) {

            alumnoExistente.setNombre(nuevo.nombre());
            alumnoExistente.setApellidos(nuevo.apellidos());
            alumnoExistente.setEmail(nuevo.email());
            alumnoExistente.setTelefono(nuevo.telefono());
            alumnoExistente.setFechaNacimiento(nuevo.fechaNacimiento());

            return repositorio.save(alumnoExistente);
        } else {

            Alumno nuevoAlumno = new Alumno();
            nuevoAlumno.setNombre(nuevo.nombre());
            nuevoAlumno.setApellidos(nuevo.apellidos());
            nuevoAlumno.setEmail(nuevo.email());
            nuevoAlumno.setTelefono(nuevo.telefono());
            nuevoAlumno.setFechaNacimiento(nuevo.fechaNacimiento());

            return repositorio.save(nuevoAlumno);
        }
    }

}
