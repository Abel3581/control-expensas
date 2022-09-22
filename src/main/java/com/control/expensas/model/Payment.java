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
@Table(name = "TB_PAYMENT")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    private LocalDate date;// fecha
    private double amount; // monto
    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn ( name = "userId")
    private User owner; // propietario

}
