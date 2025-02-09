package com.aditya.project.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;
    private String nama;
    private String username;
    private String password;
    private String email;
    private String jenis_kelamin;
    private Integer id_provinsi;
    private Integer id_kota;
    private Integer id_kecamatan;
    private Integer id_kelurahan;

}
