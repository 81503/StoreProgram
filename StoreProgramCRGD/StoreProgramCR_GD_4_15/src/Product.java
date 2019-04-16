public class Product
{
	private String product;
	private int pricePerGram;
	private int quantity;
	private String effects;
	private String flammable;
	
	public Product() {
		super();
		product = "null";
		pricePerGram = -1;
		quantity = -1;
		effects = "null";
	}
	

	public Product(String product, float pricePerKGram, int quantity, String effects) {
		super();
		this.product = product;
		this.pricePerGram = pricePerGram;
		this.quantity = quantity;
		this.effects = effects;
	}


	public String getProduct() {
		return product;
	}


	public void setProduct(String product) {
		this.product = product;
	}


	public int getPricePerGram() {
		return pricePerGram;
	}


	public void setPricePerGram(int pricePerGram) {
		this.pricePerGram = pricePerGram;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public String getEffects() {
		return effects;
	}


	public void setEffects(String effects) {
		this.effects = effects;
	}


	public String getFlammable() {
		return flammable;
	}


	public void setFlammable(String Flammable) {
		this.flammable = Flammable;
	}


	@Override
	public String toString() {
		return "\nProduct : " + product + ", \nPricePerGram : " + pricePerGram + ", \nQuantity : " + quantity
				+ "\n" + effects + ", " + flammable;
	}

	
	
	
}
