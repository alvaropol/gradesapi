package com.salesianostriana.dam.gradesapi.servicios;

import com.salesianostriana.dam.gradesapi.dto.Asignatura.PostAsignaturaDTO;
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

    public Asignatura save(PostAsignaturaDTO nuevo){
        Asignatura asignaturaExistente = null;

        if(nuevo.id()!=null){
            Optional<Asignatura> asignaturaOptional = findById(nuevo.id());
            if(asignaturaOptional.isPresent()){
                asignaturaExistente = asignaturaOptional.get();
            }
        }

        if(asignaturaExistente!=null){
            asignaturaExistente.setNombre(nuevo.nombre());
            asignaturaExistente.setHoras(nuevo.horas());
            asignaturaExistente.setDescripcion(nuevo.descripcion());

            return repositorio.save(asignaturaExistente);
        }else{
            Asignatura nuevaAsignatura = new Asignatura();
            nuevaAsignatura.setNombre(nuevo.nombre());
            nuevaAsignatura.setHoras(nuevo.horas());
            nuevaAsignatura.setDescripcion(nuevo.descripcion());

            return repositorio.save(nuevaAsignatura);
        }
    }
}
