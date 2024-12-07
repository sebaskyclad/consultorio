package com.sgv.clinica.paciente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Paciente findByNombreAndApellidoPAndApellidoM(String nombre, String apellidoP, String apellidoM);
}
