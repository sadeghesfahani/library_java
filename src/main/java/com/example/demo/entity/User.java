package com.example.demo.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@DiscriminatorValue("TYPE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@Setter
@Getter
public class User extends BaseEntity {

    private String name;
    private String lastName;


}
