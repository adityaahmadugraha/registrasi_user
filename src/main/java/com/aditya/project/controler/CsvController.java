package com.aditya.project.controler;

import com.aditya.project.service.CityImportService;
import com.aditya.project.service.KecamatanService;
import com.aditya.project.service.KelurahanService;
import com.aditya.project.service.ProvinsiImportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/import")
public class CsvController {

    private final ProvinsiImportService csvImportService;
    private final CityImportService cityImportService;
    private final KecamatanService kecamatanImportService;
    private final KelurahanService kelurahanService;


    public CsvController(ProvinsiImportService csvImportService, CityImportService cityImportService, KecamatanService kecamatanImportService, KelurahanService kelurahanService) {
        this.csvImportService = csvImportService;
        this.cityImportService = cityImportService;
        this.kecamatanImportService = kecamatanImportService;
        this.kelurahanService = kelurahanService;
    }

    @PostMapping("/provinsi")
    public ResponseEntity<String> uploadProvinsi(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File tidak boleh kosong!");
        }
        String message = csvImportService.importProvinsi(file);
        return ResponseEntity.ok(message);
    }


    @PostMapping("/city")
    public ResponseEntity<String> uploadCity(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File tidak boleh kosong!");
        }
        String message = cityImportService.importCity(file);
        return ResponseEntity.ok(message);
    }


    @PostMapping("/kecamatan")
    public ResponseEntity<String> uploadKecamatan(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File tidak boleh kosong!");
        }
        String message = kecamatanImportService.importKecamatan(file);
        return ResponseEntity.ok(message);
    }


    @PostMapping("/kelurahan")
    public ResponseEntity<String> uploadKelurahan(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File tidak boleh kosong!");
        }
        String message = kelurahanService.importKelurahan(file);
        return ResponseEntity.ok(message);
    }

}


