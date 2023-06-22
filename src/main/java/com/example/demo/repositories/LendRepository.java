package com.example.demo.repositories;

import com.example.demo.entity.Lend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LendRepository extends JpaRepository<Lend, Long> {
}
