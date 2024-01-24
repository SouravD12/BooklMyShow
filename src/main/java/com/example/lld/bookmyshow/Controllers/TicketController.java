package com.example.lld.bookmyshow.Controllers;

import com.example.lld.bookmyshow.Services.TicketService;
import com.example.lld.bookmyshow.dtos.BookTicketRequestDTO;
import com.example.lld.bookmyshow.dtos.BookTicketResponseDTO;
import com.example.lld.bookmyshow.dtos.ResponseStatus;
import com.example.lld.bookmyshow.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TicketController {

    @Autowired
    private  TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService){
        this.ticketService=ticketService;
    }
    public BookTicketResponseDTO bookTIcket(BookTicketRequestDTO requestDTO){
        BookTicketResponseDTO responseDTO= new BookTicketResponseDTO();

        try{
            Ticket ticket= ticketService.bookTicket(requestDTO.getShowSeatId(), requestDTO.getUserId());
            responseDTO.setStatus(ResponseStatus.SUCCESS);
            responseDTO.setMessage("Ticket Generated Successfully");
        }
        catch (Exception ex){
            responseDTO.setStatus(ResponseStatus.FAILURE);
            responseDTO.setMessage("Something went wrong. Check this message to find out. ");
        }
        return  responseDTO;
    }
}
