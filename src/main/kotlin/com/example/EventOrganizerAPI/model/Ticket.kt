package com.example.EventOrganizerAPI.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "tickets")
data class Ticket(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne 
    @JoinColumn(name = "event_id", nullable = false)
    val event: Event,

    val userId: Long, 
    val bookingTime: LocalDateTime = LocalDateTime.now(),
    val quantity: Int
)