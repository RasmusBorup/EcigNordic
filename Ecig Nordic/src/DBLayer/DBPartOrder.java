package DBLayer;

import java.sql.*;

import ModelLayer.Customer;
import ModelLayer.PartOrder;
import ModelLayer.Product;

public class DBPartOrder implements IFDBPartOrder 
{
	private Connection con;
	private DBProduct dbProduct;
	
	public DBPartOrder()
	{
		con = DBConnection.getInstance().getDBCon();
		dbProduct = new DBProduct();
	}
	
	@Override
	public int insertPartOrder(PartOrder partOrder, String orderNO) 
	{
		String productEAN = partOrder.getProduct().getEAN();
		int amount = partOrder.getAmount();
		double partOrderPrice = partOrder.getPartOrderPrice();
		
		String insert = "INSERT INTO PartOrder(orderNO, productEAN, amount, partOrderPrice) " +
						"VALUES('" + orderNO + "', '" + productEAN + "', '" + amount + "', '" + partOrderPrice + "')";
		System.out.println(insert);
		
		return executeQuery(insert);
	}

	@Override
	public PartOrder findPartOrder(String orderNO, String productEAN, boolean retrieveAssociation) {
		PartOrder partOrder = singleWhere("orderNO = " + orderNO + " AND productEAN = " + productEAN, retrieveAssociation);
		return partOrder;
	}

	@Override
	public int updataPartOrder(PartOrder partOrder, String orderNO, String oldProductEAN) {
		String productEAN = partOrder.getProduct().getEAN();
		int amount = partOrder.getAmount();
		double partOrderPrice = partOrder.getPartOrderPrice();
		
		String update = "UPDATE PartOrder SET productEAN = '" + productEAN + "', amount = '" + amount + "', partOrderPrice = '" + partOrderPrice + 
						"' WHERE orderNO = " + orderNO + "AND productEAN =" + oldProductEAN;

		return executeQuery(update);
	}

	@Override
	public int removePartOrder(String orderNO, String productEAN) {
		String remove = "DELETE PartOrder Where orderNO = " + orderNO + " AND productEAN = " + productEAN;
		return executeQuery(remove);
	}	
	
	private int executeQuery(String query)
	{
		//operationResponse is made to tell if any rows were modified if any and is -1 if no statement was run
		int operationResponse = -1;
		
		//Object that runs a statement
		PreparedStatement pstmt = null;

		System.out.println(query);
		
		//Try executing
		try {
			pstmt = con.prepareStatement(query);
			operationResponse = pstmt.executeUpdate();
		}
		//Catch sql exception
		catch (SQLException sqlE) {
			System.out.println("SQL Error");
			System.out.println(sqlE.getMessage());
		}
		//catch java exceptions
		catch (Exception e) {
			e.getMessage();
		}
		System.out.println(operationResponse);
		return operationResponse;
	}
	
	//singlewhere is used when only one partOrder object is to be build TODO make it possible to build associated objects as well
	private PartOrder singleWhere(String wClause, boolean retrieveAssociation) {
		ResultSet results;
		PartOrder partOrder = new PartOrder();
		String query = buildQuery(wClause);
		System.out.println("DBPartOrder -singleWhere " + query);
		try { // read from partOrder
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			if (results.next()) {
				partOrder = buildPartOrder(results, retrieveAssociation);
			}//end if
			stmt.close();
		}//end try
		catch (Exception c) {
			System.out.println("Query exception - select partOrder : " + c);
			c.printStackTrace();
		}
		return partOrder;
	}

	//Method to build a partOrder object
	private PartOrder buildPartOrder(ResultSet results, boolean retrieveAssociation) 
	{
		PartOrder partOrder = new PartOrder();

		try {
			if(retrieveAssociation)
			{
			partOrder.setProduct(dbProduct.findProduct(results.getString(1)));
			}
			else
			{
				partOrder.setProduct(new Product());
			}
			partOrder.setAmount(Integer.parseInt(results.getString(3)));
			partOrder.setPartOrderPrice(Double.parseDouble(results.getString(4)));
		} 
		catch (Exception c) {
			System.out.println("building partOrder object");
		}

		return partOrder;
	}

	//method to build the query
	private String buildQuery(String wClause) 
	{
		String query = "SELECT * FROM PartOrder";

		if (wClause.length() > 0) query = query + " WHERE " + wClause;

		return query;
	}

}
