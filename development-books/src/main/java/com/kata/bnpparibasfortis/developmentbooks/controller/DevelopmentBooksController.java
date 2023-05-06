package com.kata.bnpparibasfortis.developmentbooks.controller;

import com.kata.bnpparibasfortis.developmentbooks.model.Book;
import com.kata.bnpparibasfortis.developmentbooks.model.BookOrder;
import com.kata.bnpparibasfortis.developmentbooks.model.PriceSummary;
import com.kata.bnpparibasfortis.developmentbooks.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DevelopmentBooksController {

    @Autowired
    BookService service;

    @GetMapping("/getBooks")
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    @GetMapping("/getPrice")
    public PriceSummary getPrice(@RequestBody List<BookOrder> bookOrder) {
        return service.getPrice(bookOrder);
    }

}
