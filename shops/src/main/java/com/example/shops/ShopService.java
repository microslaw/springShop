package com.example.shops;

import com.example.shops.requests.PutShopRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ShopService {
    private final ShopRepository shopRepository;
    private final RestTemplate computersApi;

    @Autowired
    public ShopService(ShopRepository shopRepository, RestTemplate computersApi) {
        this.shopRepository = shopRepository;
        this.computersApi = computersApi;
    }

    public Optional<Shop> findById(UUID id) {
        return shopRepository.findById(id);
    }

    public Optional<Shop> findByName(String name) {
        return shopRepository.findByName(name);
    }

    public void addShop(Shop shop) {
        shopRepository.save(shop);
        PutShopRequest newShop = PutShopRequest.builder()
                .location(shop.getLocation())
                .foundingYear(shop.getFoundingYear())
                .name(shop.getName())
                .build();
        computersApi.put("/api/shops/" + shop.getName(), newShop);
    }

    public void deleteShop(String name) {
        shopRepository.deleteByName(name);
        computersApi.delete("/api/shops/" + name);
    }

    public List<Shop> listAllShops() {
        return shopRepository.findAll();
    }
}
