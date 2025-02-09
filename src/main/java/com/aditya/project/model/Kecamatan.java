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

    private Long id_kecamatan;

    @Column(name = "name_kecamatan")
    private String nameKecamatan;

    @ManyToOne
    @JoinColumn(name = "id_city", nullable = false)
    private City city;

//    @OneToMany(mappedBy = "kecamatan", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Kelurahan> kelurahanList;
}
