package com.salesianostriana.dam.gradesapi.servicios;

import com.salesianostriana.dam.gradesapi.dto.Asignatura.PostAsignaturaDTO;
import com.salesianostriana.dam.gradesapi.modelo.Alumno;
import com.salesianostriana.dam.gradesapi.modelo.Asignatura;
import com.salesianostriana.dam.gradesapi.repositorios.AsignaturaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AsignaturaServicio {

    private final AsignaturaRepositorio repositorio;

    public List<Asignatura> findAll(){return repositorio.findAll();}

    public Optional<Asignatura> findById(Long id){return repositorio.findById(id);}

    public Asignatura save(PostAsignaturaDTO nuevo) {

        Asignatura a = new Asignatura();
        a.setId(nuevo.id());
        a.setNombre(nuevo.nombre());
        a.setHoras(nuevo.horas());
        a.setDescripcion(nuevo.descripcion());

        return repositorio.save(a);
    }
}
