package com.kata.bnpparibasfortis.developmentbooks.enums;

import lombok.Getter;
import lombok.Setter;

public enum BooksEnumeration  {


	CLEAN_CODE(1, "Clean Code", "Robert Martin", 2008, 50.00),
	THE_CLEAN_CODER(2, "The Clean Coder", "Robert Martin", 2011, 50.00),
	CLEAN_ARCHITECTURE(3, "Clean Architecture", "Robert Martin", 2017, 50.00),
	TEST_DRIVEN_DEVELOPMENT(4, "Test-Driven Development By Example", "Kent Beck", 2003, 50.00),
	WORKING_WITH_LEGACY_CODE(5, "Working Effectively With Legacy Code", "Michael C. Feathers", 2004, 50.00);

	@Getter
	@Setter
	private int id;
	@Getter
	@Setter
	private String title;
	@Getter
	@Setter
	private String author;
	@Getter
	@Setter
	private int year;
	@Getter
	@Setter
	private double price;

	BooksEnumeration(int id, String title, String author, int year, double price) {
		this.setId(id);
		this.setTitle(title);
		this.setAuthor(author);
		this.setYear(year);
		this.setPrice(price);
	}
}
