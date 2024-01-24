package com.example.lld.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class BookTicketRequestDTO {
    private List<Long> showSeatId;
    private Long userId;
}
