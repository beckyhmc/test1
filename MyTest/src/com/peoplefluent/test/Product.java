package com.peoplefluent.test;

import java.util.Map;

public class Product {
	private String name;
	private int price;
	private Map<Integer, Integer> specialOffer; // <quantity, price factor>

	public Product(String name, int price, Map<Integer, Integer> specialOffer) {
		super();
		this.name = name;
		this.price = price;
		this.specialOffer = specialOffer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Map<Integer, Integer> getSpecialOffer() {
		return specialOffer;
	}

	public void setSpecialOffer(Map<Integer, Integer> specialOffer) {
		this.specialOffer = specialOffer;
	}

}
