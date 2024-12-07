package com.sgv.clinica.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/doctores")
    public String listDoctores(Model model) {
        List<Doctor> doctores = doctorService.findAllDoctors();
        model.addAttribute("doctores", doctores);
        return "doctoresList";
    }
}
