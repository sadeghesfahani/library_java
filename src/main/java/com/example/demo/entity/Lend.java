package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.plaf.BorderUIResource;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "lend")
public class Lend extends BaseEntity {

    @OneToOne
    private Customer user;

    @OneToMany
    private List<Book> books;

    private String lendDate;

    private String returnDate;

    private String status;

    private Boolean reLend;

}
