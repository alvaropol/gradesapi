package com.salesianostriana.dam.gradesapi.repositorios;


import com.salesianostriana.dam.gradesapi.modelo.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InitData {

    private final AlumnoRepositorio alumnoRepositorio;
    private final AsignaturaRepositorio asignaturaRepositorio;
    private final InstrumentoRepositorio instrumentoRepositorio;

    @PostConstruct
    public void init() {

        Alumno alumno1 = Alumno.builder()
                .nombre("Alfonso")
                .apellidos("Perez García")
                .email("alfonso@gmail.com")
                .telefono("243785984")
                .fechaNacimiento(LocalDate.of(2000,12,10))
                .build();

        Alumno alumno2 = Alumno.builder()
                .nombre("Lucia")
                .apellidos("Hermenegilda Palomares")
                .email("luciah@gmail.com")
                .telefono("663719829")
                .fechaNacimiento(LocalDate.of(1992,2,5))
                .build();


        Asignatura asignatura = Asignatura.builder()
                .nombre("Base de Datos")
                .horas(300)
                .descripcion("Asignatura de base de datos SQL")
                .referentes(new ArrayList<>())
                .build();


        ReferenteEvaluacion referente1 = new ReferenteEvaluacion(
                asignatura,
                "RA01.a",
                "Conoce la teoría de la unidad"
        );
        asignatura.addReferente(referente1);


        ReferenteEvaluacion referente2 = new ReferenteEvaluacion(
                asignatura,
                "RA01.b",
                "Sabe manejar conceptos");
        asignatura.addReferente(referente2);

        ReferenteEvaluacion referente3 = new ReferenteEvaluacion(
                asignatura,
                "RA01.c",
                "Sabe la teoría y aplicarla"
        );
        asignatura.addReferente(referente3);

        asignaturaRepositorio.save(asignatura);


        Instrumento instrumento1 = Instrumento.builder()
                .nombre("Examen")
                .fecha(LocalDateTime.now())
                .asignatura(asignatura)
                .referentes(new HashSet<>(asignatura.getReferentes()))
                .contenidos("Examen sobre subconsultas en SQL, gestión y manejo de datos junto a conceptos de teoría")
                .build();

        instrumentoRepositorio.save(instrumento1);


        Set<Asignatura> asignaturas1 = new HashSet<Asignatura>();

        asignaturas1.add(asignatura);

        alumno1.setAsignaturas(asignaturas1);
        alumno2.setAsignaturas(asignaturas1);

        alumnoRepositorio.save(alumno1);
        asignaturaRepositorio.save(asignatura);
        alumnoRepositorio.save(alumno1);

        alumnoRepositorio.save(alumno2);
        asignaturaRepositorio.save(asignatura);
        alumnoRepositorio.save(alumno2);

    }
}
