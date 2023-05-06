package com.kata.bnpparibasfortis.developmentbooks.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.kata.bnpparibasfortis.developmentbooks.model.BookOrder;
import com.kata.bnpparibasfortis.developmentbooks.model.PriceSummary;
import com.kata.bnpparibasfortis.developmentbooks.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.kata.bnpparibasfortis.developmentbooks.model.Books;


class DevelopmentBooksControllerTest {

	private static final String BOOK_TITLE = "Test-Driven Development By Example";
	private static final int NUMBER_AVAILABLE_DIFFERENT_BOOKS = 5;
	private static final double PRICE_THREE_DIFFERENT_BOOKS = 135.0;
	private static final double SINGLE_BOOK_PRICE = 50.0;
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

	@DisplayName("validate getPrice should return the total price of the selected Books")
	@Test
	public void getPriceShouldReturnFinalPriceOfBooks() {
		List<BookOrder> shoppingBasket = new ArrayList<BookOrder>();
		shoppingBasket.add(new BookOrder(1, 1));
		shoppingBasket.add(new BookOrder(2, 1));
		shoppingBasket.add(new BookOrder(3, 1));
		PriceSummary result = controller.getPrice(shoppingBasket);
		//If you buy 3 different books, you should get a 10% discount.
		assertEquals(PRICE_THREE_DIFFERENT_BOOKS, result.getFinalPrice());
	}

	@DisplayName("validate price of each book should be fifty")
	@Test
	public void validatePriceOfEachBook() {
		List<Books> result = controller.getAllBooks();
		assertEquals(SINGLE_BOOK_PRICE, result.get(0).getPrice());
		assertEquals(SINGLE_BOOK_PRICE, result.get(1).getPrice());
		assertEquals(SINGLE_BOOK_PRICE, result.get(2).getPrice());
		assertEquals(SINGLE_BOOK_PRICE, result.get(3).getPrice());
		assertEquals(SINGLE_BOOK_PRICE, result.get(4).getPrice());
	}

}
