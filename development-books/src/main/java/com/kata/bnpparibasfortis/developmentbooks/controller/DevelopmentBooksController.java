package com.kata.bnpparibasfortis.developmentbooks.controller;
import java.util.List;

import com.kata.bnpparibasfortis.developmentbooks.model.Books;
import com.kata.bnpparibasfortis.developmentbooks.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DevelopmentBooksController {

	@Autowired
	BookService service;

	@GetMapping("/getBooks")
	public List<Books> getAllBooks() { return service.getAllBooks(); }

}
