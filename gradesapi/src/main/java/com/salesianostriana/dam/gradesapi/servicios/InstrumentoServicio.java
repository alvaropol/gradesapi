package com.salesianostriana.dam.gradesapi.servicios;

import com.salesianostriana.dam.gradesapi.dto.Instrumento.GetInstrumentoDTO;
import com.salesianostriana.dam.gradesapi.dto.Instrumento.PostInstrumentoDTO;
import com.salesianostriana.dam.gradesapi.modelo.Instrumento;
import com.salesianostriana.dam.gradesapi.modelo.ReferenteEvaluacion;
import com.salesianostriana.dam.gradesapi.repositorios.AsignaturaRepositorio;
import com.salesianostriana.dam.gradesapi.repositorios.InstrumentoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class InstrumentoServicio {

    private final InstrumentoRepositorio repositorio;
    private final AsignaturaRepositorio repositorioAsignatura;

    public Optional<GetInstrumentoDTO> postInstrumento(PostInstrumentoDTO nuevo) {

       /* if (repositorioAsignatura.findById(nuevo.idAsignatura()).isPresent()) {
            Set<ReferenteEvaluacion> referentes = new HashSet<>();
            for (String r : nuevo.referentes()) {
                if (!referentes.add(
                        repositorioAsignatura
                                .getReferenceById(nuevo.idAsignatura())
                                .getReferentes()
                                .stream()
                                .filter(referenteEvaluacion -> referenteEvaluacion.getCodReferente()
                                        .equalsIgnoreCase(r))
                                .findFirst()
                                .get())) {
                    Optional.empty();
                }

            }

            Instrumento ins = new Instrumento(
                    nuevo.nombre(),
                    nuevo.fecha(),
                    nuevo.contenidos(),
                    repositorioAsignatura.getReferenceById(nuevo.idAsignatura()),
                    referentes
            )
        }*/
        return Optional.empty();
    }
}
