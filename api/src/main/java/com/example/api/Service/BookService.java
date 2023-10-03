package com.example.api.Service;

import java.util.List;

import com.example.api.Model.Book;

public interface BookService {
    List<Book> findAll();
    Book findById(Long id);

    void save(Book book);

    void remove(Long id);
}
