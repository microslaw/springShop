package com.example.computers.requests;

import com.example.computers.Shop;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@ToString
public class PutShopRequest {
    private String location;
    private int foundingYear;

    public Shop toShop(String name) {
        return Shop.builder()
                .uuid(UUID.randomUUID())
                .name(name)
                .build();
    }
}
