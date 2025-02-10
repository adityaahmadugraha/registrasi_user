package com.aditya.project.repository;


import com.aditya.project.model.City;
import com.aditya.project.model.Kecamatan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface KecamatanRepository extends JpaRepository<Kecamatan, Long> {

    Optional<Kecamatan> findByNameKecamatan(String nameKecamatan);

    @Query("SELECT k FROM Kecamatan k WHERE k.city.id_city = :id_city")
    List<Kecamatan> findByCityId(@Param("id_city") Long id_city);
}

