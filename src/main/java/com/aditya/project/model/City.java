package com.aditya.project.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity


@Table(name = "city")
public class City {

    @Id
    private Long id_city;

    @Version
    private Integer version;

    @Column(name = "name_city")
    private String nameCity;

    @ManyToOne
    @JoinColumn(name = "id_provinsi", nullable = false)
    private Provinsi provinsi;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Kecamatan> kecamatanList;
}


