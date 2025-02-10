package com.aditya.project.repository;


import com.aditya.project.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query("SELECT c FROM City c WHERE c.provinsi.id_prov = :idProvinsi")
    List<City> findByProvinsiId(@Param("idProvinsi") Long idProvinsi);

    Optional<City> findByNameCity(String nameCity);
}
