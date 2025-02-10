package com.aditya.project.service;

import com.aditya.project.model.City;
import com.aditya.project.model.Kecamatan;
import com.aditya.project.model.Kelurahan;
import com.aditya.project.model.Provinsi;
import com.aditya.project.repository.CityRepository;
import com.aditya.project.repository.KecamatanRepository;
import com.aditya.project.repository.KelurahanRepository;
import com.aditya.project.repository.ProvinsiRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    private final ProvinsiRepository provinsiRepository;
    private final CityRepository cityRepository;
    private final KecamatanRepository kecamatanRepository;
    private final KelurahanRepository kelurahanRepository;

    public LocationService(ProvinsiRepository provinsiRepository, CityRepository cityRepository,
                           KecamatanRepository kecamatanRepository, KelurahanRepository kelurahanRepository) {
        this.provinsiRepository = provinsiRepository;
        this.cityRepository = cityRepository;
        this.kecamatanRepository = kecamatanRepository;
        this.kelurahanRepository = kelurahanRepository;
    }

    public List<Provinsi> getAllProvinsi() {
        return provinsiRepository.findAll();
    }

    public List<City> getCitiesByProvinsi(Long idProvinsi) {
        return cityRepository.findByProvinsiId(idProvinsi);
    }

    public List<Kecamatan> getKecamatanByCity(Long idCity) {
        return kecamatanRepository.findByCityId(idCity);
    }

    public List<Kelurahan> getKelurahanByKecamatan(Long idKecamatan) {
        return kelurahanRepository.findByKecamatanId(idKecamatan);
    }
}

