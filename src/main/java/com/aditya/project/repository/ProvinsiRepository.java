package com.aditya.project.repository;

import com.aditya.project.model.Provinsi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinsiRepository extends JpaRepository<Provinsi, Long> {
    @Query("SELECT p.id_prov FROM Provinsi p")
    List<Long> findAllIds();
}