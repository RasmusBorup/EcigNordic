package modelLayer;

public class Product 
{
	private String EAN;
	private String name;
	private String description;
	private double price;
	private int stock;
	private int minStock;
	
	
	public Product(String EAN, String name, String description, double price, int stock, int minStock)
	{
		this.EAN = EAN;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.minStock = minStock;
	}
	
	public Product()
	{}
	
	public String getEAN() {
		return EAN;
	}

	public void setEAN(String eAN) {
		EAN = eAN;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getMinStock() {
		return minStock;
	}

	public void setMinStock(int minStock) {
		this.minStock = minStock;
	}
}
