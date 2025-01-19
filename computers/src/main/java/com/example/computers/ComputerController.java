package com.example.computers;

import com.example.computers.DTO.ComputerDTO;
import com.example.computers.DTO.ComputerListDTO;
import com.example.computers.requests.PatchComputerRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ComputerController {
    private final ComputerService computerService;

    public ComputerController(ComputerService computerService) {
        this.computerService = computerService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/computers")
    public ComputerListDTO listComputers(@RequestParam(required = false) String sort) {
        return new ComputerListDTO(this.computerService.listAllComputers());
    }

    @GetMapping("/computers/{model}")
    public ComputerDTO getComputer(@PathVariable String model) {
        Optional<Computer> fetchedComputer = this.computerService.findByModel(model);
        if (fetchedComputer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Computer not found");
        }
        return new ComputerDTO(fetchedComputer.get());
    }

    @PatchMapping("/computers/{model}")
    @ResponseStatus(HttpStatus.CREATED)
    public void patchComputer(
            @PathVariable String model,
            @RequestBody PatchComputerRequest patchComputerRequest) {
        Optional<Computer> toUpdate = computerService.findByModel(model);
        if (toUpdate.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Computer not found");
        }
        this.computerService.addComputer(patchComputerRequest.updateComputer(toUpdate.get()));
    }

//    @DeleteMapping("/computers/{model}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteComputer(@PathVariable String model) {
//
//        System.out.println("Deleting by model");
//        System.out.println(model);
//        this.computerService.deleteByModel(model);
//    }
}
