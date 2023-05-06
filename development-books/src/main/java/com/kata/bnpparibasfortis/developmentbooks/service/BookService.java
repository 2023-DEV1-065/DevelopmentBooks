package com.kata.bnpparibasfortis.developmentbooks.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.kata.bnpparibasfortis.developmentbooks.enums.BooksEnumeration;
import com.kata.bnpparibasfortis.developmentbooks.model.Books;
import org.springframework.stereotype.Service;


@Service
public class BookService {

	public List<Books> getAllBooks() {
		return Arrays.stream(BooksEnumeration.values()).map(bookEnum -> new Books(bookEnum.getId(), bookEnum.getTitle(),
				bookEnum.getAuthor(), bookEnum.getYear(), bookEnum.getPrice())).collect(Collectors.toList());
	}
}
