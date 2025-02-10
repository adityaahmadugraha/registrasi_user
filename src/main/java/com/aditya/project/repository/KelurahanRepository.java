package com.aditya.project.repository;


import com.aditya.project.model.Kelurahan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



@Repository
public interface KelurahanRepository extends JpaRepository<Kelurahan, Long> {

    Optional<Kelurahan> findByNameKelurahan(String nameKelurahan);

    @Query("SELECT k FROM Kelurahan k WHERE k.kecamatan.id_kecamatan = :id_kecamatan")
    List<Kelurahan> findByKecamatanId(@Param("id_kecamatan") Long id_kecamatan);
}
