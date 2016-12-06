package modelLayer;

public class PartOrder 
{
	private Product product;
	private int amount;
	private double partOrderPrice;
	
	public PartOrder(Product product, int amount)
	{
		this.product = product;
		this.amount = amount;
		this.partOrderPrice = calculatePartOrderPrice();
	}
	
	public PartOrder()
	{
		
	}
	
	public double calculatePartOrderPrice()
	{
		return product.getPrice() * amount;
	}

	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public double getPartOrderPrice() {
		return partOrderPrice;
	}
	
	public void setPartOrderPrice(double partOrderPrice)
	{
		this.partOrderPrice = partOrderPrice;
	}
}
