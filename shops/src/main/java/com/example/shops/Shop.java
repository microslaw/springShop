package com.example.shops;

import jakarta.persistence.*;
import lombok.*;

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
@Table(name = "shops")
public class Shop implements Comparable<Shop>, Serializable {

    @Id
    @Column(name = "id")
    private UUID uuid;

    @Column
    private String name;

    @Column
    private String location;

    @Column(name = "founding_year")
    private int foundingYear;

    @Override
    public int compareTo(Shop otherShop) {
        return (this.foundingYear - otherShop.foundingYear);
    }
}
