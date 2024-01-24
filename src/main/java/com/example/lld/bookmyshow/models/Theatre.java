package com.example.lld.bookmyshow.models;

import com.example.lld.bookmyshow.models.enums.Features;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Theatre extends BaseModel{
    private String name;
    private int maxBookingSeatAllowed;
    private String address;

    @ManyToOne
    private City city;

    @OneToMany (mappedBy = "theatre")
    private List<Auditorium> auditoriums;

}
