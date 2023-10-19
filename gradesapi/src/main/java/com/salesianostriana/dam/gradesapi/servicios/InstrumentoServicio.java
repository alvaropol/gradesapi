package com.salesianostriana.dam.gradesapi.servicios;

import com.salesianostriana.dam.gradesapi.dto.Instrumento.GetInstrumentoDTO;
import com.salesianostriana.dam.gradesapi.dto.Instrumento.PostInstrumentoDTO;
import com.salesianostriana.dam.gradesapi.modelo.Instrumento;
import com.salesianostriana.dam.gradesapi.modelo.ReferenteEvaluacion;
import com.salesianostriana.dam.gradesapi.repositorios.AsignaturaRepositorio;
import com.salesianostriana.dam.gradesapi.repositorios.InstrumentoRepositorio;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class InstrumentoServicio {

    private final InstrumentoRepositorio repositorio;
    private final AsignaturaRepositorio repositorioAsignatura;

    @Transactional
    public Optional<GetInstrumentoDTO> postInstrumento(PostInstrumentoDTO nuevo) {

       if (repositorioAsignatura.findById(nuevo.idAsignatura()).isPresent()) {
            Set<ReferenteEvaluacion> referentes = new HashSet<>();

            nuevo.referentes().forEach(referente-> {

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

        }else{
           return Optional.empty();
       }
    }
}
