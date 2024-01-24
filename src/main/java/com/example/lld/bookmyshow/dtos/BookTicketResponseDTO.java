package com.example.lld.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookTicketResponseDTO {
    private long ticketId;
    private ResponseStatus status;
    private String message;

}
