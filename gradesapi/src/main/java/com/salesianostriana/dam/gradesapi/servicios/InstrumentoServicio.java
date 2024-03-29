package com.salesianostriana.dam.gradesapi.servicios;

import com.salesianostriana.dam.gradesapi.dto.Instrumento.GetInstrumentoDTO;
import com.salesianostriana.dam.gradesapi.dto.Instrumento.PostInstrumentoDTO;
import com.salesianostriana.dam.gradesapi.modelo.Asignatura;
import com.salesianostriana.dam.gradesapi.modelo.Instrumento;
import com.salesianostriana.dam.gradesapi.modelo.ReferenteEvaluacion;
import com.salesianostriana.dam.gradesapi.repositorios.AsignaturaRepositorio;
import com.salesianostriana.dam.gradesapi.repositorios.InstrumentoRepositorio;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class InstrumentoServicio {

    private final InstrumentoRepositorio repositorio;
    private final AsignaturaRepositorio repositorioAsignatura;

    @Transactional
    public Optional<GetInstrumentoDTO> postInstrumento(PostInstrumentoDTO nuevo) {

        if (repositorioAsignatura.findById(nuevo.idAsignatura()).isPresent()) {

            if (!repositorioAsignatura.findById(nuevo.idAsignatura()).get().getReferentes().isEmpty()) {
                Set<ReferenteEvaluacion> referentes = new HashSet<>();

                nuevo.referentes().forEach(referente -> {

                    if (!referentes.add(
                            repositorioAsignatura
                                    .getReferenceById(nuevo.idAsignatura())
                                    .getReferentes()
                                    .stream()
                                    .filter(referenteEvaluacion -> referenteEvaluacion.getCodReferente()
                                            .equalsIgnoreCase(referente))
                                    .findFirst()
                                    .get())) {
                        Optional.empty();
                    }
                });

                Instrumento ins = new Instrumento(
                        nuevo.id(),
                        nuevo.nombre(),
                        nuevo.fecha(),
                        nuevo.contenidos(),
                        repositorioAsignatura.getReferenceById(nuevo.idAsignatura()),
                        referentes
                );

                repositorio.save(ins);
                return Optional.of(GetInstrumentoDTO.of(ins));
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }
    public List<Instrumento> findAll(){
        return repositorio.findAll();
    }

    public Optional<Instrumento> findById(Long id){
        return repositorio.findById(id);
    }

    @Transactional
    public void deleteInstrumentosDeUnaAsignatura(Asignatura asignatura) {
        repositorio.deleteByAsignatura(asignatura);
    }

    public Instrumento save(Instrumento i){
        return repositorio.save((i));
    }

    public boolean existsById(Long id){
        return repositorio.existsById(id);
    }

    public void deleteById(Long id){
        repositorio.deleteById(id);
    }
}

