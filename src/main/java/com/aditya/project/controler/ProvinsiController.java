package com.aditya.project.controler;

import com.aditya.project.model.Provinsi;
import com.aditya.project.service.ProvinsiService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/provinsi")
public class ProvinsiController {

    private final ProvinsiService provinsiService;

    public ProvinsiController(ProvinsiService provinsiService) {
        this.provinsiService = provinsiService;
    }

    @PutMapping("/{id}")
    public Provinsi updateProvinsi(@PathVariable Long id, @RequestParam String namaBaru) {
        return provinsiService.updateProvinsi(id, namaBaru);
    }
}
