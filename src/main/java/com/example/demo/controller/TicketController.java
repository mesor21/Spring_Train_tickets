package com.example.demo.controller;

import com.example.demo.entity.Ticket;
import com.example.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TicketController {
    @Autowired
    TicketService ticketService;

    @Async
    @GetMapping("/")
    public String getMainPage(Model model){
        model.addAttribute("list", ticketService.getAllList());
        return "mainPage";
    }
    @Async
    @GetMapping("/sale/{id}")
    public String sale(@PathVariable("id") String id){
        ticketService.switchStatus(id);
        return "redirect:/";
    }
    @Async
    @GetMapping("/admin")
    public String admin(Model model){
        model.addAttribute("list",ticketService.getAllList());
        return "adminPage";
    }
    @Async
    @GetMapping("/admin/new")
    public String addNewTicket(){
        ticketService.saveNew();
        return "redirect:/admin";
    }
    @Async
    @GetMapping("/admin/{id}")
    public String editTicket(@PathVariable("id") String id, Model model){
        model.addAttribute("ticket",ticketService.getById(id));
        return "editPage";
    }
    @Async
    @PostMapping("/admin/{id}")
    public String saveEdit(@PathVariable("id") String id,
                           @RequestParam(value = "place",required = false) String place,
                           @RequestParam(value = "name",required = false) String name,
                           @RequestParam(value = "departureStation",required = false) String departureStation,
                           @RequestParam(value = "arrivalStation",required = false) String arrivalStation,
                           @RequestParam(value = "price",required = false) String price,
                           @RequestParam(value = "priceOfGroundCard",required = false) String priceOfGroundCard,
                           @RequestParam(value = "used",required = false) String used){
        Ticket ticket = new Ticket(Long.parseLong(id), place, name,departureStation,arrivalStation,Double.parseDouble(price),Double.parseDouble(priceOfGroundCard),Boolean.valueOf(used));
        ticketService.update(ticket);
        return "redirect:/admin";
    }
    @Async
    @GetMapping("/admin/delete/{id}")
    public String deleteTicket(@PathVariable("id") String id){
        ticketService.delete(id);
        return "redirect:/admin";
    }
}
