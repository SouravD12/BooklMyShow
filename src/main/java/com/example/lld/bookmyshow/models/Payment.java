package com.example.lld.bookmyshow.models;


import com.example.lld.bookmyshow.models.enums.PaymentProvider;
import com.example.lld.bookmyshow.models.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Payment extends BaseModel{
    private int amount;
    private String transactionId;

    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.ORDINAL)
    private PaymentProvider paymentProvider;

    @OneToOne
    private Ticket ticket;
}
