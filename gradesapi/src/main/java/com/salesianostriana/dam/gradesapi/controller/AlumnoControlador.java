package com.salesianostriana.dam.gradesapi.controller;

import com.salesianostriana.dam.gradesapi.dto.GetAlumnoDTO;
import com.salesianostriana.dam.gradesapi.modelo.Alumno;
import com.salesianostriana.dam.gradesapi.servicios.AlumnoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/alumno")
@Tag(name = "Alumno", description = "Controlador de alumnos")
public class AlumnoControlador {

    private final AlumnoServicio service;


    @Operation(summary = "Obtiene una lista de todos los alumnos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado todos los alumnos",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Alumno.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {"id": 1, "nombre": "Julio", "apellidos":
                                                "García López", "email": "julio@gmail.com",
                                                "telefono":"095454545", "fechaNacimiento": "23/02/1998",
                                                "cantidadAsignaturas": 6},
                        
                                                
                                                {"id": 2, "nombre": "Antonio", "apellidos":
                                                "Vaquero Ruiz", "email": "antonio@gmail.com",
                                                "telefono":"123456789", "fechaNacimiento": "10/09/2001",
                                                "cantidadAsignaturas": 8}
                                                
                                                {"id": 3, "nombre": "Lucia", "apellidos":
                                                "Perez Alguacil", "email": "lucia@gmail.com",
                                                "telefono":"987654321", "fechaNacimiento": "21/01/1990",
                                                "cantidadAsignaturas": 5}
                                            ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún alumno",
                    content = @Content),
    })
    @GetMapping("/")
    public ResponseEntity<List<GetAlumnoDTO>> findAll(){

        List<Alumno> list = service.findAll();

        if (list.isEmpty())
            return ResponseEntity.notFound().build();

        List<GetAlumnoDTO> result =
                list.stream()
                        .map(GetAlumnoDTO::of)
                        .toList();

        return ResponseEntity.ok(result);
    }
}
