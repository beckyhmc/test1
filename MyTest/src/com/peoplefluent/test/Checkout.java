/**
 * 
 */
package com.peoplefluent.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * @author Becky.McElroy
 *
 */
public class Checkout {

	private Map<String, Product> products = new HashMap<String, Product>();

	private void init() {

		Product apple = new Product("apple", 60, 2, 1);
		// Product apple = new Product("apple", 60, 0, 0);
		products.put("apple", apple);

		Product orange = new Product("orange", 25, 3, 2);
		// Product orange = new Product("orange", 25, 0, 0);
		products.put("orange", orange);
	}

	/**
	 * @param args Not used.
	 */
	public static void main(String[] args) {

		Checkout checkout = new Checkout();
		checkout.init();

		Scanner scan = new Scanner(System.in);

		try {
			while (true) {
				System.out.println("Enter a comma-separated list of scanned items, or 'quit' to quit.");
				System.out.print(">  ");

				String input = scan.nextLine();
				if (input.trim().equals("quit")) {
					break;
				}

				String[] scannedItems = input.split(",");

				double sum = 0;
				Map<String, Integer> itemCount = new HashMap<String, Integer>();
				for (int i = 0; i < scannedItems.length; i++) {

					// calculate full normal price, counting items along the way
					Product product = checkout.getProducts().get(scannedItems[i].trim().toLowerCase());
					if (product == null) {
						System.err.println("Ignoring unrecognized item: " + scannedItems[i]);
					} else {
						sum += product.getPrice();

						if (itemCount.get(product.getName()) == null) {
							itemCount.put(product.getName(), 1);
						} else {
							int prevCount = itemCount.get(product.getName());
							itemCount.put(product.getName(), ++prevCount);
						}
					}
				}

				double total = adjustForDiscounts(checkout, sum, itemCount);

				System.out.println("£" + total / 100);
				System.out.println();
			}

			System.out.println("bye");
			System.exit(0);

		} catch (RuntimeException e) {
			System.err.println("FATAL ERROR - " + e.getClass().getName() + " - " + e.getMessage());
			System.exit(1);
		} finally {
			scan.close();
		}
	}

	private static double adjustForDiscounts(Checkout checkout, double sum, Map<String, Integer> itemCount) {
		for (Entry<String, Integer> purchasedProd : itemCount.entrySet()) {
			Product product = checkout.getProducts().get(purchasedProd.getKey());
			int discountNum = product.getDiscountQuantity();
			if (discountNum > 0) {
				int numPurchased = purchasedProd.getValue();
				while (numPurchased >= discountNum) {
					numPurchased -= discountNum;
					int numToReimb = discountNum - product.getDiscountFactor();
					sum -= (numToReimb * product.getPrice());
				}
			}
		}

		return sum;
	}

	public Map<String, Product> getProducts() {
		return products;
	}

}
