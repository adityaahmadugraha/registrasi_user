package com.aditya.project.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "kecamatan")
public class Kecamatan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_kecamatan;

    @Column(nullable = false, unique = true)
    private String name_kecamatan;

    @ManyToOne
    @JoinColumn(name = "id_city", nullable = false)
    private City city;

    @OneToMany(mappedBy = "kecamatan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Kelurahan> kelurahanList;
}
