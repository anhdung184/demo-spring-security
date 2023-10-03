/*package com.example.api.Controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.Model.Book;
import com.example.api.Repository.BookRepository;

@RestController
@RequestMapping("/api")
public class ApiBookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
    
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable(value = "id") long id){
        Optional<Book> books = bookRepository.findById(id);
        
        if(books.isPresent()){
            return new ResponseEntity<>(books.get(),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        try{
            Book _book = bookRepository
                .save(new Book(book.getBname(), book.getBauthor(), book.getBcost(),book.getBstatus()));
            return new ResponseEntity<>(_book, HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }       
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") long id,@RequestBody Book book){
        Optional<Book> bookid = bookRepository.findById(id);

        if(bookid.isPresent()){
            Book _book = bookid.get();
            _book.setBname(book.getBname());
            _book.setBauthor(book.getBauthor());
            _book.setBcost(book.getBcost());
            _book.setBstatus(book.getBstatus());
            return new ResponseEntity<>(bookRepository.save(_book),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") long id){
        try {
            bookRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/books")
    public ResponseEntity<HttpStatus> deleteAllBook(){
        try {
            bookRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
*/