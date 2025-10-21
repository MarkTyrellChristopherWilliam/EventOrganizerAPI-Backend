# Event Organizer API - Backend

Proyek Backend untuk Event Organizer menggunakan **Spring Boot** dan **Kotlin**. API ini menyediakan fungsionalitas CRUD untuk Event dan manajemen pemesanan Tiket.

- Keterangan

* Java 21
* MySQL Database Server 
* Git
* Postman 

- Konfigurasi Database

1.  Perlu layanan **MySQL** berjalan.
2.  Database baru bernama `visual_prog` di phpMyAdmin.
3.  Perbarui file `src/main/resources/application.properties` jika  default (`root`, password kosong) tidak berlaku:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/visual_prog
    spring.datasource.username=root
    spring.datasource.password=
    spring.jpa.hibernate.ddl-auto=update
    ```

- Cara Menjalankan

1.  Buka terminal di direktori utama proyek.
2.  Jalankan perintah untuk membangun dan memulai aplikasi:
    ```bash
    ./gradlew bootRun
    ```
3.  API akan berjalan di `http://localhost:8080`.
4.  Uji Endpoint utama: `GET http://localhost:8080/api/events`.
