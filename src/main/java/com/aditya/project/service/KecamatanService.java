package com.aditya.project.service;


import com.aditya.project.model.City;
import com.aditya.project.model.Kecamatan;
import com.aditya.project.repository.CityRepository;
import com.aditya.project.repository.KecamatanRepository;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class KecamatanService {

    private static final Logger logger = LoggerFactory.getLogger(KecamatanService.class);

    private final KecamatanRepository kecamatanRepository;

    private final CityRepository cityRepository;

    public KecamatanService(KecamatanRepository kecamatanRepository, CityRepository cityRepository) {
        this.kecamatanRepository = kecamatanRepository;
        this.cityRepository = cityRepository;
    }

    public Kecamatan saveOrUpdateKecamatan(Kecamatan kecamatan) {
        Optional<Kecamatan> existingCity = kecamatanRepository.findById(kecamatan.getId_kecamatan());

        if (existingCity.isPresent()) {
            Kecamatan oldKecamatan = existingCity.get();
            kecamatan.setCity(oldKecamatan.getCity());
        }
        return kecamatanRepository.save(kecamatan);
    }


    @Transactional
    public String importKecamatan(MultipartFile file) {
        List<Kecamatan> kecamatanList = new ArrayList<>();

        try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(file.getInputStream()))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .build()) {

//            reader.readNext();

            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                try {
                    if (nextLine.length < 3 || nextLine[0].trim().isEmpty() || nextLine[1].trim().isEmpty() || nextLine[2].trim().isEmpty()) {
                        logger.warn("Baris Kecamatan tidak valid: {}", (Object) nextLine);
                        continue;
                    }


                    Long idKecmatan = Long.valueOf(nextLine[0].trim());
                    String namaKecamtan = nextLine[1].trim();
                    Long idCity = Long.valueOf(nextLine[2].trim());

                    Optional<Kecamatan> existingCity = kecamatanRepository.findByNameKecamatan(namaKecamtan);

                    Kecamatan kecamatan;
                    if (existingCity.isPresent()) {
                        kecamatan = existingCity.get();
                        kecamatan.setNameKecamatan(namaKecamtan);
                    } else {
                        kecamatan = new Kecamatan();
                        kecamatan.setId_kecamatan(idKecmatan);
                        kecamatan.setNameKecamatan(namaKecamtan);
                    }

                    Optional<City> optionalCity = cityRepository.findById(idCity);
                    if (optionalCity.isPresent()) {
                        kecamatan.setCity(optionalCity.get());
                    } else {
                        continue;
                    }

                    kecamatan = saveOrUpdateKecamatan(kecamatan);
                    kecamatanList.add(kecamatan);

                    logger.info("Data valid: ID={} Nama={} Provinsi={}", idKecmatan, namaKecamtan, idCity);

                } catch (NumberFormatException e) {
                    logger.error("Format ID tidak valid: {} - Error: {}", nextLine, e.getMessage());
                } catch (Exception e) {
                    throw new RuntimeException("Gagal memproses Kecamatan: " + e.getMessage(), e);
                }
            }

            kecamatanRepository.saveAll(kecamatanList);


            return "Import Kecamatan berhasil! Total data yang disimpan: " + kecamatanList.size();

        } catch (IOException e) {
            logger.error("Gagal membaca file Kecamatan! Error: {}", e.getMessage(), e);
            return "Gagal membaca file Kecamatan!";
        } catch (CsvValidationException e) {
            logger.error("Kesalahan validasi Kecamatan! Error: {}", e.getMessage(), e);
            return "Gagal memvalidasi Kecamatan!";
        }

    }


}
