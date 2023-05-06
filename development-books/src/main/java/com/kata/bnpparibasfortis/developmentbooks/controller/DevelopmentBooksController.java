package com.kata.bnpparibasfortis.developmentbooks.controller;
import java.util.List;

import com.kata.bnpparibasfortis.developmentbooks.model.BookOrder;
import com.kata.bnpparibasfortis.developmentbooks.model.Books;
import com.kata.bnpparibasfortis.developmentbooks.model.PriceSummary;
import com.kata.bnpparibasfortis.developmentbooks.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DevelopmentBooksController {

	@Autowired
	BookService service;

	@GetMapping("/getBooks")
	public List<Books> getAllBooks() { return service.getAllBooks(); }

	@PostMapping("/getPrice")
	public PriceSummary getPrice(@RequestBody List<BookOrder> bookOrder) {
		return service.getPrice(bookOrder);
	}

}
