package com.aditya.project.service;

import com.aditya.project.model.Kecamatan;
import com.aditya.project.model.Kelurahan;
import com.aditya.project.repository.KecamatanRepository;
import com.aditya.project.repository.KelurahanRepository;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class KelurahanService {

    private final KelurahanRepository kelurahanRepository;

    private final KecamatanRepository kecamatanRepository;

    public KelurahanService(KelurahanRepository kelurahanRepository, KecamatanRepository kecamatanRepository) {
        this.kelurahanRepository = kelurahanRepository;
        this.kecamatanRepository = kecamatanRepository;
    }

    public Kelurahan saveOrUpdateKelurahan(Kelurahan kelurahan) {
        Optional<Kelurahan> existingKecamatan = kelurahanRepository.findById(kelurahan.getId_kelurahan());

        if (existingKecamatan.isPresent()) {
            Kelurahan oldKelurahan = existingKecamatan.get();
            kelurahan.setKecamatan(oldKelurahan.getKecamatan());
        }
        return kelurahanRepository.save(kelurahan);
    }


    @Transactional
    public String importKelurahan(MultipartFile file) {
        List<Kelurahan> kelurahanList = new ArrayList<>();

        try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(file.getInputStream()))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .build()) {

//            reader.readNext();

            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                try {
                    if (nextLine.length < 3 || nextLine[0].trim().isEmpty() || nextLine[1].trim().isEmpty() || nextLine[2].trim().isEmpty()) {
                        log.warn("Baris Kelurahan tidak valid: {}", (Object) nextLine);
                        continue;
                    }


                    Long idKelurahan = Long.valueOf(nextLine[0].trim());
                    String namaKelurahan = nextLine[1].trim();
                    Long idKecamatan = Long.valueOf(nextLine[2].trim());

                    Optional<Kelurahan> existingKecamatan = kelurahanRepository.findByNameKelurahan(namaKelurahan);

                    Kelurahan kelurahan;
                    if (existingKecamatan.isPresent()) {
                        kelurahan = existingKecamatan.get();
                        kelurahan.setNameKelurahan(namaKelurahan);
                    } else {
                        kelurahan = new Kelurahan();
                        kelurahan.setId_kelurahan(idKelurahan);
                        kelurahan.setNameKelurahan(namaKelurahan);
                    }

                    Optional<Kecamatan> optionalKecamatan = kecamatanRepository.findById(idKecamatan);
                    if (optionalKecamatan.isPresent()) {
                        kelurahan.setKecamatan(optionalKecamatan.get());
                    } else {
                        continue;
                    }

                    kelurahan = saveOrUpdateKelurahan(kelurahan);
                    kelurahanList.add(kelurahan);

                    log.info("Data valid: ID={} Nama={} Provinsi={}", idKelurahan, namaKelurahan, idKecamatan);

                } catch (NumberFormatException e) {
                    log.error("Format ID tidak valid: {} - Error: {}", nextLine, e.getMessage());
                } catch (Exception e) {
                    throw new RuntimeException("Gagal memproses Kecamatan: " + e.getMessage(), e);
                }
            }

            kelurahanRepository.saveAll(kelurahanList);


            return "Import Kecamatan berhasil! Total data yang disimpan: " + kelurahanList.size();

        } catch (IOException e) {
            log.error("Gagal membaca file Kecamatan! Error: {}", e.getMessage(), e);
            return "Gagal membaca file Kecamatan!";
        } catch (CsvValidationException e) {
            log.error("Kesalahan validasi Kecamatan! Error: {}", e.getMessage(), e);
            return "Gagal memvalidasi Kecamatan!";
        }

    }


}
