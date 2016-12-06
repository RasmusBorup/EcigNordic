package TestLayer;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import DBLayer.DBProduct;
import DBLayer.IFDBProduct;
import ModelLayer.Product;

public class DBProductTest 
{
	private IFDBProduct dbProduct;
	private Product product;

	@Before
	public void setUp() throws Exception 
	{
		dbProduct = new DBProduct();
		product = new Product("10103", "Frugt", "Væske med frugtsmag", 150.0, 73, 50);
	}

	@After
	public void tearDown() throws Exception 
	{
	}

	@Test
//	public void testInsert() 
//	{
//		dbProduct.insertProduct(product);
//	}
//	
//	public void testFind()
//	{
//		System.out.println("Found: " + dbProduct.findProduct("10103").getDescription());
//	}
//	
//	public void testUpdate()
//	{
//		dbProduct.updataProduct(product, "10103");
//	}
	
	public void testRemove()
	{
		dbProduct.removeProduct("10103");
	}
}
