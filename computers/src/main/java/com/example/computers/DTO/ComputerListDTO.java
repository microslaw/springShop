package com.example.computers.DTO;

import java.util.List;
import java.util.stream.Collectors;

import com.example.computers.Computer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ComputerListDTO {
    List<String> computers;

    public ComputerListDTO(List<Computer> computers) {
        this.computers = computers
                .stream()
                .map(Computer::getModel)
                .collect(Collectors
                        .toList());
    }
}
