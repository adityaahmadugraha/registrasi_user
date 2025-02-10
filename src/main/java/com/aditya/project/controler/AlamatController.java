package com.aditya.project.controler;


import com.aditya.project.model.City;
import com.aditya.project.model.Kecamatan;
import com.aditya.project.model.Kelurahan;
import com.aditya.project.model.Provinsi;
import com.aditya.project.repository.CityRepository;
import com.aditya.project.repository.KecamatanRepository;
import com.aditya.project.repository.KelurahanRepository;
import com.aditya.project.repository.ProvinsiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alamat")
public class AlamatController {

    @Autowired
    private ProvinsiRepository provinsiRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private KecamatanRepository kecamatanRepository;

    @Autowired
    private KelurahanRepository kelurahanRepository;

    @GetMapping("/provinsi")
    public List<Provinsi> getProvinsi() {
        return provinsiRepository.findAll();
    }

    @GetMapping("/city/{idProvinsi}")
    public List<City> getCities(@PathVariable Long idProvinsi) {
        return cityRepository.findByProvinsiId(idProvinsi);
    }

    @GetMapping("/kecamatan/{idCity}")
    public List<Kecamatan> getKecamatan(@PathVariable Long idCity) {
        return kecamatanRepository.findByCityId(idCity);
    }

    @GetMapping("/kelurahan/{idKecamatan}")
    public List<Kelurahan> getKelurahan(@PathVariable Long idKecamatan) {
        return kelurahanRepository.findByKecamatanId(idKecamatan);
    }
}
