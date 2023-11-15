package com.salesianostriana.dam.gradesapi.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

import java.net.URI;
import java.time.LocalDateTime;

public class AlumnoNotFoundException extends ErrorResponseException {

    public AlumnoNotFoundException(Long id_alumno){
        super(HttpStatus.NOT_FOUND, asProblemDetail("El alumno con el id "+id_alumno+" no existe, introduzca otro id"),null);
    }

    private static ProblemDetail asProblemDetail(String message){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,message);
        problem.setType(URI.create("http://gradesapi.com/errors/not-found"));
        problem.setTitle("Alumno no encontrado");
        problem.setProperty("local-datetime", LocalDateTime.now());
        return problem;
    }
}
