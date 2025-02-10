package com.aditya.project.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    @NotBlank
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
