package TestLayer;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import DBLayer.DBSalesman;
import DBLayer.IFDBSalesman;
import ModelLayer.Salesman;

public class DBSalesmanTest {
	private Salesman salesman;
	private IFDBSalesman dbSale;
	@Before
	public void setUp() throws Exception 
	{
		salesman = new Salesman("Rasmus Andersen Borup", "121212-1211", "12345678", "Rasmus@eksempel.dk", "Rasmusvej 1", "9000", "1234567890", "+5%");
		dbSale = new DBSalesman();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInsert() {
		dbSale.insertSalesman(salesman);
	}
//	
//	@Test
//	public void testFind() {
//		System.out.println("Found: " + dbSale.findSalesman("1234567890").getName());
//	}
//	
//	@Test
//	public void testUpdate()
//	{
//		salesman.setName("Ændret navn");
//		salesman.setPaymentLevel("+20%");
//		dbSale.updateSalesman(salesman, "1234567890");
//	}
//	
//	@Test
//	public void testRemove() {
//		dbSale.removeSalesman("1234567890");
//	}
}
