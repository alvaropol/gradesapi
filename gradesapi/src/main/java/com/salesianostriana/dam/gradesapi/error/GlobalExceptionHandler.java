package com.salesianostriana.dam.gradesapi.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.LocalDate;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /*@ExceptionHandler(AlumnoNotFoundException.class)
    ProblemDetail handleAlumnoNotFoundException(AlumnoNotFoundException a){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,a.getMessage());
        problem.setType(URI.create("http://gradesapi.com/errors/not-found"));
        problem.setTitle("Alumno no encontrado");
        problem.setProperty("local-datetime", LocalDate.now());
        return problem;
    }*/
}
