package com.example.demo.service;

import com.example.demo.entity.Ticket;
import com.example.demo.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    public List<Ticket> getAllList(){
        return ticketRepository.findAll();
    }
    @Async
    public void delete(String id){
        ticketRepository.delete(Long.valueOf(id));
    }
    @Async
    public void saveNew(){
        Ticket ticket = new Ticket();
        ticketRepository.save(ticket);
    }
    @Async
    public Ticket getById(String id){
        return ticketRepository.getByID(Long.valueOf(id));
    }
    @Async
    public Ticket update(Ticket employee){
        return ticketRepository.update(employee);
    }
    @Async
    public void switchStatus(String id){
        Ticket ticket = ticketRepository.getByID(Long.parseLong(id));
        ticket.setUsed(!ticket.isUsed());
        ticketRepository.update(ticket);
    }
}
