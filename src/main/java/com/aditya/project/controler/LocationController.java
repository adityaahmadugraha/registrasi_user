package com.aditya.project.controler;


import com.aditya.project.model.City;
import com.aditya.project.model.Kecamatan;
import com.aditya.project.model.Kelurahan;
import com.aditya.project.model.Provinsi;
import com.aditya.project.service.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/provinsi")
    public List<Provinsi> getAllProvinsi() {
        return locationService.getAllProvinsi();
    }

    @GetMapping("/city/{idProvinsi}")
    public List<City> getCitiesByProvinsi(@PathVariable Long idProvinsi) {
        return locationService.getCitiesByProvinsi(idProvinsi);
    }

    @GetMapping("/kecamatan/{idCity}")
    public List<Kecamatan> getKecamatanByCity(@PathVariable Long idCity) {
        return locationService.getKecamatanByCity(idCity);
    }

    @GetMapping("/kelurahan/{idKecamatan}")
    public List<Kelurahan> getKelurahanByKecamatan(@PathVariable Long idKecamatan) {
        return locationService.getKelurahanByKecamatan(idKecamatan);
    }
}
