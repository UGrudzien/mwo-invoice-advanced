package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Random;

import pl.edu.agh.mwo.invoice.product.OtherProduct;
import pl.edu.agh.mwo.invoice.product.Product;
import pl.edu.agh.mwo.invoice.product.TaxFreeProduct;

public class Invoice {
	
	private HashMap<Product, Integer> products = new HashMap<Product, Integer>();
	private static int nextNumber=1;// static dlatego aby każdy kolejny tworzony obiekt nie miał wartości zero tylko ostatni numer z tworzonego obiektu
	private int number;
	public Invoice(){
		this.number = nextNumber;
		nextNumber+=1;
	}

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
	public Integer setInvoiceNumber(){
		Random random =new Random();
		Integer number = random.nextInt(8);
		return number;
	}

	public String printedVersion() {
		
		String printed = String.valueOf(number);
		for (Product product: products.keySet()){
			printed+= "\n" + product.getName();
			printed+= "," + product.getClass().getName();
			printed +="," + products.get(product);
			
		}
		printed +="\n" + "LICZBA PRODUKTÓW: "+ products.size();
		return printed;
	}

}
