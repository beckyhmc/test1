/**
 * 
 */
package com.peoplefluent.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiConsumer;

/**
 * @author Becky.McElroy
 *
 */
public class Checkout {

	private Map<String, Product> products = new HashMap<String, Product>();

	private void init() {

		Map<Integer, Integer> appleOffer = new HashMap<Integer, Integer>();
		appleOffer.put(2, 1); // <quantity, price factor>
		Product apple = new Product("apple", 60, appleOffer);
		products.put("apple", apple);

		Map<Integer, Integer> orangeOffer = new HashMap<Integer, Integer>();
		orangeOffer.put(3, 2); // <quantity, price factor>
		Product orange = new Product("orange", 25, orangeOffer);
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
						System.err.println("Unrecognized item: " + scannedItems[i]);
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
				
				// adjust for special offers
				itemCount.forEach(new BiConsumer<String, Integer>() {

					@Override
					public void accept(String productName, Integer num) {
						Product product = checkout.getProducts().get(productName);

						Map<Integer, Integer> offer = product.getSpecialOffer();
						if (offer != null) {

						}
					}

				});

				System.out.println("£" + sum / 100);
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

	public Map<String, Product> getProducts() {
		return products;
	}

}
