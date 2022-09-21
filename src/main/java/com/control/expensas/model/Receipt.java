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
@Table(name = "TB_RECEIPT")
public class Receipt {

    private Long receiptId;
    private LocalDate date;
    private User owner;// propietario
}
