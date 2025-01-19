package com.example.shops;

import com.example.shops.DTO.ShopDTO;
import com.example.shops.DTO.ShopListDTO;
import com.example.shops.requests.PatchShopRequest;
import com.example.shops.requests.PutShopRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class ShopController {
    private ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    private Shop tryFetchShop(String name) {
        Optional<Shop> fetchedShop = this.shopService.findByName(name);
        if (fetchedShop.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Shop not found");
        }
        return fetchedShop.get();
    }

    @GetMapping("/shops")
    @ResponseStatus(HttpStatus.OK)
    public ShopListDTO listShops(String sort) {
        return new ShopListDTO(this.shopService.listAllShops());
    }

    @GetMapping("/shops/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ShopDTO getShop(@PathVariable String name) {
        Shop fetchedShop = this.tryFetchShop(name);
        return new ShopDTO(fetchedShop);
    }

    private void tryAddShop(String name, PutShopRequest putShopRequest) {
        try {
            this.shopService.addShop(putShopRequest.toShop(name));

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Shop already exists");
        }
    }

    @PutMapping("/shops/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public void putShop(
            @PathVariable String name,
            @RequestBody PutShopRequest putShopRequest) {
        this.tryAddShop(name, putShopRequest);

    }

    @PatchMapping("/shops/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchShop(
            @PathVariable String name,
            @RequestBody PatchShopRequest patchShopRequest
    ) {
        Shop toUpdate = this.tryFetchShop(name);
        this.shopService.addShop(patchShopRequest.updateShop(toUpdate));
    }

    @DeleteMapping("/shops/{name}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteShop(
            @PathVariable String name
    ) {
        this.shopService.deleteShop(name);
    }

}
