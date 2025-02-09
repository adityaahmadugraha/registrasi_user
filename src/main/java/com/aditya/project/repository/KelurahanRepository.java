package com.aditya.project.repository;


import com.aditya.project.model.Kelurahan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;



@Repository
public interface KelurahanRepository extends JpaRepository<Kelurahan, Long> {
    Optional<Kelurahan> findByNameKelurahan(String nameKelurahan);


}