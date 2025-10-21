package com.example.EventOrganizerAPI.repository

import com.example.EventOrganizerAPI.model.Event
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EventRepository : JpaRepository<Event, Long> {
    fun findByCategory(category: String): List<Event>
}