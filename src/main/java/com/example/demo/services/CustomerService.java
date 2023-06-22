package com.example.demo.services;

import com.example.demo.entity.Book;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Lend;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.LendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final LendRepository lendRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, LendRepository lendRepository) {

        this.customerRepository = customerRepository;
        this.lendRepository = lendRepository;
    }

    public Customer register(String name, String lastName) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setLastName(lastName);
        return this.customerRepository.save(customer);
    }

    public Customer register(Customer customer) {
        return this.customerRepository.save(customer);
    }


    public void showCustomerInfo(Long id) throws Exception {
        Customer customer = this.customerRepository.findById(id).orElseThrow(() -> new Exception("notfound"));
        System.out.println(customer.toString());
    }

    public Lend requestLend(List<Book> books, Customer customer) {
        Lend lend = new Lend();
        lend.setUser(customer);
        lend.setBooks(books);
        lend.setStatus("wait for approve");
        return this.lendRepository.save(lend);
    }

    public Lend requestRelend(Lend lend) {
        lend.setStatus("request re-lend");
        return this.lendRepository.save(lend);
    }

    public List<Lend> lendHistory(Customer customer) {
        return this.lendRepository.findAll().stream().filter(lend -> lend.getUser() == customer).collect(Collectors.toList());
    }
}
