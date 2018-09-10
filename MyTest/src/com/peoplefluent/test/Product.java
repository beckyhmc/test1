package com.peoplefluent.test;

public class Product {
	private String name;
	private int price;
	private int discountQuantity;
	private int discountFactor;

	public Product(String name, int price, int discountQuantity, int discountFactor) {
		super();
		this.name = name;
		this.price = price;
		this.discountQuantity = discountQuantity;
		this.discountFactor = discountFactor;
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

	public int getDiscountQuantity() {
		return discountQuantity;
	}

	public void setDiscountQuantity(int discountQuantity) {
		this.discountQuantity = discountQuantity;
	}

	public int getDiscountFactor() {
		return discountFactor;
	}

	public void setDiscountFactor(int discountFactor) {
		this.discountFactor = discountFactor;
	}


}
