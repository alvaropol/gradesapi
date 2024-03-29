package com.salesianostriana.dam.gradesapi.servicios;

import com.salesianostriana.dam.gradesapi.dto.Asignatura.PostAsignaturaDTO;
import com.salesianostriana.dam.gradesapi.modelo.Asignatura;
import com.salesianostriana.dam.gradesapi.modelo.ReferenteEvaluacion;
import com.salesianostriana.dam.gradesapi.repositorios.AsignaturaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AsignaturaServicio {

    private final AsignaturaRepositorio repositorio;

    public List<Asignatura> findAll(){return repositorio.findAll();}

    public Optional<Asignatura> findById(Long id){return repositorio.findById(id);}

    public Asignatura save(PostAsignaturaDTO nuevo) {

        Asignatura asignatura = new Asignatura();
        asignatura.setId(nuevo.id());
        asignatura.setNombre(nuevo.nombre());
        asignatura.setHoras(nuevo.horas());
        asignatura.setDescripcion(nuevo.descripcion());

        return repositorio.save(asignatura);
    }

    public Asignatura saveDTO(Asignatura a){
        return repositorio.save((a));
    }

    public Optional<Asignatura> addReferente(Long id, List<ReferenteEvaluacion> referentes) {
        Optional<Asignatura> selected = repositorio.findById(id);

        if (selected.isPresent()) {
            Asignatura asignatura = selected.get();

            for (ReferenteEvaluacion ref : referentes) {
                boolean exists = asignatura.getReferentes().stream()
                        .anyMatch(referenteExiste -> referenteExiste.getCodReferente().equals(ref.getCodReferente()));

                if(!exists)
                    asignatura.addReferente(ref);

            }

            return Optional.of(repositorio.save(asignatura));
        }
        return Optional.empty();
    }

    public void deleteAsignatura(Asignatura borrarAsignatura){
        List<ReferenteEvaluacion> referentesABorrar = borrarAsignatura.getReferentes();
        referentesABorrar.stream()
                .peek(borrarAsignatura::removeReferente);
        repositorio.delete(borrarAsignatura);
    }
}
