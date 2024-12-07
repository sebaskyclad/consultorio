package com.sgv.clinica.cita;

import com.sgv.clinica.exception.BadRequestCita;

import java.time.LocalDateTime;

public class CitaValidator {
    public static void validateCitaDates(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        if (fechaInicio == null || fechaFin == null) {
            throw new BadRequestCita("Fechas nulas");
        }
        if (!fechaInicio.toLocalDate().equals(fechaFin.toLocalDate())) {
            throw new BadRequestCita("Las fechas deben estar en el mismo dia");
        }
        if (!fechaFin.isAfter(fechaInicio)) {
            throw new BadRequestCita("Las horas de fin no puede ser antes de la fecha de inicio");
        }
    }
}