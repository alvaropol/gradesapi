package com.salesianostriana.dam.gradesapi.controller;

import com.salesianostriana.dam.gradesapi.dto.Asignatura.PostAsignaturaDTO;
import com.salesianostriana.dam.gradesapi.modelo.Asignatura;
import com.salesianostriana.dam.gradesapi.servicios.AsignaturaServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/asignatura")
@Tag(name = "Asignatura", description = "Controlador de asignaturas")
public class AsignaturaControlador {

    private final AsignaturaServicio service;

    @Operation(summary = "Crea una asignatura")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado una asignatura",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Asignatura.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                               { "id": 1, "nombre": "Bases de Datos", 
                                               "horas": 192, "descripción": 
                                               "Asignatura de bases de datos" }
                                               
                                               { "id": 2, "nombre": "Programación", 
                                               "horas": 230, "descripción": 
                                               "Asignatura de programación" }
                                            ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "Bad Request por parte del usuario",
                    content = @Content),
    })
    @PostMapping("/")
    public ResponseEntity<PostAsignaturaDTO> crearAsignatura(@RequestBody PostAsignaturaDTO nuevo){

        if(nuevo==null){
            return ResponseEntity.badRequest().build();
        }else{
            Asignatura asignatura = service.save(nuevo);

            return ResponseEntity.status(HttpStatus.CREATED).body(PostAsignaturaDTO.of(asignatura));
        }
    }
}
