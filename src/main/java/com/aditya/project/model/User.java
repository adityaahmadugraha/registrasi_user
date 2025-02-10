package com.aditya.project.model;

//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//@Setter
//@Getter
//@Entity
//@Table(name = "users")
//
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id_user;
//    private String nama;
//    private String username;
//    private String password;
//    private String email;
//    private String jenis_kelamin;
//    private Integer id_provinsi;
//    private Integer id_kota;
//    private Integer id_kecamatan;
//    private Integer id_kelurahan;
//
//}


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

    // Tambahkan ID alamat sebagai Long
    private Long id_provinsi;
    private Long id_kota;
    private Long id_kecamatan;
    private Long id_kelurahan;

    // Setter & Getter untuk alamat
    public void setId_provinsi(Long id_provinsi) {
        this.id_provinsi = id_provinsi;
    }

    public void setId_kota(Long id_kota) {
        this.id_kota = id_kota;
    }

    public void setId_kecamatan(Long id_kecamatan) {
        this.id_kecamatan = id_kecamatan;
    }

    public void setId_kelurahan(Long id_kelurahan) {
        this.id_kelurahan = id_kelurahan;
    }

    public Long getId_provinsi() {
        return id_provinsi;
    }

    public Long getId_kota() {
        return id_kota;
    }

    public Long getId_kecamatan() {
        return id_kecamatan;
    }

    public Long getId_kelurahan() {
        return id_kelurahan;
    }
}
