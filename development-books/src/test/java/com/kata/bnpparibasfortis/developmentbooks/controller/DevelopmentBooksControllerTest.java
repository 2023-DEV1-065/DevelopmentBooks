package com.kata.bnpparibasfortis.developmentbooks.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.kata.bnpparibasfortis.developmentbooks.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.kata.bnpparibasfortis.developmentbooks.model.Books;


class DevelopmentBooksControllerTest {

	private static final String BOOK_TITLE = "Test-Driven Development By Example";
	private static final int NUMBER_AVAILABLE_DIFFERENT_BOOKS = 5;
	DevelopmentBooksController controller;

	@BeforeEach
	public void setUp() {
		controller = new DevelopmentBooksController();
		controller.service = new BookService();
	}

	@DisplayName("validate number of the different Books is five")
	@Test
	public void getAllBooksShouldReturnFiveBooks() {
		List<Books> books = controller.getAllBooks();
		assertEquals(NUMBER_AVAILABLE_DIFFERENT_BOOKS, books.size());
	}

	@DisplayName("validate get book title should return the name of the Book")
	@Test
	void getBookTitleShouldReturnTitleOfTheBook() {
		List<Books> result = controller.getAllBooks();
		assertEquals(BOOK_TITLE, result.get(3).getTitle());
	}

}