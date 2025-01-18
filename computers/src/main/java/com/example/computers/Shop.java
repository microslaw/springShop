package com.example.computers;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "shops")
public class Shop implements Comparable<Shop>, Serializable {

    @Id
    @Column(name = "id")
    private UUID uuid;

    @Column
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "shop", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Computer> computer_list = new ArrayList<>();

    @Autowired
    public void addComputer(Computer computer) {
        computer_list.add(computer);
        computer.setShop(this);
    }

    public void removeComputer(Computer computer) {
        computer_list.remove(computer);
        computer.setShop(null);
    }

    @Override
    public int compareTo(Shop otherShop) {
        return (0);
    }
}
