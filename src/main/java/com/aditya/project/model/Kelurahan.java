package com.aditya.project.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "kelurahan")
public class Kelurahan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_kelurahan;

    @Column(nullable = false, unique = true)
    private String name_kelurahan;

    @ManyToOne
    @JoinColumn(name = "id_kecamatan", nullable = false)
    private Kecamatan kecamatan;
}
