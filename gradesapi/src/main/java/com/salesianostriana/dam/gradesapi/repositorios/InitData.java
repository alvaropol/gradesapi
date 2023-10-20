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
    private final CalificacionRepositorio calificacionRepositorio;

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

        Alumno alumno3 = Alumno.builder()
                .nombre("Antonio")
                .apellidos("Lopez Dominguez")
                .email("antonio@gmail.com")
                .telefono("223119233")
                .fechaNacimiento(LocalDate.of(2003,8,28))
                .build();


        Asignatura asignatura = Asignatura.builder()
                .nombre("Base de Datos")
                .horas(290)
                .descripcion("Asignatura de base de datos SQL")
                .referentes(new ArrayList<>())
                .build();

        Asignatura asignatura2 = Asignatura.builder()
                .nombre("Acceso a Datos")
                .horas(320)
                .descripcion("Asignatura de acceso a datos")
                .referentes(new ArrayList<>())
                .build();

        ReferenteEvaluacion referenteA2_1 = new ReferenteEvaluacion(
                asignatura2,
                "AD01.a",
                "Define que es una API REST"
        );
        asignatura2.addReferente(referenteA2_1);

        ReferenteEvaluacion referenteA2_2 = new ReferenteEvaluacion(
                asignatura2,
                "AD01.b",
                "Conoce conceptos para la creacion de API REST"
        );
        asignatura2.addReferente(referenteA2_2);

        ReferenteEvaluacion referenteA2_3 = new ReferenteEvaluacion(
                asignatura2,
                "AD01.c",
                "Sabe gestiona una coleccion POSTMAN"
        );
        asignatura2.addReferente(referenteA2_3);

        asignaturaRepositorio.save(asignatura2);



        ReferenteEvaluacion referente1 = new ReferenteEvaluacion(
                asignatura,
                "BD01.a",
                "Conoce la teoría de la unidad"
        );
        asignatura.addReferente(referente1);


        ReferenteEvaluacion referente2 = new ReferenteEvaluacion(
                asignatura,
                "BD01.b",
                "Sabe manejar conceptos");
        asignatura.addReferente(referente2);

        ReferenteEvaluacion referente3 = new ReferenteEvaluacion(
                asignatura,
                "BD01.c",
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

        Instrumento instrumento2 = Instrumento.builder()
                .nombre("Ejercicio de evaluacion DTO/JsonView")
                .fecha(LocalDateTime.now())
                .asignatura(asignatura2)
                .referentes(new HashSet<>(asignatura2.getReferentes()))
                .contenidos("Ejercicio que comprueba si el alumno sabe gestionar DTO y JSONVIEW")
                .build();

        instrumentoRepositorio.save(instrumento2);


        Set<Asignatura> asignaturas = new HashSet<Asignatura>();

        asignaturas.add(asignatura);
        asignaturas.add(asignatura2);

        alumno1.setAsignaturas(asignaturas);
        alumno2.setAsignaturas(asignaturas);
        alumno3.setAsignaturas(asignaturas);

        alumnoRepositorio.save(alumno1);
        asignaturaRepositorio.save(asignatura);
        asignaturaRepositorio.save(asignatura2);
        alumnoRepositorio.save(alumno1);

        alumnoRepositorio.save(alumno2);
        asignaturaRepositorio.save(asignatura);
        asignaturaRepositorio.save(asignatura2);
        alumnoRepositorio.save(alumno2);

        alumnoRepositorio.save(alumno3);
        asignaturaRepositorio.save(asignatura);
        asignaturaRepositorio.save(asignatura2);
        alumnoRepositorio.save(alumno3);

        Calificacion calificacion1 = Calificacion.builder()
                .instrumento(instrumento2)
                .referente(referenteA2_1)
                .alumno(alumno1)
                .build();

        calificacionRepositorio.save(calificacion1);

    }
}
