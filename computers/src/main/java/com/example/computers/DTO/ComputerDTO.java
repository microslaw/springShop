package com.example.computers.DTO;

import com.example.computers.Computer;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ComputerDTO implements Comparable<ComputerDTO> {

    private String producer;
    private String model;
    private int memory;
    private String mac_number;

    public ComputerDTO(Computer computer) {
        this.producer = computer.getProducer();
        this.model = computer.getModel();
        this.memory = computer.getMemory();
        this.mac_number = computer.getMac_number();
    }

    public int compareTo(ComputerDTO otherComputer) {
        return (otherComputer.getMemory() - this.getMemory());
    }

}
