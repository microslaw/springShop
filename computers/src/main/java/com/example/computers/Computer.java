package com.example.computers;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Entity;

import java.io.Serializable;
import java.util.UUID;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Computers")
public class Computer implements Comparable<Computer>, Serializable {

    @Id
    private UUID uuid;

    @Column
    private String producer;

    @Column
    private String model;

    @Column
    private int memory;

    @Column(unique = true)
    private String mac_number;

     @ManyToOne
     @JoinColumn(name="shop", referencedColumnName = "id")
     @ToString.Exclude
     private Shop shop;

    @Override
    public int compareTo(Computer otherComputer) {
        return (otherComputer.getMemory() - this.getMemory());
    }

}
