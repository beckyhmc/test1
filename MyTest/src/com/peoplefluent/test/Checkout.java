/**
 * 
 */
package com.peoplefluent.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Becky.McElroy
 *
 */
public class Checkout {

	private Map<String, Integer> productPrices = new HashMap<String, Integer>();

	private void init() {
		productPrices.put("apple", 60);
		productPrices.put("orange", 25);
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
				for (int i = 0; i < scannedItems.length; i++) {
					Integer price = checkout.getProductPrices().get(scannedItems[i].trim().toLowerCase());
					if (price == null) {
						System.err.println("Unrecognized item: " + scannedItems[i]);
					} else {
						sum += price;
					}
				}

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

	public Map<String, Integer> getProductPrices() {
		return productPrices;
	}

	public void setProductPrices(Map<String, Integer> prices) {
		this.productPrices = prices;
	}
}
