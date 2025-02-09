package com.aditya.project.repository;


import com.aditya.project.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByNameCity(String nameCity);


}
