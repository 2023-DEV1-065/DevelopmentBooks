package com.kata.bnpparibasfortis.developmentbooks.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.kata.bnpparibasfortis.developmentbooks.model.Books;
import com.kata.bnpparibasfortis.developmentbooks.model.BookOrder;
import com.kata.bnpparibasfortis.developmentbooks.model.PriceSummary;


class BookServiceTest {

	private static final int NUMBER_AVAILABLE_DIFFERENT_BOOKS = 5;
	private static final double SINGLE_BOOK_PRICE = 50.0;
	BookService service;

	@BeforeEach
	public void setUp() {
		service = new BookService();
	}

	@DisplayName("validate number of the different Books is five")
	@Test
	public void getAllBooksShouldReturnFiveBooks() {
		List<Books> books = service.getAllBooks();
		assertEquals(NUMBER_AVAILABLE_DIFFERENT_BOOKS, books.size());
		Assertions.assertTrue(books.containsAll(getExpectedBooks()));
	}

	@DisplayName("validate obtaining of all books, should return books with the different details")
	@Test
	public void getAllBooksShouldReturnBooksWithDetails() {
		List<Books> obtainedBooks = service.getAllBooks();
		List<Books> excpectedBooks = getExpectedBooks();
		assertEquals(excpectedBooks.get(0), obtainedBooks.get(0));
		assertEquals(excpectedBooks.get(1), obtainedBooks.get(1));
		assertEquals(excpectedBooks.get(2), obtainedBooks.get(2));
		assertEquals(excpectedBooks.get(3), obtainedBooks.get(3));
		assertEquals(excpectedBooks.get(4), obtainedBooks.get(4));
	}

	private List<Books> getExpectedBooks() {
		List<Books> excpectedBooks = new ArrayList<Books>();
		excpectedBooks.add(new Books(1, "Clean Code", "Robert Martin", 2008, 50.00));
		excpectedBooks.add(new Books(2, "The Clean Coder", "Robert Martin", 2011, 50.00));
		excpectedBooks.add(new Books(3, "Clean Architecture", "Robert Martin", 2017, 50.00));
		excpectedBooks.add(new Books(4, "Test-Driven Development By Example", "Kent Beck", 2003, 50.00));
		excpectedBooks.add(new Books(5, "Working Effectively With Legacy Code", "Michael C. Feathers", 2004, 50.00));
		return excpectedBooks;
	}

	@DisplayName("validate getPrice should return the total price of the selected Books")
	@Test
	public void getPriceShouldReturnPriceOfBooks() {
		List<BookOrder> shoppingBasket = new ArrayList<BookOrder>();
		shoppingBasket.add(new BookOrder(2, 1));
		PriceSummary result = service.getPrice(shoppingBasket);
		// No discount if you buy only one book
		assertEquals(SINGLE_BOOK_PRICE, result.getFinalPrice());
	}

}
