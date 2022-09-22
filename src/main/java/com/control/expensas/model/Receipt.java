package com.control.expensas.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
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

    @ManyToOne(fetch = FetchType.EAGER)
    private User owner;// propietario
}
