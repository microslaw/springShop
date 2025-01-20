package com.example.computers;

import com.example.computers.DTO.ComputerDTO;
import com.example.computers.DTO.ComputerListDTO;
import com.example.computers.requests.PatchComputerRequest;
import com.example.computers.requests.PatchShopRequest;
import com.example.computers.requests.PutComputerRequest;
import com.example.computers.requests.PutShopRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
// @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowPrivateNetwork = "true")
@RequestMapping("/api")
public class ShopController {
    private ShopService shopService;
    private ComputerService computerService;

    public ShopController(ShopService shopService, ComputerService computerService) {
        this.shopService = shopService;
        this.computerService = computerService;
    }

    private Shop tryFetchShop(String name) {
        Optional<Shop> fetchedShop = this.shopService.findByName(name);
        if (fetchedShop.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Shop not found");
        }
        return fetchedShop.get();
    }

    private void tryAddShop(String name, PutShopRequest putShopRequest) {
        try {
            this.shopService.addShop(putShopRequest.toShop(name));

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Shop already exists");
        }
    }

    private Computer tryFetchComputer(Shop shop, String model) {
        Optional<Computer> fetchedComputer = shop
                .getComputer_list()
                .stream()
                .filter(computer -> computer.getModel().equals(model))
                .findFirst();
        if (fetchedComputer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Computer not found");
        }
        return fetchedComputer.get();
    }

    private void tryAddComputer(String name, Computer newComputer) {
        try {
            Shop fetchedShop = this.tryFetchShop(name);
            this.computerService.addComputer(newComputer);
            fetchedShop.addComputer(newComputer);
            this.shopService.addShop(fetchedShop);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Computer already exists");
        }
    }


    @GetMapping("/shops/{name}/computer_list")
    @ResponseStatus(HttpStatus.OK)
    public ComputerListDTO getShopComputers(@PathVariable String name) {
        Shop fetchedShop = this.tryFetchShop(name);
        return new ComputerListDTO(fetchedShop.getComputer_list());
    }


    @GetMapping("/shops/{name}/computer_list/{model}")
    @ResponseStatus(HttpStatus.OK)
    public ComputerDTO getShopComputers(
            @PathVariable String name,
            @PathVariable String model) {
        Shop fetchedShop = this.tryFetchShop(name);
        Computer fetchedComputer = this.tryFetchComputer(fetchedShop, model);
        return new ComputerDTO(fetchedComputer);
    }

    @PutMapping("/shops/{name}/computer_list/{model}")
    @ResponseStatus(HttpStatus.CREATED)
    public void putComputer(
            @PathVariable String name,
            @PathVariable String model,
            @RequestBody PutComputerRequest putComputerRequest) {
        System.out.println(putComputerRequest);
        this.tryAddComputer(name, putComputerRequest.toComputer(model));
    }

    @PatchMapping("/shops/{name}/computer_list/{model}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchComputer(
            @PathVariable String name,
            @PathVariable String model,
            @RequestBody PatchComputerRequest patchComputerRequest) {
        System.out.println(patchComputerRequest);
        Shop fetchedShop = this.tryFetchShop(name);
        Computer toUpdate = this.tryFetchComputer(fetchedShop, model);

        this.computerService.addComputer(patchComputerRequest.updateComputer(toUpdate));
    }



    @DeleteMapping("/shops/{name}/computer_list/{model}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteComputer(
            @PathVariable String model,
            @PathVariable String name) {

        Shop fetchedShop = this.tryFetchShop(name);
        Computer computer = this.tryFetchComputer(fetchedShop, model);

        fetchedShop.removeComputer(computer);
        shopService.addShop(fetchedShop);

        computerService.deleteByModel(model);
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
