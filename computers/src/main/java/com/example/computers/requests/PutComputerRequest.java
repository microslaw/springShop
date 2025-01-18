package com.example.computers.requests;

import com.example.computers.Computer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class PutComputerRequest {
    private String producer;
    private String mac_number;
    private int memory;

    public Computer toComputer(String model) {

        return Computer.builder()
                .uuid(UUID.randomUUID())
                .producer(producer)
                .model(model)
                .mac_number(mac_number)
                .memory(memory)
                .build();

    }
}