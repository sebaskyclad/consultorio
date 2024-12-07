package com.sgv.clinica;

import com.sgv.clinica.consultorio.Consultorio;
import com.sgv.clinica.consultorio.ConsultorioRepository;
import com.sgv.clinica.doctor.Doctor;
import com.sgv.clinica.doctor.DoctorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class DataInitializer implements CommandLineRunner {

    private final DoctorRepository doctorRepository;
    private final ConsultorioRepository consultorioRepository;

    @Autowired
    public DataInitializer(DoctorRepository doctorRepository, ConsultorioRepository consultorioRepository) {
        this.doctorRepository = doctorRepository;
        this.consultorioRepository = consultorioRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (doctorRepository.count() == 0) {
            Doctor doctor1 = new Doctor();
            doctor1.setNombre("Dr. Juan");
            doctor1.setApellidoP("Perez");
            doctor1.setApellidoM("Rodriguez");
            doctor1.setEspecialidad("Cardiología");
            doctorRepository.save(doctor1);

            Doctor doctor2 = new Doctor();
            doctor2.setNombre("Dr. Maria");
            doctor2.setApellidoP("Lopez");
            doctor2.setApellidoM("Farias");
            doctor2.setEspecialidad("Pediatría");
            doctorRepository.save(doctor2);

            Doctor doctor3 = new Doctor();
            doctor3.setNombre("Dr. Aldo");
            doctor3.setApellidoP("Pichardo");
            doctor3.setApellidoM("Lombardo");
            doctor3.setEspecialidad("Optalmologia");
            doctorRepository.save(doctor3);

        }

        if (consultorioRepository.count() == 0) {
            Consultorio consultorio1 = new Consultorio();
            consultorio1.setNumero(101);
            consultorio1.setPiso(1);
            consultorioRepository.save(consultorio1);

            Consultorio consultorio2 = new Consultorio();
            consultorio2.setNumero(102);
            consultorio2.setPiso(1);
            consultorioRepository.save(consultorio2);

            Consultorio consultorio3 = new Consultorio();
            consultorio3.setNumero(202);
            consultorio3.setPiso(2);
            consultorioRepository.save(consultorio3);
        }
    }
}
