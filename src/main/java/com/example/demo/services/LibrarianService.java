package com.example.demo.services;

import com.example.demo.entity.Book;
import com.example.demo.entity.Category;
import com.example.demo.entity.Lend;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.LendRepository;
import com.example.demo.repositories.LibrarianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class LibrarianService {
    private final LibrarianRepository librarianRepository;
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    private final LendRepository lendRepository;

    @Autowired
    public LibrarianService(
            LibrarianRepository librarianRepository,
            BookRepository bookRepository,
            CategoryRepository categoryRepository, LendRepository lendRepository) {
        this.librarianRepository = librarianRepository;
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.lendRepository = lendRepository;
    }


    public Book addBook(Book book) {
        return this.bookRepository.save(book);
    }

    public Book addBook(String name, String author, Category category) {
        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setCategory(category);
        return this.bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        this.bookRepository.deleteById(id);
    }

    public void deleteBook(Book book) {
        this.bookRepository.delete(book);
    }

    public Book updateCategory(Book book, Category category) {
        book.setCategory(category);
        return this.bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    public Category addCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    public Category addCategory(String name, String description) {
        Category category = new Category();
        category.setName(name);
        category.setDescription(description);
        return this.categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        this.categoryRepository.deleteById(id);
    }

    public void deleteCategory(Category category) {
        this.categoryRepository.delete(category);
    }

    public Category updateCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    public Category updateCategory(Long id, String name, String description) {
        return this.categoryRepository.findById(id).map(category -> {
            category.setName(name);
            category.setDescription(description);
            return this.categoryRepository.save(category);
        }).orElseThrow(() -> new NoSuchElementException("Category not found"));
    }

    public List<Category> showAllCategoriesHasAtLeastOneBook() {
        return this.categoryRepository.findAll().stream().filter(category -> {
            return this.categoryRepository.doesCategoryHasBook(category.getId());
        }).collect(Collectors.toList());
    }

    public Lend approveLend(Lend lend) {
        lend.setStatus("approved");
        return this.lendRepository.save(lend);
    }

    public Lend approveLend(Long lendId) {
        Lend lend = this.lendRepository.findById(lendId).orElseThrow(() -> new NoSuchElementException("Lend not found"));
        lend.setStatus("approved");
        return this.lendRepository.save(lend);
    }

    public Lend approveRelend(Lend lend) {
        lend.setStatus("approved");
        lend.setReLend(false);
        lend.setLendDate(new Date().toString());
        Date date = new Date();  // your original date
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();  // convert it to LocalDate
        LocalDate newLocalDate = localDate.plusMonths(1);  // add one month
        Date newDate = Date.from(newLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());  // convert it back to Date
        lend.setReturnDate(newDate.toString());
        return lendRepository.save(lend);
    }

}
