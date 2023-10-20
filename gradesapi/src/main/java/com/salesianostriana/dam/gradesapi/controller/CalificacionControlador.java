package com.salesianostriana.dam.gradesapi.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.gradesapi.dto.Calificacion.*;
import com.salesianostriana.dam.gradesapi.modelo.*;
import com.salesianostriana.dam.gradesapi.servicios.AlumnoServicio;
import com.salesianostriana.dam.gradesapi.servicios.CalificacionServicio;
import com.salesianostriana.dam.gradesapi.servicios.InstrumentoServicio;
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

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calificacion")
@Tag(name = "Calificacion", description = "Controlador de calificacion")
public class CalificacionControlador {

    private final CalificacionServicio calificacionServicio;
    private final AlumnoServicio alumnoServicio;
    private final InstrumentoServicio instrumentoServicio;


    @Operation(summary = "Obtiene una lista de todas las calificaciones de un referente de evaluación para los alumnos calificados en dicho referente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Si se ha encontrado dicha lista de calificaciones por el id de asignatura y el codReferente de esta última",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Calificacion.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "idAsignatura": 1,
                                                "codReferente": "RA01.a",
                                                "alumnos": [
                                                    {
                                                        "id": 1,
                                                        "nombre": "Alfonso",
                                                        "apellidos": "Perez García",
                                                        "calificaciones": [
                                                            {
                                                                "idInstrumento": 1,
                                                                "calificacion": 7.3
                                                            }
                                                        ]
                                                    }
                                                ]
                                            }                            
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna asignatura o referente con los datos pasados",
                    content = @Content),
    })
    @GetMapping("/{idAsignatura}/{codReferente}")
    public ResponseEntity<?> getCalificacionesByReferente(
            @PathVariable Long idAsignatura,
            @PathVariable String codReferente
    ) {
        GetAlumnoEnCalificacion02DTO calificaciones = calificacionServicio.getCalificacionesByReferente(idAsignatura, codReferente);

        if (calificaciones != null) {
            return ResponseEntity.status(HttpStatus.OK).body(calificaciones);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Obtiene una lista de todas las calificaciones de un instrumento de evaluación de todos los alumnos calificados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Si se ha encontrado la lista de calificaciones de un instrumento para los alumnos calificados",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Calificacion.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "idInstrumento": 1,
                                                "nombre": "Examen",
                                                "alumnos": [
                                                    {
                                                        "id": 2,
                                                        "nombre": "Lucia",
                                                        "apellidos": "Hermenegilda Palomares",
                                                        "calificaciones": [
                                                            {
                                                                "codReferente": "RA01.a",
                                                                "descripcion": "Conoce la teoría de la unidad",
                                                                "calificacion": 7.3
                                                            },
                                                            {
                                                                "codReferente": "RA01.b",
                                                                "descripcion": "Sabe manejar conceptos",
                                                                "calificacion": 5.0
                                                            }
                                                        ]
                                                    },
                                                    {
                                                        "id": 1,
                                                        "nombre": "Alfonso",
                                                        "apellidos": "Perez García",
                                                        "calificaciones": [
                                                            {
                                                                "codReferente": "RA01.a",
                                                                "descripcion": "Conoce la teoría de la unidad",
                                                                "calificacion": 8.3
                                                            },
                                                            {
                                                                "codReferente": "RA01.b",
                                                                "descripcion": "Sabe manejar conceptos",
                                                                "calificacion": 3.0
                                                            }
                                                        ]
                                                    }
                                                ]
                                            }                                   
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna asignatura con ese ID",
                    content = @Content),
    })
    @GetMapping("/instrumento/{id}")
    @JsonView(CalificacionView.CalificacionAlumno.class)
    public ResponseEntity<GetAlumnoEnCalificacionDTO> getCalificacionesByInstrumento(@PathVariable Long id) {
        GetAlumnoEnCalificacionDTO calificaciones = calificacionServicio.getCalificacionesByInstrumento(id);

        if (calificaciones != null) {
            return ResponseEntity.status(HttpStatus.OK).body(calificaciones);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Añade una o varias calificaciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado la/s calificacion/es",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Calificacion.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "idInstrumento": 1,
                                                "nombre": "Examen",
                                                "calificaciones": [
                                                    {
                                                        "id": 3,
                                                        "codReferente": "RA01.a",
                                                        "descripcion": "Conoce la teoría de la unidad",
                                                        "calificacion": 7.3
                                                    },
                                                    {
                                                        "id": 4,
                                                        "codReferente": "RA01.b",
                                                        "descripcion": "Sabe manejar conceptos",
                                                        "calificacion": 5.0
                                                    }
                                                ]
                                            }                                     
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "Bad Request por parte del usuario",
                    content = @Content),
    })
    @PostMapping("/")
    @JsonView(CalificacionView.PostCalificacion.class)
    public ResponseEntity<?> addCalificaciones(@RequestBody PostCalificacionDTO postCalificacionDTO) {

        Optional<Alumno> alumno = alumnoServicio.findById(postCalificacionDTO.idAlumno());

        if(alumno.isEmpty())
            return ResponseEntity.badRequest().body(new Mensaje("No se puede crear la calificación. Compruebe que los datos del instrumento y los referentes son correctos"));

        Optional<Instrumento> instrumento = instrumentoServicio.findById(postCalificacionDTO.idInstrumento());


        if(instrumento.isEmpty())
            return ResponseEntity.badRequest().body(new Mensaje("No se puede crear la calificación. Compruebe que los datos del instrumento y los referentes son correctos"));

        GetCalificacionDTO calificaciones = calificacionServicio.addCalificaciones(postCalificacionDTO);

        if (calificaciones!=null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(calificaciones);
        } else {
            return ResponseEntity.badRequest().body(new Mensaje("No se puede crear la calificación. Compruebe que los datos del instrumento y los referentes son correctos"));
        }
    }


    @Operation(summary = "Elimina una calificacion por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado esa calificacion por ese ID",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Calificacion.class))
                    )}),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCalificacion(@PathVariable Long id){

        if(calificacionServicio.existsById(id))
            calificacionServicio.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
