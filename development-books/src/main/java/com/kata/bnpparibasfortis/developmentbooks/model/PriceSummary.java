package com.kata.bnpparibasfortis.developmentbooks.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PriceSummary {

	private int totalBooks;

	private double actualPrice;

	private double totalDiscount;

	private double finalPrice;

}
