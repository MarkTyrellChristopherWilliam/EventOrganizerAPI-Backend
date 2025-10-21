package com.example.EventOrganizerAPI.service

import com.example.EventOrganizerAPI.model.Event
import com.example.EventOrganizerAPI.repository.EventRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class EventService(private val eventRepository: EventRepository) {

    fun getAllEvents(): List<Event> = eventRepository.findAll()

    fun getEventById(id: Long): Event? = eventRepository.findByIdOrNull(id)

    fun createEvent(event: Event): Event = eventRepository.save(event)

    fun updateEvent(id: Long, updatedEvent: Event): Event? {
        return eventRepository.findByIdOrNull(id)?.let { existingEvent ->
            val eventToSave = existingEvent.copy(
                name = updatedEvent.name,
                description = updatedEvent.description,
                category = updatedEvent.category,
                location = updatedEvent.location,
                dateTime = updatedEvent.dateTime,
                totalTickets = updatedEvent.totalTickets,
                availableTickets = minOf(updatedEvent.totalTickets, existingEvent.availableTickets)
            )
            eventRepository.save(eventToSave)
        }
    }

    fun deleteEvent(id: Long) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id)
        } else {
            throw NoSuchElementException("Event with ID $id not found.")
        }
    }

    fun findByCategory(category: String): List<Event> = eventRepository.findByCategory(category)
}