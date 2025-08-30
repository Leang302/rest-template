package com.leang.resttemplate.controller;

import com.leang.resttemplate.model.Ticket;
import com.leang.resttemplate.model.request.TicketRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tickets")
public class TicketController {
    String BASE_URL = "http://localhost:9090/api/v1/tickets";
    RestTemplate restTemplate = new RestTemplate();
//    @GetMapping
//    public Ticket[] getAllTickets(){
//        return restTemplate.getForObject(BASE_URL,Ticket[].class);
//    }

    @GetMapping
    public ResponseEntity<Ticket[]> getAllTickets() {
        return restTemplate.getForEntity(BASE_URL, Ticket[].class);
    }

    @PostMapping
    public Ticket createTicket(@RequestBody TicketRequest ticketRequest) {
        return restTemplate.postForObject(BASE_URL, ticketRequest, Ticket.class);
    }

    @PutMapping("{id}")
    public String updateTicketById(@PathVariable Long id, @RequestBody TicketRequest ticketRequest) {
        restTemplate.put("http://localhost:9090/api/v1/tickets/{id}", ticketRequest, id);
        return "Update success";
    }

    @DeleteMapping("{id}")
    public String updateTicketById(@PathVariable Long id) {

        restTemplate.delete("http://localhost:9090/api/v1/tickets/{id}", id);
        return "Delete success";
    }
}
