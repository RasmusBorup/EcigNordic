package modelLayer;
import java.util.ArrayList;
public class Salesman extends Person 
{
	private String salesmanID;
	private String paymentLevel;
	private ArrayList<Order> sales;
	

	public Salesman(String name, String ssn, String phoneno, String email, String address, String zipCode, String salesmanID, String paymentLevel) 
	{
		super(name, ssn, phoneno, email, address, zipCode);
		this.salesmanID = salesmanID;
		this.paymentLevel = paymentLevel;
		sales = new ArrayList<Order>();
	}
	
	public Salesman()
	{
		super();
	}

	public void addSale(Order order)
	{
		sales.add(order);
	}
	
	public String getSalesmanID() {
		return salesmanID;
	}

	public void setSalesmanID(String salesmanID) {
		this.salesmanID = salesmanID;
	}

	public String getPaymentLevel() {
		return paymentLevel;
	}

	public void setPaymentLevel(String paymentLevel) {
		this.paymentLevel = paymentLevel;
	}
}
