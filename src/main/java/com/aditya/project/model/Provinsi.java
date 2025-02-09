package com.aditya.project.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "provinsi")
public class Provinsi {

    @Id
    private Long id_prov;

    @Column(nullable = false, unique = true)
    private String nama_prov;


}