# Event Organizer API - Backend

Proyek Backend untuk Event Organizer menggunakan **Spring Boot** dan **Kotlin**. API ini menyediakan fungsionalitas CRUD untuk Event dan manajemen pemesanan Tiket.

- Keterangan

* Java 21
* MySQL Database Server 
* Git
* Postman 

- Cara Menjalankan

1.  Buka terminal di direktori utama proyek.
2.  Jalankan perintah untuk membangun dan memulai aplikasi:
    ```bash
    ./gradlew bootRun
    ```
3.  API akan berjalan di `http://localhost:8080`.
4.  Uji Endpoint utama: `GET http://localhost:8080/api/events`.

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

   ## untuk isi database melalui SQL untuk test database
   - tabel events:

    INSERT INTO events (name, description, category, location, date_time, total_tickets, available_tickets, price) 
    VALUES
    ('Konser The Codebreakers - Asia Tour', 'Pertunjukan spektakuler band rock legendaris dari UK.', 'Musik', 'Stadion Utama Gelora Asia', '2025-11-20 20:00:00', 50000, 50000, 1500000.0),
    ('Java Spring Developers Workshop', 'Workshop intensif 2 hari tentang pengembangan API dengan Spring Boot dan Kotlin.', 'Workshop', 'Hotel Grand Jakarta, Ruang Edelweiss', '2025-10-28 09:00:00', 50, 50, 750000.0),
    ('Bazaar Kuliner Nusantara', 'Festival makanan lokal yang menghadirkan lebih dari 100 UMKM kuliner terbaik.', 'Kuliner', 'Lap. Banteng Expo Hall', '2025-11-10 11:00:00', 0, 0, 0.0),
    ('Workshop Fotografi Ponsel Pro', 'Belajar teknik pengambilan gambar profesional hanya dengan kamera ponsel Anda.', 'Workshop', 'Co-working Space, Lt 3', '2025-12-05 14:00:00', 30, 30, 150000.0),
    ('Pameran Seni Kontemporer "Perubahan"', 'Pameran karya-karya seni modern yang menggambarkan isu sosial dan lingkungan.', 'Komunitas', 'Galeri Nasional', '2025-12-15 10:00:00', 200, 200, 50000.0);

   - tabel tickets:

    UPDATE events SET available_tickets = available_tickets - 7 WHERE id = 1; 

    UPDATE events SET available_tickets = available_tickets - 1 WHERE id = 2;

    INSERT INTO tickets (event_id, user_id, quantity, booking_time) 
    VALUES
    (1, 1, 2, NOW()), -- User 1 pesan 2 tiket Konser (ID 1)
    (1, 2, 5, NOW()), -- User 2 pesan 5 tiket Konser (ID 1)
    (2, 1, 1, NOW()), -- User 1 pesan 1 tiket Workshop Java (ID 2)
    (5, 1, 3, NOW()); -- User 1 pesan 3 tiket Pameran Seni (ID 5)
