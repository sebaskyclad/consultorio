package com.sgv.clinica.cita;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByDoctorId(Long doctorId);

    @Query("SELECT c FROM Cita c WHERE c.paciente.id = :pacienteId " +
            "AND (:start BETWEEN c.fechaInicio AND c.fechaFin OR :end BETWEEN c.fechaInicio AND c.fechaFin OR " +
            "c.fechaInicio BETWEEN :start AND :end)")
    List<Cita> findOverlappingCitas(@Param("pacienteId") Long pacienteId,
                                    @Param("start") LocalDateTime start,
                                    @Param("end") LocalDateTime end);


    @Query("SELECT c FROM Cita c WHERE (c.doctor.id = :doctorId OR c.consultorio.id = :consultorioId) " +
            "AND (:start BETWEEN c.fechaInicio AND c.fechaFin OR :end BETWEEN c.fechaInicio AND c.fechaFin OR " +
            "c.fechaInicio BETWEEN :start AND :end)")
    List<Cita> findOverlappingCitasForDoctorAndConsultorio(@Param("doctorId") Long doctorId,
                                                           @Param("consultorioId") Long consultorioId,
                                                           @Param("start") LocalDateTime start,
                                                           @Param("end") LocalDateTime end);

    @Query("SELECT c FROM Cita c WHERE c.doctor.id = :doctorId " +
            "AND c.fechaInicio >= :start AND c.fechaFin <= :end")
    List<Cita> findCitaByDoctorMismoDia(@Param("doctorId") Long doctorId,
                                        @Param("start") LocalDateTime start,
                                        @Param("end") LocalDateTime end);

    @Query("SELECT COUNT(c) FROM Cita c WHERE c.doctor.id = :doctorId " +
            "AND c.fechaInicio >= :start AND c.fechaFin <= :end")
    Long countCitasByDoctorMismoDia(@Param("doctorId") Long doctorId,
                                    @Param("start") LocalDateTime start,
                                    @Param("end") LocalDateTime end);
}
