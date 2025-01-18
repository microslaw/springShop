package com.example.computers;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, UUID> {
    Optional<Computer> findByShopName(String shop);
    Optional<Computer> findByModel(String model);
    @Transactional
    void deleteByModel(String model);
}
