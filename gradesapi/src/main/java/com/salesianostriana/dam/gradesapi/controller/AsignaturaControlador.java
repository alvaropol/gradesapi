package com.salesianostriana.dam.gradesapi.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.gradesapi.dto.Alumno.GetAlumnoDTO;
import com.salesianostriana.dam.gradesapi.dto.Asignatura.GetAsignaturaDTO;
import com.salesianostriana.dam.gradesapi.dto.Asignatura.PostAsignaturaDTO;
import com.salesianostriana.dam.gradesapi.modelo.Alumno;
import com.salesianostriana.dam.gradesapi.modelo.AlumnoView;
import com.salesianostriana.dam.gradesapi.modelo.Asignatura;
import com.salesianostriana.dam.gradesapi.modelo.AsignaturaView;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/asignatura")
@Tag(name = "Asignatura", description = "Controlador de asignaturas")
public class AsignaturaControlador {

    private final AsignaturaServicio service;


    @Operation(summary = "Obtiene una lista de todas las asignaturas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado todas las asignaturas",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Asignatura.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                { "id": 1, "nombre": "Bases de Datos", 
                                                "horas": 192, "descripción": "Asignatura de bases de datos", 
                                                "numReferentes": 12}
                                                
                                                { "id": 2, "nombre": "Programacion", 
                                                "horas": 230, "descripción": "Asignatura de programacion", 
                                                "numReferentes": 8}
                                                
                                                { "id": 3, "nombre": "Sistemas informáticos", 
                                                "horas": 98, "descripción": "Asignatura de Sistemas informáticos", 
                                                "numReferentes": 4}
                                                
                         
                                            ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna asignatura",
                    content = @Content),
    })
    @GetMapping("/")
    @JsonView(AsignaturaView.AsignaturaList01.class)
    public ResponseEntity<List<GetAsignaturaDTO>> findAll(){
        List<Asignatura> list = service.findAll();

        if (list.isEmpty())
            return ResponseEntity.notFound().build();

        List<GetAsignaturaDTO> result =
                list.stream()
                        .map(GetAsignaturaDTO::of)
                        .toList();

        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Obtiene una asignatura por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Si se ha encontrado esa asignatura con ese ID",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Asignatura.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                               { “id”: 1, “nombre”: “Bases de Datos”, 
                                               “horas”: 192, “descripción”: “Asignatura de bases de datos”, 
                                               “referentes”: [
                                               { “codReferente”: 1, 
                                               “descripcion”: “El alumno sabe hacer consultas},
                                               { “codReferente”: 2, 
                                               “descripcion”: “El alumno sabe definir conceptos de base de datos}
                                               ] }
                                               
                                            ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna asignatura con ese ID",
                    content = @Content),
    })
    @GetMapping("/{id}")
    @JsonView(AsignaturaView.AsignaturaList02.class)
    public ResponseEntity<GetAsignaturaDTO> getById(@PathVariable Long id){
        return ResponseEntity.of(service.findById(id)
                .map(GetAsignaturaDTO::of));
    }

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
