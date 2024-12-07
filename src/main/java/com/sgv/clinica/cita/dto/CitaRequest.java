package com.sgv.clinica.cita.dto;

import com.sgv.clinica.consultorio.Consultorio;
import com.sgv.clinica.doctor.Doctor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CitaRequest {
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Doctor doctor;
    private Consultorio consultorio;
    private String nombre;  // Only the name of the Paciente
    private String apellidoP;  // Only the name of the Paciente
    private String apellidoM;  // Only the name of the Paciente

    // Getters and setters
}
