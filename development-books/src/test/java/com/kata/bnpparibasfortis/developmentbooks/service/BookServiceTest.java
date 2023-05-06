package com.kata.bnpparibasfortis.developmentbooks.service;

import com.kata.bnpparibasfortis.developmentbooks.exception.InvalidBookInputException;
import com.kata.bnpparibasfortis.developmentbooks.model.Book;
import com.kata.bnpparibasfortis.developmentbooks.model.BookOrder;
import com.kata.bnpparibasfortis.developmentbooks.model.PriceSummary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;


class BookServiceTest {

    private static final int NUMBER_AVAILABLE_DIFFERENT_BOOKS = 5;
    private static final double SINGLE_BOOK_PRICE = 50.0;
    private static final double PRICE_TWO_DIFFERENT_BOOKS = 95.0;
    private static final double PRICE_THREE_DIFFERENT_BOOKS = 135.0;
    private static final double PRICE_FOUR_DIFFERENT_BOOKS = 160.0;
    private static final double PRICE_FIVE_DIFFERENT_BOOKS = 187.5;
    BookService service;

    @BeforeEach
    public void setUp() {
        service = new BookService();
    }

    @DisplayName("validate number of the different Book is five")
    @Test
    void getAllBooksShouldReturnFiveBooks() {
        List<Book> books = service.getAllBooks();
        assertEquals(NUMBER_AVAILABLE_DIFFERENT_BOOKS, books.size());
        Assertions.assertTrue(books.containsAll(getExpectedBooks()));
    }

    @DisplayName("validate obtaining of all books, should return books with the different details")
    @Test
    void getAllBooksShouldReturnBooksWithDetails() {
        List<Book> obtainedBooks = service.getAllBooks();
        List<Book> excpectedBooks = getExpectedBooks();
        assertEquals(excpectedBooks.get(0), obtainedBooks.get(0));
        assertEquals(excpectedBooks.get(1), obtainedBooks.get(1));
        assertEquals(excpectedBooks.get(2), obtainedBooks.get(2));
        assertEquals(excpectedBooks.get(3), obtainedBooks.get(3));
        assertEquals(excpectedBooks.get(4), obtainedBooks.get(4));
    }

    @DisplayName("validate getPrice should return the total price of the selected Book")
    @Test
    void getPriceShouldReturnPriceOfBooks() {
        List<BookOrder> shoppingBasket = new ArrayList<BookOrder>();
        shoppingBasket.add(new BookOrder(2, 1));
        PriceSummary result = service.getPrice(shoppingBasket);
        // No discount if you buy only one book
        assertEquals(SINGLE_BOOK_PRICE, result.getFinalPrice());
    }

    @DisplayName("validate getPrice should return five percent discounted price for two different books")
    @Test
    void getPriceShouldReturnFivePercentDiscountedPriceForTwoDifferentBooks() {
        List<BookOrder> shoppingBasket = new ArrayList<BookOrder>();
        shoppingBasket.add(new BookOrder(1, 1));
        shoppingBasket.add(new BookOrder(2, 1));
        PriceSummary result = service.getPrice(shoppingBasket);
        // You buy two different books from the series, you get a 5% discount on those two books.
        assertEquals(PRICE_TWO_DIFFERENT_BOOKS, result.getFinalPrice());
    }

    @Test
    @DisplayName("validate getPrice should return ten percent discounted price for three different books")
    void getPriceShouldReturnTenPercentDiscountedPriceForThreeDifferentBooks() {
        List<BookOrder> shoppingBasket = new ArrayList<BookOrder>();
        shoppingBasket.add(new BookOrder(1, 1));
        shoppingBasket.add(new BookOrder(2, 1));
        shoppingBasket.add(new BookOrder(3, 1));
        PriceSummary result = service.getPrice(shoppingBasket);
        // If you buy 3 different books, you get a 10% discount.
        assertEquals(PRICE_THREE_DIFFERENT_BOOKS, result.getFinalPrice(), 0.0);
    }

