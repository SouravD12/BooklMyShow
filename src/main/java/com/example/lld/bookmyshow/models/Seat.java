package com.example.lld.bookmyshow.models;

import com.example.lld.bookmyshow.models.enums.SeatType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Seat extends BaseModel{
    private int number;
    private int row_num;
    private int col_num;

    @Enumerated(EnumType.ORDINAL)
    private SeatType seatType;

    @ManyToOne
    private Auditorium auditorium;

    @OneToMany (mappedBy = "seat")
    private List<SeatInShow> seatInShows;
}
