package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;


import pl.edu.agh.mwo.invoice.product.OtherProduct;
import pl.edu.agh.mwo.invoice.product.Product;
import pl.edu.agh.mwo.invoice.product.TaxFreeProduct;

public class Invoice {
	
	private HashMap<Product, Integer> products = new HashMap<Product, Integer>();

	public void addProduct(Product product) {
		// TODO: implement
		if (products.containsKey(product)) {
			products.replace(product, (products.get(product) + 1));
		} else {
			products.put(product, 1);
		}
	}

	public void addProduct(Product product, Integer quantity) {
		if (products.containsKey(product)) {
			products.replace(product, (products.get(product) + 1));
		} else {
			products.put(product, 1);
		}
	}

	public BigDecimal getSubtotal() {
		if (products.isEmpty()) {
			return BigDecimal.ZERO;
		} else {

			BigDecimal subTotal = BigDecimal.ZERO;

			for (Product product : products.keySet()) {

				BigDecimal sum = product.getPrice().multiply(new BigDecimal(products.get(product)));

				subTotal = subTotal.add(sum);
			}
			return subTotal;
		}

		}

	

	public BigDecimal getTax() {
		if (products.isEmpty()) {
			return BigDecimal.ZERO;
		} else {
			BigDecimal getTax = BigDecimal.ZERO;

			for (Product product : products.keySet()) {
				BigDecimal taxSum = product.getTaxPercent().multiply(new BigDecimal(products.get(product)))
						.multiply(product.getPrice());
				getTax = getTax.add(taxSum);

			}
			return getTax;

		}
	}

	public BigDecimal getTotal() {
		if (products.isEmpty()) {
			return BigDecimal.ZERO;
		}
		BigDecimal total = BigDecimal.ZERO;

		for (Product product : products.keySet()) {
			BigDecimal sum = product.getPriceWithTax().multiply(new BigDecimal(products.get(product)));
			total = total.add(sum);

		}
		return total;
	}
	public static void main (String[]args){
		
		HashMap<Product, Integer> myProduct = new HashMap<Product, Integer>();
		Product maslo = new OtherProduct("masloZ", new BigDecimal(10));
		Product chleb = new TaxFreeProduct("chlebK", new BigDecimal(1));
		myProduct.put( maslo, 2);
		myProduct.put( chleb, 1);
		Invoice invoice = new Invoice();
		invoice.getSubtotal();
		
	}
}
