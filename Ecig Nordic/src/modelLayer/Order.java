package modelLayer;
import java.util.ArrayList;
public class Order 
{
	private int orderNO;
	private static int orderCount = 10100;
	private Customer customer;
	private Salesman salesman;
	private ArrayList<PartOrder> partOrders;
	private double totalPrice;
	private String orderDate;

	public Order(Customer customer, Salesman salesman, String orderDate)
	{
		this.customer = customer;
		this.salesman = salesman;
		this.orderDate = orderDate;
		this.totalPrice = 0;
		orderNO = calculateOrderNO();
	}
	
	public Order()
	{
		
	}
	
	public int calculateOrderNO()
	{
		orderCount++;
		return orderCount;
	}
	
	public double calculateTotalPrice()
	{
		double totalPrice = 0;
		for(PartOrder partOrder : partOrders)
		{
			totalPrice += partOrder.getPartOrderPrice();
		}
		return totalPrice;
	}
	
	public void addPartOrder(PartOrder partOrder)
	{
		partOrders.add(partOrder);
		totalPrice = calculateTotalPrice();
	}

	public int getOrderNO() {
		return orderNO;
	}
	
	public void setOrderNO(int orderNO) {
		this.orderNO = orderNO;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Salesman getSalesman() {
		return salesman;
	}
	
	public void setSalesman(Salesman salesman) {
		this.salesman = salesman;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public String getOrderDate() {
		return orderDate;
	}
	
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
}
