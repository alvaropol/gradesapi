package com.salesianostriana.dam.gradesapi.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.gradesapi.dto.Alumno.GetAlumnoDTO;
import com.salesianostriana.dam.gradesapi.dto.Alumno.PostAlumnoDTO;
import com.salesianostriana.dam.gradesapi.modelo.Alumno;
import com.salesianostriana.dam.gradesapi.modelo.AlumnoView;
import com.salesianostriana.dam.gradesapi.modelo.Asignatura;
import com.salesianostriana.dam.gradesapi.servicios.AlumnoServicio;
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
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/alumno")
@Tag(name = "Alumno", description = "Controlador de alumnos")
public class AlumnoControlador {

    private final AlumnoServicio service;
    private final AsignaturaServicio serviceAsignatura;


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
    @JsonView(AlumnoView.AlumnoList01.class)
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


    @Operation(summary = "Obtiene un alumno por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Si se ha encontrado a ese alumno con ese ID",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Alumno.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {"id": 1, "nombre": "Julio", "apellidos":
                                                "García López", "email": "julio@gmail.com",
                                                "telefono":"095454545", "fechaNacimiento": "23/02/1998",
                                                "asignaturas": [
                                                { "id": 1, "nombre": "Bases de Datos" },
                                                    { "id": 2, "nombre": "Programacion" },
                                                    { "id": 3, "nombre": "Acceso a datos" }
                                                ]}
                                            ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado a ningún alumno con ese ID",
                    content = @Content),
    })
    @GetMapping("/{id}")
    @JsonView(AlumnoView.AlumnoList02.class)
    public ResponseEntity<GetAlumnoDTO> getById(@PathVariable Long id){
        return ResponseEntity.of(service.findById(id)
                .map(GetAlumnoDTO::of));
    }


    @Operation(summary = "Crea un alumno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado un alumno",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Alumno.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {"id": 1, "nombre": "Julio", "apellidos":
                                                "García López", "email": "julio@gmail.com",
                                                "telefono":"095454545", "fechaNacimiento": "23/02/1998"}
                                            ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "Bad Request por parte del usuario",
                    content = @Content),
    })
    @PostMapping("/")
    public ResponseEntity<PostAlumnoDTO> crearAlumno(@RequestBody PostAlumnoDTO nuevo){

        if(nuevo==null){
           return ResponseEntity.badRequest().build();
       }else{

            Alumno alumno = service.save(nuevo);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(PostAlumnoDTO.of(alumno));
        }
    }


    @Operation(summary = "Edita un alumno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado ese alumno",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Alumno.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {"id": 1, "nombre": "Alfonso", "apellidos":
                                                "García López", "email": "alfonso@gmail.com",
                                                "telefono":"095454545", "fechaNacimiento": "23/02/1998",
                                                 "cantidadAsignaturas": 8}
                                            ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado al alumno con ese ID",
                    content = @Content),
    })
    @PutMapping("/{id}")
    @JsonView(AlumnoView.AlumnoList01.class)
    public ResponseEntity<GetAlumnoDTO> editAlumno(@PathVariable Long id, @RequestBody PostAlumnoDTO editado){

        return ResponseEntity.of(service.findById(id).map(
                antiguo -> {
                    antiguo.setNombre(editado.nombre());
                    antiguo.setApellidos(editado.apellidos());
                    antiguo.setEmail(editado.email());
                    antiguo.setTelefono(editado.telefono());
                    antiguo.setFechaNacimiento(editado.fechaNacimiento());
                    antiguo.setAsignaturas((antiguo.getAsignaturas()));

                    return GetAlumnoDTO.of(service.saveDTO(antiguo));
               }));
    }


    @Operation(summary = "Añade una asignatura a un alumno ya creado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha editado ese alumno",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Alumno.class))
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ese/a alumno/asignatura o ambos por su ID",
                    content = @Content),
    })
    @PostMapping("/{id}/matricula/{id_asig}")
    public ResponseEntity<?> addAsignatura(@PathVariable Long id, @PathVariable Long id_asig){
        Optional<Alumno> alumnoOptional = service.findById(id);
        Optional<Asignatura> asignaturaOptional = serviceAsignatura.findById(id_asig);

        if(alumnoOptional.isPresent()&& asignaturaOptional.isPresent()){

            service.addAsignatura(id, id_asig);
            return ResponseEntity.status(HttpStatus.CREATED).build();

        }
        return ResponseEntity.notFound().build();

    }

    @Operation(summary = "Elimina un alumno por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado ese alumno por su ID",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Alumno.class))
                    )}),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAlumno (@PathVariable Long id){

        if(service.existsById(id)){
            service.deleteById(id);
        }
        return ResponseEntity.noContent().build();
    }

}
