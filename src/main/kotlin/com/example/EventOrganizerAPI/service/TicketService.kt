package com.example.EventOrganizerAPI.service

import com.example.EventOrganizerAPI.model.Ticket
import com.example.EventOrganizerAPI.repository.EventRepository
import com.example.EventOrganizerAPI.repository.TicketRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TicketService(
    private val ticketRepository: TicketRepository,
    private val eventRepository: EventRepository
) {

    fun getTicketsByUserId(userId: Long): List<Ticket> = ticketRepository.findByUserId(userId)

    @Transactional
    fun bookTicket(eventId: Long, userId: Long, quantity: Int): Ticket {
        val event = eventRepository.findById(eventId).orElseThrow {
            NoSuchElementException("Event not found with ID: $eventId")
        }

        if (quantity <= 0) {
            throw IllegalArgumentException("Quantity must be positive.")
        }

        if (event.availableTickets < quantity) {
            throw IllegalStateException("Not enough tickets available for event: ${event.name}")
        }

        event.availableTickets -= quantity
        eventRepository.save(event)

        val newTicket = Ticket(
            event = event,
            userId = userId,
            quantity = quantity
        )

        return ticketRepository.save(newTicket)
    }

    @Transactional
    fun cancelTicket(ticketId: Long) {
        val ticket = ticketRepository.findById(ticketId).orElseThrow {
            NoSuchElementException("Ticket not found with ID: $ticketId")
        }
        val event = ticket.event

        event.availableTickets += ticket.quantity
        eventRepository.save(event)

        ticketRepository.delete(ticket)
    }
}