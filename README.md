#registrasi_user#

🚀 Fitur

database PostgreSQL 

Dokumentasi API menggunakan Swagger

Upload file.csv maksimal 10MB


🛠️ Instalasi & Konfigurasi

1️⃣ Prasyarat

Java 17+

PostgreSQL (dan telah membuat database data_user)

Maven

2️⃣ Konfigurasi Database

spring.datasource.url=jdbc:postgresql://localhost:5432/data_user
spring.datasource.username=(ganti dengan username postgresql)
spring.datasource.password=(ganti dengan password postgresql)

3️⃣ Menjalankan Aplikasi

Jalankan perintah berikut di terminal:

Aplikasi akan berjalan di http://localhost:8080

4️⃣ Mengakses Swagger UI

Setelah aplikasi berjalan, buka Swagger UI di:

http://localhost:8080/swagger-ui/index.html#/

🔧 Konfigurasi Tambahan


Konfigurasi Upload File

Ukuran maksimal file yang dapat diunggah:

spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB


