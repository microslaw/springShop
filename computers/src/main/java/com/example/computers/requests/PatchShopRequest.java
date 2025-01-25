package com.example.computers.requests;

import com.example.computers.Shop;
import lombok.Builder;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
@Builder
public class PatchShopRequest {
    private String name;

    public Shop updateShop(Shop toUpdate) {

        if (this.name != null) {
            toUpdate.setName(name);
        }
        return toUpdate;
    }
}
