package com.example.demo.repositories;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepositoryImpl implements CustomCategoryRepository {

    private final EntityManager entityManager;

    @Autowired
    public CategoryRepositoryImpl(EntityManager en) {
        this.entityManager = en;
    }


    public boolean doesCategoryHasBook(Long categoryId) {
        String query = "SELECT COUNT(b) FROM Book b WHERE b.category.id = :categoryId";
        Long count = (Long) entityManager.createQuery(query)
                .setParameter("categoryId", categoryId)
                .getSingleResult();
        return count > 0;
    }
}
