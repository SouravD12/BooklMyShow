package com.example.lld.bookmyshow.models;

import com.example.lld.bookmyshow.models.enums.Features;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class MovieShow extends BaseModel{
    private String name;
    private Date startTime;
    private Date endTime;

    @ManyToOne
    private Auditorium auditorium;

    @OneToMany(mappedBy = "show")
    private List<SeatInShow> seatInShows;

    @OneToMany(mappedBy = "show")
    private List<SeatTypeInShow> seatTypesInShow;

    @ElementCollection
    @Enumerated(EnumType.ORDINAL)
    private List<Features> requiredFeatures;

}
