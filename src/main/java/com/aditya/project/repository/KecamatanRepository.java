package com.aditya.project.repository;


import com.aditya.project.model.City;
import com.aditya.project.model.Kecamatan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface KecamatanRepository extends JpaRepository<Kecamatan, Long> {
    Optional<Kecamatan> findByNameKecamatan(String nameKecamatan);


}
