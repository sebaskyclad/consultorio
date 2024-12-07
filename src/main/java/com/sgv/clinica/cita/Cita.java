package com.sgv.clinica.cita;

import com.sgv.clinica.consultorio.Consultorio;
import com.sgv.clinica.doctor.Doctor;
import com.sgv.clinica.paciente.Paciente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "cita")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "fechaInicio")
    private LocalDateTime fechaInicio;
    @Column(name = "fechaFin")
    private LocalDateTime fechaFin;
    @ManyToOne
    @JoinColumn(name = "doctor", referencedColumnName = "id")
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name = "paciente", referencedColumnName = "id")
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "consultorio", referencedColumnName = "id")
    private Consultorio consultorio;
}
