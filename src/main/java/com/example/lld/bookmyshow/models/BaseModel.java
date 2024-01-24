package com.example.lld.bookmyshow.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass

public class BaseModel {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
}