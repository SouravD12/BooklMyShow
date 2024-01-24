package com.example.lld.bookmyshow.models;

import com.example.lld.bookmyshow.models.enums.SeatStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class SeatInShow extends BaseModel{

    @Enumerated(EnumType.ORDINAL)
    private SeatStatus seatStatus;

    @ManyToOne
    private Seat seat;

    @ManyToOne
    private MovieShow show;

    @ManyToOne
    private Ticket ticket;

    private Date statusUpdatedAt;
}
