package TestLayer;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import DBLayer.DBCustomer;
import DBLayer.IFDBCustomer;
import ModelLayer.Customer;

public class DBCustomerTest {
	private Customer customer;
	private IFDBCustomer dbCustomer;
	@Before
	public void setUp() throws Exception 
	{
		customer = new Customer("Rasmus Andersen Borup", "121212-1213", "12345678", "Rasmus@eksempel.dk", "Rasmusvej 1", "9000");
		dbCustomer = new DBCustomer();
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
//	public void testInsert() 
//	{
//		dbCustomer.insertCustomer(customer);
//	}
//	
//	@Test
//	public void testFind()
//	{
//		System.out.println("Found: " + dbCustomer.findCustomer("12345678").getName());
//	}
//	
//	@Test
//	public void testUpdate()
//	{
//		dbCustomer.updateCustomer(customer, "12345678");
//	}
//	
//	@Test
//	public void testRemove()
//	{
//		dbCustomer.removeCustomer("12345678");
//	}
}
