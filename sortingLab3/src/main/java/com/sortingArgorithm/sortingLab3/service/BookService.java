package com.sortingArgorithm.sortingLab3.service;

import com.sortingArgorithm.sortingLab3.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class BookService {

    private final List<Book> books = new ArrayList<>();

    public BookService() {
        // Initialize sample data
        books.add(new Book(1L, "Book 1", "2023-01-01", "1234567890", true));
        books.add(new Book(2L, "Book 2", "2023-02-01", "0987654321", false));
        // Add more sample books as needed
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public Book getBookById(Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Book saveBook(Book book) {
        // Simulate saving by adding to the list (in a real app, you'd persist to the database)
        books.add(book);
        return book;
    }

    public void deleteBookById(Long id) {
        books.removeIf(book -> book.getId().equals(id));
    }

    public Book addBook(Book book) {
        // Generate a new ID (you may want to adjust this logic based on your needs)
        Long newId = (long) (books.size() + 1);
        book.setId(newId);
        books.add(book);
        return book;
    }

    public Book updateBook(Long id, Book updatedBook) {
        Book existingBook = getBookById(id);
        if (existingBook != null) {
            if (updatedBook.getTitle() != null) {
                existingBook.setTitle(updatedBook.getTitle());
            }
            if (updatedBook.getPublishedDate() != null) {
                existingBook.setPublishedDate(updatedBook.getPublishedDate());
            }
            if (updatedBook.getIsbn() != null) {
                existingBook.setIsbn(updatedBook.getIsbn());
            }
        }
        return existingBook;
    }

    // Sorting methods
    public List<Book> getAllBooksSortedById() {
        List<Book> sortedBooks = new ArrayList<>(books);
        sortedBooks.sort(Comparator.comparing(Book::getId));
        return sortedBooks;
    }

    public List<Book> getAllBooksSortedByTitle() {
        List<Book> sortedBooks = new ArrayList<>(books);
        sortedBooks.sort(Comparator.comparing(Book::getTitle));
        return sortedBooks;
    }
}
