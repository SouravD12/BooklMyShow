package com.example.lld.bookmyshow.models;

import com.example.lld.bookmyshow.models.enums.Features;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Auditorium extends BaseModel{
    private String name;
    private int num_rows;
    private int num_cols;

    @ManyToOne
    private Theatre theatre;

    @OneToMany(mappedBy = "auditorium")
    private List<MovieShow> shows;

    @OneToMany(mappedBy = "auditorium")
    private List<Seat> seats;

    @ElementCollection
    @Enumerated(EnumType.ORDINAL)
    private List<Features> features;
}
