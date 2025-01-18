package com.example.shops;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MainInitializer implements InitializingBean {
    private final ShopService shopService;

    @Autowired
    public MainInitializer(ShopService shopService) {
        this.shopService = shopService;
    }

    @Override
    public void afterPropertiesSet() {

        Shop myShop = Shop.builder()
                .uuid(UUID.randomUUID())
                .name("xcom")
                .location("Gdynia")
                .foundingYear(2021)
                .build();

        shopService.addShop(myShop);


        Shop myShop2 = Shop.builder()
                .uuid(UUID.randomUUID())
                .name("mediaExpert")
                .location("Gdansk")
                .foundingYear(2015)
                .build();

        shopService.addShop(myShop2);

    }
}
