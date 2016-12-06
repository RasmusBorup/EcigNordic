package TestLayer;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import DBLayer.DBPartOrder;
import DBLayer.IFDBPartOrder;
import ModelLayer.PartOrder;
import ModelLayer.Product;

public class DBPartOrderTest 
{
	private PartOrder partOrder;
	private Product product;
	private IFDBPartOrder dbPartOrder;
	@Before
	public void setUp() throws Exception 
	{
		dbPartOrder = new DBPartOrder();
		product = new Product("10101", "Mynte", "Væske med myntesmag", 165.0, 73, 50);
		partOrder = new PartOrder(product, 2);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInsert() 
	{
		dbPartOrder.insertPartOrder(partOrder, "10101");
	}
//	
//	public void testFind()
//	{
//		PartOrder foundPartOrder = dbPartOrder.findPartOrder("10101", "10101", false);
//		System.out.println("Found partorder with price: " + foundPartOrder.getPartOrderPrice());
//	}
//	
//	public void testUpdate()
//	{
//		dbPartOrder.updataPartOrder(partOrder, "10101", "10101");
//	}
//	
//	public void testRemove()
//	{
//		dbPartOrder.removePartOrder("10101", "10101");
//	}
}
