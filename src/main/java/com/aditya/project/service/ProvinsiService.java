package com.aditya.project.service;

import com.aditya.project.model.Provinsi;
import com.aditya.project.repository.ProvinsiRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class ProvinsiService {

    private final ProvinsiRepository provinsiRepository;

    public ProvinsiService(ProvinsiRepository provinsiRepository) {
        this.provinsiRepository = provinsiRepository;
    }

    @Transactional
    public Provinsi updateProvinsi(Long id, String namaBaru) {
        Optional<Provinsi> optionalProvinsi = provinsiRepository.findById(id);

        if (optionalProvinsi.isPresent()) {
            Provinsi provinsi = optionalProvinsi.get();
            provinsi.setNama_prov(namaBaru);
            return provinsiRepository.save(provinsi);
        } else {
            throw new EntityNotFoundException("Provinsi dengan ID " + id + " tidak ditemukan.");
        }
    }
}