    @DisplayName("validate getPrice should return twenty percent discounted price for four different books")
    @Test
    void getPriceShouldReturnTwentyPercentDiscountedPriceForFourDifferentBooks() {
        List<BookOrder> shoppingBasket = new ArrayList<BookOrder>();
        shoppingBasket.add(new BookOrder(1, 1));
        shoppingBasket.add(new BookOrder(2, 1));
        shoppingBasket.add(new BookOrder(3, 1));
        shoppingBasket.add(new BookOrder(4, 1));
        PriceSummary result = service.getPrice(shoppingBasket);
        // With 4 different books, you get a 20% discount.
        assertEquals(PRICE_FOUR_DIFFERENT_BOOKS, result.getFinalPrice(), 0.0);
    }

    @DisplayName("validate getPrice should return twenty five percent discounted price for five different books")
    @Test
    void getPriceShouldReturnTwentyFivePercentDiscountedPriceForFiveDifferentBooks() {
        List<BookOrder> shoppingBasket = new ArrayList<BookOrder>();
        shoppingBasket.add(new BookOrder(1, 1));
        shoppingBasket.add(new BookOrder(2, 1));
        shoppingBasket.add(new BookOrder(3, 1));
        shoppingBasket.add(new BookOrder(4, 1));
        shoppingBasket.add(new BookOrder(5, 1));
        PriceSummary result = service.getPrice(shoppingBasket);
        // if you buy all 5, you get a huge 25% discount.
        assertEquals(PRICE_FIVE_DIFFERENT_BOOKS, result.getFinalPrice());
    }

    @DisplayName("validate getPrice should return the actual price without any discounted for multiple books of same type")
    @Test
    void getPriceShouldReturnActualPriceWithoutAnyDiscountedForMultipleBooksOfSameType() {
        List<BookOrder> shoppingBasket = new ArrayList<BookOrder>();
        shoppingBasket.add(new BookOrder(1, 5));
        PriceSummary result = service.getPrice(shoppingBasket);
        // if you buy 5 examples of the same book, you will not get discount.
        assertEquals(250.0, result.getFinalPrice());
    }

    @DisplayName("validate getPrice should return discounted price after grouping books for maximum discount")
    @Test
    void getPriceShouldReturnDiscountedPriceAfterGroupingBooksForMaximumDiscount() {
        List<BookOrder> shoppingBasket = new ArrayList<BookOrder>();
        shoppingBasket.add(new BookOrder(1, 2));
        shoppingBasket.add(new BookOrder(2, 2));
        shoppingBasket.add(new BookOrder(3, 2));
        shoppingBasket.add(new BookOrder(4, 2));
        shoppingBasket.add(new BookOrder(5, 2));
        PriceSummary result = service.getPrice(shoppingBasket);
        assertEquals(375.0, result.getFinalPrice());
    }

    @DisplayName("validate price of each book should be fifty")
    @Test
    void validatePriceOfEachBook() {
        List<Book> result = service.getAllBooks();
        assertEquals(SINGLE_BOOK_PRICE, result.get(0).getPrice());
        assertEquals(SINGLE_BOOK_PRICE, result.get(1).getPrice());
        assertEquals(SINGLE_BOOK_PRICE, result.get(2).getPrice());
        assertEquals(SINGLE_BOOK_PRICE, result.get(3).getPrice());
        assertEquals(SINGLE_BOOK_PRICE, result.get(4).getPrice());
    }

    @DisplayName("validate getPrice should return discounted price after grouping books into groups with similar books left with no discount")
    @Test
    void getPriceShouldReturnDiscountedPriceAfterGroupingBooksIntoGroupsWithSimilarBooksLeftWithNoDiscount() {
        List<BookOrder> shoppingBasket = new ArrayList<BookOrder>();
        shoppingBasket.add(new BookOrder(1, 2));
        shoppingBasket.add(new BookOrder(2, 2));
        shoppingBasket.add(new BookOrder(3, 2));
        shoppingBasket.add(new BookOrder(4, 2));
        shoppingBasket.add(new BookOrder(5, 12));
        PriceSummary result = service.getPrice(shoppingBasket);
        assertEquals(875.0, result.getFinalPrice());
    }

