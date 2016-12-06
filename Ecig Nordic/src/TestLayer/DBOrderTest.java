package TestLayer;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import DBLayer.DBOrder;
import DBLayer.IFDBOrder;
import ModelLayer.Customer;
import ModelLayer.Order;
import ModelLayer.Salesman;

public class DBOrderTest {

	private Customer customer;
	private Salesman salesman;
	private Order order;
	private IFDBOrder dbOrder;
	
	@Before
	public void setUp() throws Exception 
	{
		dbOrder = new DBOrder();
		customer = new Customer("Rasmus Andersen Borup", "121212-1213", "12345678", "Rasmus@eksempel.dk", "Rasmusvej 1", "9000");
		salesman = new Salesman("Rasmus Andersen Borup", "121212-1211", "12345678", "Rasmus@eksempel.dk", "Rasmusvej 1", "9000", "1234567890", "+5%");
		order = new Order(customer, salesman, "280514");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testInsert()
	{
		dbOrder.insertSalesOrder(order);
	}
//	
//	public void testFind()
//	{
//		System.out.println("Found order from: " + dbOrder.findSalesOrder("10101", false).getOrderDate());
//	}
//	
//	public void testUpdate()
//	{
//		dbOrder.updataSalesOrder(order, "10101");
//	}
//	
//	public void testRemove()
//	{
//		dbOrder.removeSalesOrder("10101");
//	}
}
