package com.aditya.project.controler;


import com.aditya.project.model.City;
import com.aditya.project.model.Kecamatan;
import com.aditya.project.model.Kelurahan;
import com.aditya.project.model.Provinsi;
import com.aditya.project.repository.CityRepository;
import com.aditya.project.repository.KecamatanRepository;
import com.aditya.project.repository.KelurahanRepository;
import com.aditya.project.repository.ProvinsiRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/alamat")
public class AlamatController {

    private final ProvinsiRepository provinsiRepository;

    private final CityRepository cityRepository;

    private final KecamatanRepository kecamatanRepository;

    private final KelurahanRepository kelurahanRepository;

    public AlamatController(ProvinsiRepository provinsiRepository, CityRepository cityRepository, KecamatanRepository kecamatanRepository, KelurahanRepository kelurahanRepository) {
        this.provinsiRepository = provinsiRepository;
        this.cityRepository = cityRepository;
        this.kecamatanRepository = kecamatanRepository;
        this.kelurahanRepository = kelurahanRepository;
    }

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
