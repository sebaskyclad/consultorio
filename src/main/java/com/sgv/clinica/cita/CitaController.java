package com.sgv.clinica.cita;

import com.sgv.clinica.cita.dto.CitaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/citas")
public class CitaController {

    private final CitaService citaService;

    @Autowired
    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @PostMapping("/api/add")
    public ResponseEntity<Cita> addCita(@RequestBody CitaRequest citaRequest) {
        Cita cita = new Cita();
        cita.setFechaInicio(citaRequest.getFechaInicio());
        cita.setFechaFin(citaRequest.getFechaFin());
        cita.setDoctor(citaRequest.getDoctor());
        cita.setConsultorio(citaRequest.getConsultorio());

        Cita savedCita = citaService.addCita(cita, citaRequest.getNombre(), citaRequest.getApellidoP(), citaRequest.getApellidoM());

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCita);
    }

    @PostMapping("/add")
    public String viewCita(@ModelAttribute CitaRequest citaRequest) {
        Cita cita = new Cita();
        cita.setFechaInicio(citaRequest.getFechaInicio());
        cita.setFechaFin(citaRequest.getFechaFin());
        cita.setDoctor(citaRequest.getDoctor());
        cita.setConsultorio(citaRequest.getConsultorio());

        citaService.addCita(cita, citaRequest.getNombre(), citaRequest.getApellidoP(), citaRequest.getApellidoM());

        return "redirect:/citas/doctor/" + citaRequest.getDoctor().getId() + "/" + citaRequest.getFechaInicio().format(DateTimeFormatter.ofPattern("ddMMyyyy"));
    }

    @GetMapping("/api/doctor/{doctorId}/{date}")
    public List<Cita> DoctorFecha(@PathVariable Long doctorId,
                                  @PathVariable String date) {
        DateTimeFormatter fecha = DateTimeFormatter.ofPattern("ddMMyyyy");
        LocalDate parsedDate = LocalDate.parse(date, fecha);
        return citaService.getCitasByDoctorAndDate(doctorId, parsedDate);
    }

    @GetMapping("/add")
    public String showAddCitaForm(Model model) {
        model.addAttribute("citaRequest", new CitaRequest());
        return "addCita";
    }

    @GetMapping("/doctor/{doctorId}/{date}")
    public String viewDoctorFecha(@PathVariable Long doctorId,
                                  @PathVariable String date,
                                  Model model) {
        DateTimeFormatter fecha = DateTimeFormatter.ofPattern("ddMMyyyy");
        LocalDate parsedDate = LocalDate.parse(date, fecha);
        List<Cita> citas = citaService.getCitasByDoctorAndDate(doctorId, parsedDate);

        model.addAttribute("citas", citas);
        model.addAttribute("doctorId", doctorId);
        model.addAttribute("date", parsedDate);
        return "citasByDoctorAndDate";
    }
}
