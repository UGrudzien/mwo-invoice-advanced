package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
	private HashMap<Product, Integer> products = new HashMap<Product, Integer>();
	public void addProduct(Product product) {
		if (products.containsKey(product)){
			products.put(product, products.get(product)+1);
		}
		else{
			products.put(product, 1);
		}
	}


	public void addProduct(Product product, Integer quantity) {
		
	}

	public BigDecimal getSubtotal() {//liczy wartość faktury
		if (products.isEmpty()){
			return BigDecimal.ZERO;
		}
		else{
			BigDecimal subTot = BigDecimal.ZERO;
			for (Product product: products.keySet()){
				
				BigDecimal sum = new BigDecimal(products.get(product)).multiply(product.getPrice());
				subTot=subTot.add(sum);
			
		 }
			return subTot;
		}
	}

	public BigDecimal getTax() {
		if (products.isEmpty()){
			return BigDecimal.ZERO;
		}
		else{
		return null;
		}
	}

	public BigDecimal getTotal() {
		if (products.isEmpty()){
			return BigDecimal.ZERO;
		}
		else{
		return null;
		}
	}
}
