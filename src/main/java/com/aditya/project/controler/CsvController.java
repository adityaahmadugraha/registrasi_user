package com.aditya.project.controler;

import com.aditya.project.model.City;
import com.aditya.project.service.CityImportService;
import com.aditya.project.service.ProvinsiImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/import")
public class CsvController {

    @Autowired
    private ProvinsiImportService csvImportService;




    @PostMapping("/provinsi")
    public ResponseEntity<String> uploadProvinsi(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File tidak boleh kosong!");
        }
        String message = csvImportService.importProvinsi(file);
        return ResponseEntity.ok(message);
    }


    @Autowired
    private CityImportService cityImportService;

    @PostMapping("/city")
    public ResponseEntity<String> uploadCity(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File tidak boleh kosong!");
        }
        String message = cityImportService.importCity(file);
        return ResponseEntity.ok(message);
    }

}


