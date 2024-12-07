package com.sgv.clinica.consultorio;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ConsultorioController {

    private final ConsultorioService consultorioService;

    @Autowired
    public ConsultorioController(ConsultorioService consultorioService) {
        this.consultorioService = consultorioService;
    }

    @GetMapping("/consultorios")
    public String listConsultorios(Model model) {
        List<Consultorio> consultorios = consultorioService.consultar();
        model.addAttribute("consultorios", consultorios);
        return "consultoriosList";
    }
}
