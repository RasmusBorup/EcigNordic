package modelLayer;
import java.util.ArrayList;

public class Customer extends Person
{
	private ArrayList<Order> orders;
	public Customer(String name, String ssn, String phoneno, String email, String address, String zipCode) 
	{
		super(name, ssn, phoneno, email, address, zipCode);
		orders = new ArrayList<Order>();
	}
	
	public Customer()
	{
		super();
	}
	
	public void addOrder(Order order)
	{
		orders.add(order);
	}
}
