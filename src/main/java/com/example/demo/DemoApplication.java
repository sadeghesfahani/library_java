package com.example.demo;

import com.example.demo.services.CustomerService;
import com.example.demo.services.LibrarianService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(CustomerService customerService, LibrarianService librarianService) {
        return runner -> {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Enter 1 for librarian, 2 for customer, or 'exit' to quit:");
                String input = scanner.nextLine();

                if ("exit".equalsIgnoreCase(input)) {
                    break;
                }

                switch (input) {
                    case "1" -> {
                        System.out.println("You're a librarian. What do you want to do?");
                        // Here, you can add more options for the librarian and call the corresponding
                        // methods from the LibrarianService based on the input.
                        // For example:
                        System.out.println("Enter 'addbook' to add a book:\n Enter 'addCategory' to add category");
                        String librarianInput = scanner.nextLine();
                        if ("addbook".equalsIgnoreCase(librarianInput)) {
                            System.out.println("Enter: name, author, categoryId");
                            String librarianInputArgs = scanner.nextLine();
                            String[] parts = librarianInputArgs.split(",");

                            String name = parts[0].trim();
                            String author = parts[1].trim();
                            Long categoryId = Long.parseLong(parts[2].trim());
                            System.out.println(librarianService.addBook(name, author, categoryId));

                        }

                        if ("addCategory".equalsIgnoreCase(librarianInput)) {
                            System.out.println("Enter: name, description");
                            String librarianInputArgs = scanner.nextLine();
                            String[] parts = librarianInputArgs.split(",");

                            String name = parts[0].trim();
                            String description = parts[1].trim();
                            System.out.println(librarianService.addCategory(name, description));

                        }
                    }
                    // Repeat for other librarian options...
                    case "2" -> {
                        System.out.println("You're a customer. What do you want to do?");
                        // Here, you can add more options for the customer and call the corresponding
                        // methods from the CustomerService based on the input.
                        // For example:
                        System.out.println("Enter 'lend' to borrow a book:\n" +
                                "Enter 'register' to register:");
                        String customerInput = scanner.nextLine();
                        if ("register".equalsIgnoreCase(customerInput)) {
                            System.out.println("Enter: name, last name");
                            String librarianInputArgs = scanner.nextLine();
                            String[] parts = librarianInputArgs.split(",");

                            String name = parts[0].trim();
                            String lastname = parts[1].trim();
                            System.out.println(customerService.register(name, lastname));
                        }
//                        if ("borrow".equalsIgnoreCase(customerInput)) {
//                            customerService.requestLend();
//                        }
                    }
                    // Repeat for other customer options...
                    default -> System.out.println("Invalid option. Try again.");
                }
            }

            scanner.close();
        };
    }
}
