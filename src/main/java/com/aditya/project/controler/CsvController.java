package com.aditya.project.controler;

import com.aditya.project.service.CsvImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/import")
public class CsvController {

    @Autowired
    private CsvImportService csvImportService;

    @PostMapping("/csv")
    public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File tidak boleh kosong!");
        }
        String message = csvImportService.importCsv(file);
        return ResponseEntity.ok(message);
    }
}

