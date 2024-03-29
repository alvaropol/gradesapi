package com.salesianostriana.dam.gradesapi.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Asignatura {

    @Id @GeneratedValue
    private Long id;

    private String nombre;

    private int horas;

    private String descripcion;

    @OneToMany(mappedBy = "asignatura", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<ReferenteEvaluacion> referentes = new ArrayList<>();

    // Helpers

    // En este caso el lado propietario es Asignatura, ya que tiene CascadeType.ALL
    public void addReferente(ReferenteEvaluacion referenteEvaluacion) {
        referentes.add(referenteEvaluacion);
        referenteEvaluacion.setAsignatura(this);
    }

    public void removeReferente(ReferenteEvaluacion referenteEvaluacion) {
        removeReferente(referenteEvaluacion.getCodReferente());
        referenteEvaluacion.setAsignatura(null);
    }

    private void removeReferente(String codReferente) {
        referentes.removeIf(referenteEvaluacion -> referenteEvaluacion.getCodReferente().equalsIgnoreCase(codReferente));
    }

}
