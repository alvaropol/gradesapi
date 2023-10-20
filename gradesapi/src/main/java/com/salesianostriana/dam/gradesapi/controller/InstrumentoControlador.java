package com.salesianostriana.dam.gradesapi.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.gradesapi.dto.Alumno.GetAlumnoDTO;
import com.salesianostriana.dam.gradesapi.dto.Alumno.PostAlumnoDTO;
import com.salesianostriana.dam.gradesapi.dto.Asignatura.PostAsignaturaDTO;
import com.salesianostriana.dam.gradesapi.dto.Instrumento.GetInstrumentoDTO;
import com.salesianostriana.dam.gradesapi.dto.Instrumento.PostInstrumentoDTO;
import com.salesianostriana.dam.gradesapi.modelo.*;
import com.salesianostriana.dam.gradesapi.servicios.AsignaturaServicio;
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

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/instrumento")
@Tag(name = "Instrumento", description = "Controlador de instrumentos")
public class InstrumentoControlador {

    private final InstrumentoServicio service;
    private final AsignaturaServicio serviceAsignatura;
    private final CalificacionServicio serviceCalificacion;

    @Operation(summary = "Obtiene una lista de todos los instrumentos de evaluación de una asignatura")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Si se ha encontrado la lista de instrumentos de esa asignatura",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Instrumento.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                 {
                                                        "id": 1,
                                                        "fecha": "2023-11-09T11:44:30",
                                                        "nombre": "Proyecto",
                                                        "contenidos": "Proyecto de creación de API REST",
                                                        "numeroReferentes": 2
                                                    },
                                                    
                                                     {
                                                            "id": 2,
                                                            "fecha": "2023-12-16T10:23:37",
                                                            "nombre": "Examen",
                                                            "contenidos": "Examen de programación",
                                                            "numeroReferentes": 2
                                                        }
                                            ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna asignatura con ese ID",
                    content = @Content),
    })
    @GetMapping("/{id_asig}")
    @JsonView(InstrumentoView.Instrumento02.class)
    public ResponseEntity<List<GetInstrumentoDTO>> findAll(@PathVariable Long id_asig) {

        List<Instrumento> instrumentos = service.findAll();

        List<GetInstrumentoDTO> instrumentosDeEsaAsignatura = instrumentos.stream()
                .filter(instrumento -> instrumento.getAsignatura().getId().equals(id_asig))
                .map(GetInstrumentoDTO::of)
                .collect(Collectors.toList());

        if (!instrumentosDeEsaAsignatura.isEmpty()) {
            return ResponseEntity.ok(instrumentosDeEsaAsignatura);
        }
        return ResponseEntity.notFound().build();

    }


    @Operation(summary = "Obtiene el detalle de un instrumento por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Si se ha encontrado ese instrumento con ese ID",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Instrumento.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                 {
                                                     "id": 1,
                                                     "fecha": "2023-11-09T11:44:30",
                                                     "nombre": "Proyecto",
                                                     "contenidos": "Proyecto de creación de API REST",
                                                     "asignatura": {
                                                         "id": 1,
                                                         "nombre": "Base de Datos"
                                                     },
                                                     "referentes": [
                                                         {
                                                             "codReferente": "RA01.b",
                                                             "descripcion": "El alumno sabe hacer subconsultas"
                                                         },
                                                         {
                                                             "codReferente": "RA01.a",
                                                             "descripcion": "El alumno sabeeee comandos de base de datos"
                                                         }
                                                     ]
                                                 }
                                            ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningun instrumento con ese ID",
                    content = @Content),
    })
    @GetMapping("/detalle/{id}")
    @JsonView(InstrumentoView.Instrumento03.class)
    public ResponseEntity<GetInstrumentoDTO> getById(@PathVariable Long id) {

        Optional<Instrumento> instrumentoOptional = service.findById(id);

        return instrumentoOptional.map(instrumento -> ResponseEntity.ok(GetInstrumentoDTO.of(instrumento))).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Crea un instrumento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado un instrumento",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Instrumento.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 2,
                                                "fecha": "2023-11-09T11:44:30",
                                                "nombre": "Proyecto",
                                                "contenidos": "Proyecto de creación de API REST",
                                                "idAsignatura": 2,
                                                "referentes": [
                                                    {
                                                        "codReferente": "RA01.a",
                                                        "descripcion": "El alumno sabe comandos de base de datos"
                                                    },
                                                    {
                                                        "codReferente": "RA01.b",
                                                        "descripcion": "El alumno sabe hacer subconsultas"
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
    @JsonView(InstrumentoView.Instrumento01.class)
    public ResponseEntity<GetInstrumentoDTO> addInstrumento(@RequestBody PostInstrumentoDTO dto) {

        if (dto != null) {
            Long idAsignatura = dto.idAsignatura();
            Optional<Asignatura> asignaturaOptional = serviceAsignatura.findById(idAsignatura);

            if (asignaturaOptional.isPresent()) {
                Asignatura asignatura = asignaturaOptional.get();

                if (asignatura.getReferentes() != null && !asignatura.getReferentes().isEmpty()) {
                    Optional<GetInstrumentoDTO> instrumentoOptional = service.postInstrumento(dto);
                    GetInstrumentoDTO getInstrumentoDTO = instrumentoOptional.get();

                    return ResponseEntity
                            .status(HttpStatus.CREATED)
                            .body(getInstrumentoDTO);
                }
            }
        }
            return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Edita un instrumento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado ese instrumento",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Instrumento.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 1,
                                                "fecha": "2023-11-09T11:44:30",
                                                "nombre": "Proyecto editado",
                                                "contenidos": "Proyecto de creación de API REST editado",
                                                "numeroReferentes": 2
                                            }                                        
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ese instrumento con ese ID",
                    content = @Content),
    })
    @PutMapping("/{id}")
    @JsonView(InstrumentoView.Instrumento02.class)
    public ResponseEntity<GetInstrumentoDTO> editInstrumento(@PathVariable Long id, @RequestBody PostInstrumentoDTO editado){

        return ResponseEntity.of(service.findById(id).map(
                antiguo -> {
                    antiguo.setNombre(editado.nombre());
                    antiguo.setFecha(editado.fecha());
                    antiguo.setContenidos(editado.contenidos());

                    return GetInstrumentoDTO.of(service.save(antiguo));
                }));
    }

    @Operation(summary = "Elimina un instrumento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado el instrumento por su ID",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Instrumento.class))
                    )})
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInstrumento(@PathVariable Long id){
        if(service.existsById(id)){
            Optional<Instrumento> instrumentoOptional = service.findById(id);
            Instrumento ins = instrumentoOptional.get();
            List<Calificacion> calificacionesEnInstrumento = serviceCalificacion.findByInstrumentoId(id);

            calificacionesEnInstrumento = calificacionesEnInstrumento.stream()
                            .filter(calificacion -> calificacion.getInstrumento().getId().equals(ins.getId()))
                                    .toList();

            calificacionesEnInstrumento.forEach(calificacion -> {
                serviceCalificacion.deleteById(calificacion.getId());
            });

            service.deleteById(id);
        }
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Añade un referente de evaluación a un instrumento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha añadido ese referente a ese instrumento",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Instrumento.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 1,
                                                "nombre": "Base de Datos",
                                                "horas": 212,
                                                "descripcion": "Asignatura de Base de Datos",
                                                "referentes": [
                                                    {
                                                        "codReferente": "RA01.a",
                                                        "descripcion": "El alumno sabeeee comandos de base de datos"
                                                    },
                                                    {
                                                        "codReferente": "RA01.b",
                                                        "descripcion": "El alumno sabe hacer subconsultas"
                                                    },
                                                    {
                                                        "codReferente": "RA01.c",
                                                        "descripcion": "El alumno define conceptos"
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
    @PostMapping("/{id}/referente/{cod_ref}")
    public ResponseEntity<GetInstrumentoDTO> addReferenteEnInstrumento(@PathVariable Long id,@PathVariable String cod_ref) {

        Optional<Instrumento> instrumentoOptional = service.findById(id);

        if (instrumentoOptional.isPresent()) {

            Instrumento instrumento = instrumentoOptional.get();
            Asignatura asignatura = instrumento.getAsignatura();

            boolean referenteExistente = asignatura.getReferentes().stream()
                    .anyMatch(r -> r.getCodReferente().equals(cod_ref));

            if (referenteExistente) {
                ReferenteEvaluacion referente = asignatura.getReferentes().stream()
                        .filter(r -> r.getCodReferente().equals(cod_ref))
                        .findFirst()
                        .orElse(null);

                if (referente != null) {
                    instrumento.getReferentes().add(referente);
                    service.save(instrumento);

                    Instrumento instrumentoActualizado = service.findById(id).orElse(null);

                    if (instrumentoActualizado != null) {
                        GetInstrumentoDTO instrumentoDTO = GetInstrumentoDTO.of(instrumentoActualizado);
                        return ResponseEntity.status(HttpStatus.CREATED).body(instrumentoDTO);
                    }
                }
            }
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Elimina un referente en un instrumento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado ese referente en ese instrumento",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Instrumento.class))
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "Bad Request por parte del usuario",
                    content = @Content),
    })
    @DeleteMapping("/{id}/referente/{cod_ref}")
    public ResponseEntity<?> eliminarReferenteDeInstrumento(@PathVariable Long id, @PathVariable String cod_ref) {
        Optional<Instrumento> instrumentoOptional = service.findById(id);

        if (instrumentoOptional.isPresent()) {
            Instrumento instrumento = instrumentoOptional.get();
            Asignatura asignatura = instrumento.getAsignatura();

            boolean referenteExistente = asignatura.getReferentes().stream()
                    .anyMatch(r -> r.getCodReferente().equals(cod_ref));

            if (referenteExistente) {
                ReferenteEvaluacion referente = asignatura.getReferentes().stream()
                        .filter(r -> r.getCodReferente().equals(cod_ref))
                        .findFirst()
                        .orElse(null);

                if (referente != null) {
                    instrumento.getReferentes().remove(referente);
                    service.save(instrumento);
                    return ResponseEntity.noContent().build();
                }
            }
        }
        return ResponseEntity.notFound().build();
    }
}
