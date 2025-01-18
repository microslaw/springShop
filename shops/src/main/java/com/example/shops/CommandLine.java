package com.example.shops;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CommandLine implements CommandLineRunner {
    private final ShopService shopService;

    @Autowired
    public CommandLine(ShopService shopService) {
        this.shopService = shopService;
    }

    public void ls_shops() {
        for (Shop shop : shopService.listAllShops()) {
            System.out.println(shop);
        }
    }


    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String command;
        main_loop:
        while (true) {
            System.out.print("s>");
            command = scanner.nextLine();
            switch (command) {

                case "ls shops" -> {
                    ls_shops();
                }

                case "exit" -> {
                    System.out.println("shutting down...");
                    break main_loop;
                }
                default -> {
                    System.out.print("Unknown command");
                    System.out.print(command);
                    System.out.println("Available commands are:");
                    System.out.println("    ls shops");

                    System.out.println("exit");

                }
            }
        }
        scanner.close();
    }
}
