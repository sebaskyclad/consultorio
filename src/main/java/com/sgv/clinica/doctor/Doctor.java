package com.sgv.clinica.doctor;

import com.sgv.clinica.persona.Persona;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "doctor")

public class Doctor extends Persona {
    @Column(name = "especialidad")
    private String especialidad;
}
