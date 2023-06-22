package com.example.demo.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@DiscriminatorValue("CUSTOMER")
public class Customer extends User {

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + super.getId() +
                ", name='" + super.getName() + '\'' +
                ", lastName='" + super.getLastName() + '\'' +
                '}';

    }



}