    @DisplayName("validate getPrice should return discounted price after optimizing grouping of books")
    @Test
    void getPriceShouldReturnMaximumDiscountedPriceAfterOptimizingGroupingOfBooks() {
        List<BookOrder> shoppingBasket = new ArrayList<BookOrder>();
        shoppingBasket.add(new BookOrder(1, 2));
        shoppingBasket.add(new BookOrder(2, 2));
        shoppingBasket.add(new BookOrder(3, 2));
        shoppingBasket.add(new BookOrder(4, 1));
        shoppingBasket.add(new BookOrder(5, 1));
        PriceSummary result = service.createPriceSummaryWithDiscount(shoppingBasket);
        assertEquals(320.0, result.getFinalPrice(), 0.0);
    }

    @DisplayName("validate getPrice should throw invalid book input exception if invalid book id provided")
    @Test
    void getPriceShouldThrowInvalidBookInputExceptionIfInvalidIdProvided() {
        List<BookOrder> shoppingBasket = new ArrayList<BookOrder>();
        shoppingBasket.add(new BookOrder(4, 1));
        shoppingBasket.add(new BookOrder(6, 1));
        InvalidBookInputException exception = assertThrows(InvalidBookInputException.class, () -> {
            service.getPrice(shoppingBasket);
        });
        String expectedMessage = "Provided book Id is invalid! Please select only from the available book Id's.";
        Assertions.assertTrue(exception.getMessage().equals(expectedMessage));
    }

    @DisplayName("validate getPrice should throw invalid book input exception if invalid quantity provided")
    @Test
    void getPriceShouldThrowInvalidBookInputExceptionIfInvalidQuantityIsProvided() {
        List<BookOrder> shoppingBasket = new ArrayList<BookOrder>();
        shoppingBasket.add(new BookOrder(2, 1));
        shoppingBasket.add(new BookOrder(3, 0));
        InvalidBookInputException exception = assertThrows(InvalidBookInputException.class, () -> {
            service.getPrice(shoppingBasket);
        });
        String expectedMessage = "Provided book quantity is Invalid! Please select more than zero.";
        Assertions.assertTrue(exception.getMessage().equals(expectedMessage));
    }

    @DisplayName("validate getPrice should throw invalid book input exception if repeated book Ids are provided")
    @Test
    void getPriceShouldThrowInvalidBookInputExceptionIfRepeatedBookIdsAreProvided() {
        List<BookOrder> shoppingBasket = new ArrayList<BookOrder>();
        shoppingBasket.add(new BookOrder(3, 1));
        shoppingBasket.add(new BookOrder(3, 2));
        InvalidBookInputException exception = assertThrows(InvalidBookInputException.class, () -> {
            service.getPrice(shoppingBasket);
        });
        String expectedMessage = "Provided book Ids are invalid! please do not repeat any provided book Id.";
        Assertions.assertTrue(exception.getMessage().equals(expectedMessage));
    }

    private List<Book> getExpectedBooks() {
        List<Book> excpectedBooks = new ArrayList<Book>();
        excpectedBooks.add(new Book(1, "Clean Code", "Robert Martin", 2008, 50.00));
        excpectedBooks.add(new Book(2, "The Clean Coder", "Robert Martin", 2011, 50.00));
        excpectedBooks.add(new Book(3, "Clean Architecture", "Robert Martin", 2017, 50.00));
        excpectedBooks.add(new Book(4, "Test-Driven Development By Example", "Kent Beck", 2003, 50.00));
        excpectedBooks.add(new Book(5, "Working Effectively With Legacy Code", "Michael C. Feathers", 2004, 50.00));
        return excpectedBooks;
    }

}
