package com.example.EventOrganizerAPI.controller

import com.example.EventOrganizerAPI.model.Event
import com.example.EventOrganizerAPI.service.EventService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/events")
class EventController(private val eventService: EventService) {

    @GetMapping
    fun getAllEvents(): List<Event> = eventService.getAllEvents()

    @GetMapping("/{id}")
    fun getEventById(@PathVariable id: Long): ResponseEntity<Event> {
        val event = eventService.getEventById(id)
        return event?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PostMapping
    fun createEvent(@RequestBody event: Event): ResponseEntity<Event> {
        val newEvent = eventService.createEvent(event)
        return ResponseEntity(newEvent, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateEvent(@PathVariable id: Long, @RequestBody event: Event): ResponseEntity<Event> {
        val updated = eventService.updateEvent(id, event)
        return updated?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @DeleteMapping("/{id}")
    fun deleteEvent(@PathVariable id: Long): ResponseEntity<Void> {
        return try {
            eventService.deleteEvent(id)
            ResponseEntity(HttpStatus.NO_CONTENT)
        } catch (e: NoSuchElementException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/category")
    fun getEventsByCategory(@RequestParam name: String): List<Event> {
        return eventService.findByCategory(name)
    }
}