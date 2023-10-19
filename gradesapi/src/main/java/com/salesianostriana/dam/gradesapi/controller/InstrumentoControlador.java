package com.salesianostriana.dam.gradesapi.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.gradesapi.dto.Instrumento.GetInstrumentoDTO;
import com.salesianostriana.dam.gradesapi.dto.Instrumento.PostInstrumentoDTO;
import com.salesianostriana.dam.gradesapi.modelo.*;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/instrumento")
@Tag(name = "Instrumento", description = "Controlador de instrumentos")
public class InstrumentoControlador {

    private final InstrumentoServicio service;


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
    public ResponseEntity<GetInstrumentoDTO> addInstrumento(@RequestBody PostInstrumentoDTO dto){

        if(dto!=null){
            Optional<GetInstrumentoDTO> instrumentoOptional = service.postInstrumento(dto);
            GetInstrumentoDTO getInstrumentoDTO = instrumentoOptional.get();

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(getInstrumentoDTO);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }
}
