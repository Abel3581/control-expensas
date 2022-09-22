package com.control.expensas.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "TB_DEPARTMENT")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long departId;
    private  char department;
    private int flat; //piso
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<User> owners;//propietarios con rol ROLE_OWNER
    private int numberOfFloors;// cant de pisos
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "userManager_id")
    private User dutyManager;// Encargado con rol ROLE_DUTY_MANAGER
    @OneToMany(targetEntity=Payment.class,cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Payment> payments; // pagos



}
