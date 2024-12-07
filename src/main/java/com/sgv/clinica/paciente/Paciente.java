package com.sgv.clinica.paciente;

import com.sgv.clinica.persona.Persona;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "paciente")
public class Paciente extends Persona {
}