package com.example.shops.DTO;

import com.example.shops.Shop;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ShopDTO {

    private String name;
    private String location;
    private int foundingYear;

    public ShopDTO(Shop shop) {
        this.name = shop.getName();
        this.location = shop.getLocation();
        this.foundingYear = shop.getFoundingYear();
    }

}
