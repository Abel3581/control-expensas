package com.control.expensas.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TB_RECEIPT")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long receiptId;

    private LocalDate date;

    private double amount; // monto

    private String nameInCharge; // nombre del encargado

    @ManyToOne(fetch = FetchType.EAGER)
    private User owner;// propietario
}
