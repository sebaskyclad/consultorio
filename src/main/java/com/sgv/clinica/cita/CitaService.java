package com.sgv.clinica.cita;

import com.sgv.clinica.exception.BadRequestCita;
import com.sgv.clinica.paciente.Paciente;
import com.sgv.clinica.paciente.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CitaService {
    private final CitaRepository citaRepository;
    private final PacienteService pacienteService;
    @Autowired
    public CitaService(CitaRepository citaRepository, PacienteService pacienteService) {
        this.citaRepository = citaRepository;
        this.pacienteService = pacienteService;
    }
    public Optional<Cita> getCita(Long id) {
        return this.citaRepository.findById(id);
    }
    public List<Cita> getCitas() {
        return this.citaRepository.findAll();
    }
    public Cita addCita(Cita cita, String pacienteNombre, String pacienteApelliodoP, String pacienteApellidoM) {
        CitaValidator.validateCitaDates(cita.getFechaInicio(), cita.getFechaFin());
        Paciente paciente = pacienteService.findOrCreatePacienteByName(pacienteNombre, pacienteApelliodoP, pacienteApellidoM);
        cita.setPaciente(paciente);

        validatePacienteCitaConflict(cita.getPaciente().getId(), cita.getFechaInicio(), cita.getFechaFin());
        validateNumeroCitasDoctor(cita.getDoctor().getId(), LocalDate.from(cita.getFechaInicio()));
        validateDoctorCitaConflict(cita.getDoctor().getId(), cita.getConsultorio().getId(), cita.getFechaInicio(), cita.getFechaFin());
        return this.citaRepository.save(cita);
    }
    public Cita updateCita(Cita cita, Long id) {
        Cita existingCita = citaRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Cita not found with id: " + id));

        boolean isFechaInicioUpdated = !cita.getFechaInicio().equals(existingCita.getFechaInicio());
        boolean isFechaFinUpdated = !cita.getFechaFin().equals(existingCita.getFechaFin());

        if (isFechaInicioUpdated || isFechaFinUpdated) {
            CitaValidator.validateCitaDates(cita.getFechaInicio(), cita.getFechaFin());
            validatePacienteCitaConflict(cita.getPaciente().getId(), cita.getFechaInicio(), cita.getFechaFin());
            validateDoctorCitaConflict(cita.getDoctor().getId(), cita.getConsultorio().getId(), cita.getFechaInicio(), cita.getFechaFin());
        }

        cita.setId(id);
        return citaRepository.save(cita);
    }

    public void deleteCita(Long id) {
        this.citaRepository.deleteById(id);
    }

    public List<Cita> getCitaByDoctor(Long id) {
        return this.citaRepository.findByDoctorId(id);
    }

    public List<Cita> getCitasByDoctorAndDate(Long doctorId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);

        return citaRepository.findCitaByDoctorMismoDia(doctorId, startOfDay, endOfDay);
    }

    public void validateNumeroCitasDoctor(Long doctorId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);
        if (citaRepository.countCitasByDoctorMismoDia(doctorId, startOfDay, endOfDay) == 8) {
            throw new BadRequestCita("El doctor ya tiene la cantidad de citas maxima en el dia");
        }
    }

    private void validatePacienteCitaConflict(Long pacienteId, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        LocalDateTime horaInicio = fechaInicio.minusHours(2);
        LocalDateTime horaFin = fechaFin.plusHours(2);

        List<Cita> overlappingCitas = citaRepository.findOverlappingCitas(pacienteId, horaInicio, horaFin);

        if (!overlappingCitas.isEmpty()) {
            throw new BadRequestCita("El paciente ya tiene una cita en una ventana de tiempo de 2 horas");
        }
    }

    private void validateDoctorCitaConflict(Long doctorId, Long consultorioId, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        List<Cita> overlappingCitas = citaRepository.findOverlappingCitasForDoctorAndConsultorio(doctorId, consultorioId, fechaInicio, fechaFin);

        if (!overlappingCitas.isEmpty()) {
            throw new BadRequestCita("El paciente ya tiene una cita en una ventana de tiempo de 2 horas");
        }
    }
}
