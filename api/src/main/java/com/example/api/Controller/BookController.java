package com.example.api.Controller;


import com.example.api.Model.JwtTokenProvider;
import com.example.api.Model.MyUserDetails;
import com.example.api.Repository.BookRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.api.Model.Book;
import com.example.api.Service.BookService;
import java.util.List;



@Controller
public class BookController {
    @Autowired
    BookService bookService;

    @Autowired
    private HttpServletResponse response;

    @GetMapping("/books")
    public String listBook(Model model){
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/dangki")
    public String dangky(Model model){
        return "dangki";
    }

    @GetMapping("/create-book")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new Book());
        return "create";
    }

    @PostMapping("/create-book")
    public String saveCustomer(Book book,Model model) {
        bookService.save(book);
        model.addAttribute("book",new Book());
        model.addAttribute("message", "New book created successfully");
    return "create";
    }

    @GetMapping("/edit-book/{id}")
    public String showEditForm(@PathVariable Long id,Model model){
        Book book = bookService.findById(id);
        if(book != null) {
        model.addAttribute("book", book);
        return "edit";
        }else {
            return "error.404";
        }
    }
    @PostMapping("/update-book/{id}")
    public String updateBook(@PathVariable("id") @ModelAttribute Book book,Model model){
        bookService.save(book);
        model.addAttribute("message", "Book updated successfully");
        model.addAttribute("book",book);
       return "edit";
    }

    @GetMapping("/delete-book/{id}")
    public String showDeleteForm(@PathVariable Long id,Model model){
        Book book = bookService.findById(id);
        if(book != null) {
            model.addAttribute("book",book);
            return "delete";
        }else {
            return "error.404";
        }
    }

    @PostMapping("/delete-book")
    public String deleteBook(@ModelAttribute("book") Book book){
        bookService.remove(book.getId());
        return "redirect:books";
    }
    @GetMapping("/logout")
    public String logout() {
        SecurityContextHolder.clearContext();
        return "login";
    }
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }



//    @PostMapping("/login")
//    public String login(@RequestParam String username, @RequestParam String password){
//        if (username.equals("admin") && password.equals("admin")) {
//            // Lưu thông tin xác thực của người dùng vào cookies
//            Cookie cookie = new Cookie("username", username);
//            cookie.setMaxAge(60 * 60 * 24); // 1 ngày
//            cookie.setPath("/");
//            response.addCookie(cookie);
//
//            return "redirect:/books";
//        } else {
//            return "login";
//        }
//    }
}
