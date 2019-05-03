public class Product
{
	private String product;
	private float pricePerKGram;
	private int quantity;
	private int rating;
	private float temp;
	public String getProduct;
	
	public Product() {
		super();
		product = "null";
		pricePerKGram = -1;
		quantity = -1;
		rating = -1;
	}
	

	public Product(String product, float pricePerKGram, int quantity, String effects) {
		super();
		this.product = product;
		this.pricePerKGram = pricePerKGram;
		this.quantity = quantity;
		this.rating = rating;
	}

	public String getProductName() {
		return product;
	}


	public void setProductName (String product) {
		this.product = product;
	}


	public float getPricePerKGram() {
		return pricePerKGram;
	}


	public void setPricePerKGram(float pricePerKGram) {
		this.pricePerKGram = pricePerKGram;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public int getRating() {
		return rating;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}


	public float getTemp() {
		return temp;
	}


	public void setTemp(float temp) {
		this.temp = temp;
	}


	@Override
	public String toString() {
		return "\n| Product: " + product + " \n| PricePerKilogram : " + pricePerKGram + " \n| Quantity : " + quantity
				+ "\n| Hazard Rating: " + rating + "\n| Storing Temperature: " + temp +"°C";
	}

	
	
	
}
