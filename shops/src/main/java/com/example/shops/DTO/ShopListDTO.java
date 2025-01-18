package com.example.shops.DTO;

import com.example.shops.Shop;

import java.util.List;
import java.util.stream.Collectors;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ShopListDTO {
    List<String> shopNames;

    public ShopListDTO(List<Shop> shops) {
        this.shopNames = shops
                .stream()
                .map(Shop::getName)
                .collect(Collectors
                        .toList());
    }

}
