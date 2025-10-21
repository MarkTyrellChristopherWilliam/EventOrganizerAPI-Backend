package com.example.EventOrganizerAPI.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "events")
data class Event(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    val description: String,
    val category: String, 
    val location: String,
    val dateTime: LocalDateTime,
    val totalTickets: Int,
    var availableTickets: Int,
    val price: Double
)