package com.sgv.clinica.paciente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    private final PacienteRepository pacienteRepository;
    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }
    public List<Paciente> findAllPacientes() {
        return this.pacienteRepository.findAll();
    }
    public Optional<Paciente> findPacienteById(long id) {
        return this.pacienteRepository.findById(id);
    }
    public Paciente savePaciente(Paciente paciente) {
        return this.pacienteRepository.save(paciente);
    }

    public Paciente findOrCreatePacienteByName(String nombre, String apellidoP, String apellidoM) {
        Paciente paciente = pacienteRepository.findByNombreAndApellidoPAndApellidoM(nombre, apellidoP, apellidoM);

        if (paciente == null) {
            paciente = new Paciente();
            paciente.setNombre(nombre);
            paciente.setApellidoP(apellidoP);
            paciente.setApellidoM(apellidoM);
            paciente = pacienteRepository.save(paciente);
        }

        return paciente;
    }
}
