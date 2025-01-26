package com.example.computers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;
import java.util.*;

@Component
public class CommandLine implements CommandLineRunner {
    private final ComputerService computerService;
    private final ShopService shopService;

    @Autowired
    public CommandLine(ComputerService computerService, ShopService shopService) {
        this.computerService = computerService;
        this.shopService = shopService;
    }

    public void ls_shops() {
        for (Shop shop : shopService.listAllShops()) {
            System.out.println(shop);
        }
    }

    public void ls_computers() {
        for (Computer computer : computerService.listAllComputers()) {
            System.out.print(computer);
            System.out.print("  shop_uuid=");
            System.out.println(computer.getShop().getUuid());
        }
    }

    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String command;
        main_loop:
        while (true) {
            System.out.print("c>");
            TimeUnit.SECONDS.sleep(1);
            try {
                command = scanner.nextLine();
            } catch (NoSuchElementException e) {
                TimeUnit.SECONDS.sleep(1);
                continue;
            }
            switch (command) {
                case "ls computers" -> {
                    ls_computers();
                }
                case "ls shops" -> {
                    ls_shops();
                }


                case "add computer" -> {
                    Computer newComputer = new Computer();
                    newComputer.setUuid(UUID.randomUUID());
                    System.out.print("producer=");
                    newComputer.setProducer(scanner.nextLine());
                    System.out.print("model=");
                    newComputer.setModel(scanner.nextLine());
                    System.out.print("mac_number=");
                    newComputer.setMac_number(scanner.nextLine());
                    System.out.print("memory=");
                    newComputer.setMemory(Integer.parseInt(scanner.nextLine()));
                    System.out.println("s&c>ls shops");

                    List<Shop> shops = shopService.listAllShops();
                    ListIterator<Shop> shopIterator = shops.listIterator();

                    while (shopIterator.hasNext()) {
                        System.out.print(shopIterator.nextIndex());
                        System.out.print(")  ");
                        System.out.println(shopIterator.next());
                    }
                    System.out.print("shop=");
                    int pickedShop = Integer.parseInt(scanner.nextLine());
                    newComputer.setShop(shops.get(pickedShop));

                    computerService.addComputer(newComputer);
                }
                case "rm computer" -> {
                    System.out.print("model=");
                    String model = scanner.nextLine();
                    Optional<Computer> computer = computerService.findByModel(model);
                    if (computer.isPresent()) {
                        computerService.deleteByModel(model);
                        System.out.println("Computer removed.");
                    } else {
                        System.out.print("Computer ");
                        System.out.print(model);
                        System.out.print(" not found");
                    }
                }

                case "exit" -> {
                    System.out.println("shutting down...");
                    break main_loop;
                }
                default -> {
                    System.out.print("Unknown command");
                    System.out.print(command);
                    System.out.println("Available commands are:");
                    System.out.println("    ls computers");
                    System.out.println("    ls shops");
                    System.out.println("    add computer");
                    System.out.println("    delete computer");
                    System.out.println("    ls shops");

                    System.out.println("exit");

                }
            }
        }
        scanner.close();
    }
}
