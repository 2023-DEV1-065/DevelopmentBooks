package com.kata.bnpparibasfortis.developmentbooks.controller;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DevelopmentBooksController {

	@GetMapping("/getBooks")
	public List<String> getAllBooks() {
		return Arrays.asList("Clean Code", "The Clean Coder", "Clean Architecture",
				"Test-Driven Development By Example", "Working Effectively With Legacy Code");
	}

}
