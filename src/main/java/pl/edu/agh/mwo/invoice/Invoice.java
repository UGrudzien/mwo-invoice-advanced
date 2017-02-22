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
		this.addProduct(product, 1);
	}

	public void addProduct(Product product, Integer quantity) {
		if (quantity <=0){
			throw new IllegalArgumentException();
		}
		if (products.containsKey(product)) {
			products.replace(product, (products.get(product) + quantity));
		} else {
			products.put(product, quantity);
		}
	}

	public BigDecimal getSubtotal() {
	

			BigDecimal subTotal = BigDecimal.ZERO;

			for (Product product : products.keySet()) {

				BigDecimal sum = product.getPrice().multiply(new BigDecimal(products.get(product)));

				subTotal = subTotal.add(sum);
			}
			return subTotal;
		}

		

	

	public BigDecimal getTax() {
			BigDecimal getTax = BigDecimal.ZERO;

			for (Product product : products.keySet()) {
				BigDecimal taxSum = product.getTaxPercent().multiply(new BigDecimal(products.get(product)))
						.multiply(product.getPrice());
				getTax = getTax.add(taxSum);

			}
			return getTax;

		}
	

	public BigDecimal getTotal() {
		
		
		BigDecimal total = BigDecimal.ZERO;

		total = total.add(this.getSubtotal()).add(this.getTax());

		return total;
	}

}
