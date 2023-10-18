package com.salesianostriana.dam.gradesapi.servicios;

import com.salesianostriana.dam.gradesapi.dto.Alumno.PostAlumnoDTO;
import com.salesianostriana.dam.gradesapi.modelo.Alumno;
import com.salesianostriana.dam.gradesapi.modelo.Asignatura;
import com.salesianostriana.dam.gradesapi.repositorios.AlumnoRepositorio;
import com.salesianostriana.dam.gradesapi.repositorios.AsignaturaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AlumnoServicio {

    private final AlumnoRepositorio repositorio;
    private final AsignaturaRepositorio repositorioAsignatura;

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

    public Alumno saveDTO(Alumno a){
        return repositorio.save((a));
    }

    public void addAsignatura(Long id, Long id_asig){

        Optional<Alumno> alumnoOptional = repositorio.findById(id);
        Optional<Asignatura> asignaturaOptional = repositorioAsignatura.findById(id_asig);

        if(alumnoOptional.isPresent()&& asignaturaOptional.isPresent()){
            Alumno alumno = alumnoOptional.get();
            Asignatura asignatura = asignaturaOptional.get();

            Set<Asignatura> asignaturas= alumno.getAsignaturas();

            asignaturas.add(asignatura);

            repositorio.save(alumno);

        }

    }
}
