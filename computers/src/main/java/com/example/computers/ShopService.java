package com.example.computers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ShopService {
    private final ShopRepository shopRepository;

    @Autowired
    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public Optional<Shop> findById(UUID id) {
        return shopRepository.findById(id);
    }

    public Optional<Shop> findByName(String name) {
        return shopRepository.findByName(name);
    }

    public void addShop(Shop shop) {
        shopRepository.save(shop);
    }

    public void deleteShop(String name) {
        shopRepository.deleteByName(name);
    }

    public List<Shop> listAllShops() {
        return shopRepository.findAll();
    }
}
