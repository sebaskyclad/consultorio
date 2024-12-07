package com.sgv.clinica.paciente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PacienteController {

    private final PacienteService pacienteService;
    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("/pacientes")
    public String listPacientes(Model model) {
        List<Paciente> pacientes = pacienteService.findAllPacientes();
        model.addAttribute("pacientes", pacientes);
        return "pacientesList";
    }
}
