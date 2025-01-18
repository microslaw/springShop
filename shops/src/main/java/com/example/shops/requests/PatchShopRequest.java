package com.example.shops.requests;

import com.example.shops.Shop;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@ToString
public class PatchShopRequest {
    private String location;
    private int foundingYear;
    private String name;

    public Shop updateShop(Shop toUpdate) {

        if (this.location != null) {
            toUpdate.setLocation(location);
        }
        if (this.foundingYear != 0) {
            toUpdate.setFoundingYear(foundingYear);
        }
        if (this.name != null) {
            toUpdate.setName(name);
        }
        return toUpdate;
    }
}
