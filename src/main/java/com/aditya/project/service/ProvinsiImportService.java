package com.aditya.project.service;

import com.aditya.project.model.Provinsi;
import com.aditya.project.repository.ProvinsiRepository;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProvinsiImportService {

    private static final Logger logger = LoggerFactory.getLogger(ProvinsiImportService.class);

    private final ProvinsiRepository provinsiRepository;

    public ProvinsiImportService(ProvinsiRepository provinsiRepository) {
        this.provinsiRepository = provinsiRepository;
    }

    @Transactional
    public String importProvinsi(MultipartFile file) {
        List<Provinsi> provinsiList = new ArrayList<>();

        try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(file.getInputStream()))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .build()) {

//            reader.readNext();

            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                try {
                    if (nextLine.length < 2 || nextLine[0].trim().isEmpty() || nextLine[1].trim().isEmpty()) {
                        logger.warn("Baris Provinsi tidak valid: {}", (Object) nextLine);
                        continue;
                    }

                    Long idProvinsi = Long.valueOf(nextLine[0].trim());
                    String namaProvinsi = nextLine[1].trim();

                    if (provinsiRepository.existsById(idProvinsi)) {
                        logger.info("Provinsi dengan ID {} sudah ada, dilewati.", idProvinsi);
                        continue;
                    }

                    Provinsi provinsi = new Provinsi();
                    provinsi.setId_prov(idProvinsi);
                    provinsi.setNama_prov(namaProvinsi);

                    provinsiList.add(provinsi);
                    logger.info("Data valid: ID={} Nama={}", idProvinsi, namaProvinsi);

                } catch (NumberFormatException e) {
                    logger.error("Format ID tidak valid: {} - Error: {}", (Object) nextLine, e.getMessage());
                } catch (Exception e) {
                    throw new RuntimeException("Gagal memproses Provinsi: " + e.getMessage(), e);
                }
            }

            provinsiRepository.saveAll(provinsiList);
            provinsiRepository.flush();

            return "Import Provinsi berhasil! Total data: " + provinsiList.size();

        } catch (IOException e) {
            logger.error("Gagal membaca file Provinsi! Error: {}", e.getMessage(), e);
            return "Gagal membaca file Provinsi!";
        } catch (CsvValidationException e) {
            logger.error("Kesalahan validasi Provinsi! Error: {}", e.getMessage(), e);
            return "Gagal memvalidasi Provinsi!";
        }
    }

}
