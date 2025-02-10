package com.aditya.project.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    @JoinColumn(name = "id_city")
    private City city;

}
