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
    private Long id_kelurahan;

    @Column(name = "name_kelurahan")
    private String nameKelurahan;

    @ManyToOne
    @JoinColumn(name = "id_kecamatan")
    private Kecamatan kecamatan;
}
