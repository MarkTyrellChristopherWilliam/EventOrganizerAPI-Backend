package com.example.EventOrganizerAPI.controller

import com.example.EventOrganizerAPI.model.Ticket
import com.example.EventOrganizerAPI.service.TicketService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/tickets")
class TicketController(private val ticketService: TicketService) {

    @PostMapping("/book/{eventId}")
    fun bookTicket(
        @PathVariable eventId: Long,
        @RequestParam userId: Long,
        @RequestParam quantity: Int
    ): ResponseEntity<Ticket> {
        return try {
            val bookedTicket = ticketService.bookTicket(eventId, userId, quantity)
            ResponseEntity(bookedTicket, HttpStatus.CREATED)
        } catch (e: NoSuchElementException) {
            ResponseEntity(HttpStatus.NOT_FOUND) 
        } catch (e: IllegalStateException) {
            ResponseEntity(HttpStatus.BAD_REQUEST) 
        } catch (e: IllegalArgumentException) {
            ResponseEntity(HttpStatus.BAD_REQUEST) 
        }
    }

    @GetMapping("/user/{userId}")
    fun getTicketsByUserId(@PathVariable userId: Long): List<Ticket> {
        return ticketService.getTicketsByUserId(userId)
    }
}