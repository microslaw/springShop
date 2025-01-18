package com.example.computers.requests;

import com.example.computers.Computer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PatchComputerRequest {
    private String producer;
    private String mac_number;
    private int memory;

    public Computer updateComputer(Computer toUpdate) {
        if (this.producer != null) {
            toUpdate.setProducer(producer);
        }
        if (this.mac_number != null) {
            toUpdate.setMac_number(mac_number);
        }
        if (this.memory != 0) {
            toUpdate.setMemory(memory);
        }
        return toUpdate;
    }
}
