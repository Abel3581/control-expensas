package com.control.expensas.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TB_PAYMENT")
public class Payment {

    private Long paymentId;
    private LocalDate date;// fecha
    private double amount; // monto
    private Department department;
    private User owner; // propietario

}
