package com.sgv.clinica.consultorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultorioService {
    private final ConsultorioRepository consultorioRepository;
    @Autowired
    public ConsultorioService(ConsultorioRepository consultorioRepository) {
        this.consultorioRepository = consultorioRepository;
    }

    public List<Consultorio> consultar() {
        return consultorioRepository.findAll();
    }

    public Optional<Consultorio> consultar(Long id) {
        return consultorioRepository.findById(id);
    }
}
