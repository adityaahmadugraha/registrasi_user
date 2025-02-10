package com.aditya.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
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
    private Long id_provinsi;
    private Long id_kota;
    private Long id_kecamatan;
    private Long id_kelurahan;
}
