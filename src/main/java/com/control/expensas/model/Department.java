package com.control.expensas.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "TB_DEPARTMENT")
public class Department {

    private Long departId;
    private  char department;
    private int flat; //piso
    private List<User> owners;//propietarios con rol ROLE_OWNER
    private int numberOfFloors;// cant de pisos
    private User dutyManager;// Encargado con rol ROLE_DUTY_MANAGER
    private List<Payment> payments; // pagos



}
