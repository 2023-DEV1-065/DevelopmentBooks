package com.kata.bnpparibasfortis.developmentbooks.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.kata.bnpparibasfortis.developmentbooks.enums.BooksEnumeration;
import com.kata.bnpparibasfortis.developmentbooks.model.BookOrder;
import com.kata.bnpparibasfortis.developmentbooks.model.Books;
import com.kata.bnpparibasfortis.developmentbooks.model.PriceSummary;
import org.springframework.stereotype.Service;


@Service
public class BookService {
	private static final double SINGLE_BOOK_PRICE = 50.0;
	private static final double ONE_HUNDRED = 100;
	public List<Books> getAllBooks() {
		return Arrays.stream(BooksEnumeration.values()).map(bookEnum -> new Books(bookEnum.getId(), bookEnum.getTitle(),
				bookEnum.getAuthor(), bookEnum.getYear(), bookEnum.getPrice())).collect(Collectors.toList());
	}

	public PriceSummary getPrice(List<BookOrder> books) {
		if (books.size() == 1) {
			return createPriceSummaryWithoutDiscount(books.get(0));
		} else {
			return createPriceSummaryWithDiscount(books);
		}
	}
	private PriceSummary createPriceSummaryWithoutDiscount(BookOrder booksInput) {
		double totalPrice = SINGLE_BOOK_PRICE * booksInput.getQuantity();
		PriceSummary priceSummary = new PriceSummary();
		priceSummary.setActualPrice(totalPrice);
		priceSummary.setFinalPrice(totalPrice);
		priceSummary.setTotalBooks(booksInput.getQuantity());
		priceSummary.setTotalDiscount(0);
		return priceSummary;
	}

	private PriceSummary createPriceSummaryWithDiscount(List<BookOrder> books) {
		int totalBooks = books.stream().mapToInt(BookOrder::getQuantity).sum();
		List<Integer> bookGroups = new ArrayList<>();
		double priceOfSimilarBooksLeft = 0;
		int noOfGroups = 1 + (totalBooks / books.size());
		double finalPrice = 0;

		for (int i = 0; i < noOfGroups; i++) {
			int typesOfBookLeft = (int) books.stream().filter(book -> book.getQuantity() > 0).count();
			if (typesOfBookLeft > 1) {
				bookGroups.add(typesOfBookLeft);
				reduceQuantityOfAddedBooksIntoGroups(books);
			} else {
				priceOfSimilarBooksLeft = calculatePriceWithoutDiscount(books);
				break;
			}
		}

		optimizeGroups(bookGroups);
		finalPrice = priceOfSimilarBooksLeft
				+ bookGroups.stream().mapToDouble(this::calculateDiscountAccordingToNumOfBooksInGroup).sum();
		return createPriceSummary(totalBooks, finalPrice);
	}
	private void reduceQuantityOfAddedBooksIntoGroups(List<BookOrder> books) {
		books.forEach(book -> book.setQuantity(book.getQuantity() - 1));
	}
	private double calculatePriceWithoutDiscount(List<BookOrder> books) {
		return books.stream().filter(book -> book.getQuantity() > 0)
				.mapToDouble(book -> book.getQuantity() * SINGLE_BOOK_PRICE).sum();
	}

	private void optimizeGroups(List<Integer> groups) {
		for (int i = 0; i < groups.size(); i++) {
			Integer group = groups.get(i);
			if (group == 5 && groups.contains(3)) {
				groups.set(i, 4);
				groups.set(groups.indexOf(3), 4);
			}
		}
	}
	private double calculateDiscountAccordingToNumOfBooksInGroup(int groupSize) {
		double discountedPrice = 0;
		double actualCost = groupSize * SINGLE_BOOK_PRICE;
		if (groupSize == 1)
			return SINGLE_BOOK_PRICE;
		if (groupSize == 2)
			return actualCost * (1 - (5.0 / ONE_HUNDRED));
		if (groupSize == 3)
			return actualCost * (1 - (10.0 / ONE_HUNDRED));
		if (groupSize == 4)
			return actualCost * (1 - (20.0 / ONE_HUNDRED));
		if (groupSize == 5)
			return actualCost * (1 - (25.0 / ONE_HUNDRED));
		return discountedPrice;
	}
	private PriceSummary createPriceSummary(int totalBooks, double finalPrice) {
		PriceSummary priceSummary = new PriceSummary();
		priceSummary.setActualPrice(SINGLE_BOOK_PRICE * totalBooks);
		priceSummary.setFinalPrice(finalPrice);
		priceSummary.setTotalBooks(totalBooks);
		priceSummary.setTotalDiscount(priceSummary.getActualPrice() - priceSummary.getFinalPrice());
		return priceSummary;
	}

}
