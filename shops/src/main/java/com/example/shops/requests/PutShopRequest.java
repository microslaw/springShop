package com.example.shops.requests;

import com.example.shops.Shop;
import lombok.Builder;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
public class PutShopRequest {
    private String location;
    private int foundingYear;
    private String name;

    public Shop toShop(String name) {
        return Shop.builder()
                .uuid(UUID.randomUUID())
                .name(name)
                .location(location)
                .foundingYear(foundingYear)
                .build();
    }
}
