package com.aditya.project.service;

import com.aditya.project.model.City;
import com.aditya.project.model.Provinsi;
import com.aditya.project.repository.CityRepository;
import com.aditya.project.repository.ProvinsiRepository;
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
public class CityImportService {

    private static final Logger logger = LoggerFactory.getLogger(CityImportService.class);

    private final CityRepository cityRepository;

    private final ProvinsiRepository provinsiRepository;

    public CityImportService(CityRepository cityRepository, ProvinsiRepository provinsiRepository) {
        this.cityRepository = cityRepository;
        this.provinsiRepository = provinsiRepository;
    }

    public City saveOrUpdateCity(City city) {
        Optional<City> existingCity = cityRepository.findById(city.getId_city());

        if (existingCity.isPresent()) {
            City oldCity = existingCity.get();
            city.setProvinsi(oldCity.getProvinsi());
        }
        return cityRepository.save(city);
    }


    @Transactional
    public String importCity(MultipartFile file) {
        List<City> cityList = new ArrayList<>();

        try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(file.getInputStream()))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .build()) {

//            reader.readNext();

            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                try {
                    if (nextLine.length < 3 || nextLine[0].trim().isEmpty() || nextLine[1].trim().isEmpty() || nextLine[2].trim().isEmpty()) {
                        logger.warn("Baris City tidak valid: {}", (Object) nextLine);
                        continue;
                    }


                    Long idCity = Long.valueOf(nextLine[0].trim());
                    String namaCity = nextLine[1].trim();
                    Long idProvinsi = Long.valueOf(nextLine[2].trim());

                    Optional<City> existingCity = cityRepository.findByNameCity(namaCity);

                    City city;
                    if (existingCity.isPresent()) {
                        city = existingCity.get();
                        city.setNameCity(namaCity);
                    } else {
                        city = new City();
                        city.setId_city(idCity);
                        city.setNameCity(namaCity);
                    }

                    Optional<Provinsi> optionalProvinsi = provinsiRepository.findById(idProvinsi);
                    if (optionalProvinsi.isPresent()) {
                        city.setProvinsi(optionalProvinsi.get());
                    } else {
                        logger.warn("Provinsi dengan ID {} tidak ditemukan.", idProvinsi);
                        continue;
                    }

                    city = saveOrUpdateCity(city);
                    cityList.add(city);

                    logger.info("Data valid: ID={} Nama={} Provinsi={}", idCity, namaCity, idProvinsi);

                } catch (NumberFormatException e) {
                    logger.error("Format ID tidak valid: {} - Error: {}", nextLine, e.getMessage());
                } catch (Exception e) {
                    throw new RuntimeException("Gagal memproses City: " + e.getMessage(), e);
                }
            }

            cityRepository.saveAll(cityList);


            return "Import City berhasil! Total data yang disimpan: " + cityList.size();

        } catch (IOException e) {
            logger.error("Gagal membaca file City! Error: {}", e.getMessage(), e);
            return "Gagal membaca file City!";
        } catch (CsvValidationException e) {
            logger.error("Kesalahan validasi City! Error: {}", e.getMessage(), e);
            return "Gagal memvalidasi City!";
        }

    }


}
