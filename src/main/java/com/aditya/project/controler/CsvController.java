package com.aditya.project.controler;

import com.aditya.project.service.CityImportService;
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

    public CsvController(ProvinsiImportService csvImportService, CityImportService cityImportService) {
        this.csvImportService = csvImportService;
        this.cityImportService = cityImportService;
    }

    @PostMapping("/provinsi")
    public ResponseEntity<String> uploadProvinsi(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File tidak boleh kosong!");
        }
        String message = csvImportService.importProvinsi(file);
        return ResponseEntity.ok(message);
    }


    private final CityImportService cityImportService;

    @PostMapping("/city")
    public ResponseEntity<String> uploadCity(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File tidak boleh kosong!");
        }
        String message = cityImportService.importCity(file);
        return ResponseEntity.ok(message);
    }

}


