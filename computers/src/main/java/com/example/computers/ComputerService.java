package com.example.computers;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ComputerService {
    private final ComputerRepository computerRepository;

    @Autowired
    public ComputerService(ComputerRepository computerRepository) {
        this.computerRepository = computerRepository;
    }

    public Optional<Computer> findById(UUID id) {
        return computerRepository.findById(id);
    }

    public Optional<Computer> findByShop(String shop) {
        return computerRepository.findByShopName(shop);
    }

    public Optional<Computer> findByModel(String model) {
        return computerRepository.findByModel(model);
    }

    public void deleteById(UUID id) {
        computerRepository.deleteById(id);
    }

    @Transactional
    public void deleteByModel(String model) {
        System.out.println("Removing by model");
        System.out.println(model);
        computerRepository.deleteByModel(model);
    }

    public void addComputer(Computer computer) {
        computerRepository.save(computer);
    }

    public List<Computer> listAllComputers() {
        return computerRepository.findAll();
    }
}
