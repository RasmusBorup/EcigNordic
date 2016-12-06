package DBLayer;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ModelLayer.Customer;
import ModelLayer.Order;
import ModelLayer.Salesman;


public class DBOrder implements IFDBOrder 
{
	private Connection con;
	private DBCustomer dbCust;
	private DBSalesman dbSalesman;
	
	
	public DBOrder()
	{
		con = DBConnection.getInstance().getDBCon();
		dbCust = new DBCustomer();
		dbSalesman = new DBSalesman();
	}

	@Override
	public int insertSalesOrder(Order order) 
	{
		int orderNO = order.getOrderNO();
		String customerPhoneno = order.getCustomer().getPhoneno();
		String salesmanID = order.getSalesman().getSalesmanID();
		double totalPrice = order.getTotalPrice();
		String insert = "";
		//Convert date string to sql date
		try {
			String startDate = order.getOrderDate();
			SimpleDateFormat simpleDate = new SimpleDateFormat("ddMMyy");
			java.util.Date parsedOrderDate = simpleDate.parse(startDate);
			java.sql.Date sqlDate = new java.sql.Date(parsedOrderDate.getTime());
		
			insert = "INSERT INTO salesOrder(orderNO, customerphoneno, SalesmanID, totalPrice, orderDate) " +
					 "VALUES('" + orderNO + "', '" + customerPhoneno + "', '" + salesmanID + "', '" + totalPrice + "', '" + sqlDate + "')";
			System.out.println(insert);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return executeQuery(insert);
	}

	@Override
	public Order findSalesOrder(String orderNO, boolean retrieveAssociation) 
	{
		Order order = singleWhere("orderNO = " + orderNO, retrieveAssociation);
		return order;
	}

	@Override
	public int updataSalesOrder(Order order, String orderNO) 
	{
		String customerPhoneno = order.getCustomer().getPhoneno();
		String salesmanID = order.getSalesman().getSalesmanID();
		double totalPrice = order.getTotalPrice();
		String update = "";
		//Convert date string to sql date
		try {
			String startDate = order.getOrderDate();
			SimpleDateFormat simpleDate = new SimpleDateFormat("ddMMyy");
			java.util.Date parsedOrderDate = simpleDate.parse(startDate);
			java.sql.Date sqlDate = new java.sql.Date(parsedOrderDate.getTime());
			
			update = "UPDATE salesOrder SET customerPhoneno = '" + customerPhoneno + "', salesmanID = '" + salesmanID + "', totalPrice = '" + totalPrice + "', orderDate = '" + sqlDate + "' " +
						    "WHERE orderNO = " + orderNO;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return executeQuery(update);
	}

	@Override
	public int removeSalesOrder(String orderNO) 
	{
		String remove = "DELETE salesOrder Where orderNO = " + orderNO;

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
	
	//singlewhere is used when only one order object is to be build TODO make it possible to build associated objects as well
	private Order singleWhere(String wClause, boolean retrieveAssociation) {
		ResultSet results;
		Order order = new Order();
		String query = buildQuery(wClause);
		System.out.println("DBOrder -singleWhere " + query);
		try { // read from order
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			if (results.next()) {
				order = buildOrder(results, retrieveAssociation);
			}//end if
			stmt.close();
		}//end try
		catch (Exception c) {
			System.out.println("Query exception - select order : " + c);
			c.printStackTrace();
		}
		return order;
	}

	//Method to build a order object
	private Order buildOrder(ResultSet results, boolean retrieveAssociation) 
	{
		Order order = new Order();

		try {
			order.setOrderNO(Integer.parseInt(results.getString(1)));
			order.setTotalPrice(Double.parseDouble(results.getString(4)));
			order.setOrderDate(results.getString(5));
			if(retrieveAssociation)
			{
				order.setCustomer(dbCust.findCustomer(results.getString(2)));
				order.setSalesman(dbSalesman.findSalesman(results.getString(3)));
			}
			else
			{
				order.setCustomer(new Customer());
				order.setSalesman(new Salesman());
			}
		}
		catch (Exception c) {
			System.out.println("building order object");
		}

		return order;
	}

	//method to build the query
	private String buildQuery(String wClause) 
	{
		String query = "SELECT * FROM salesOrder";

		if (wClause.length() > 0) query = query + " WHERE " + wClause;

		return query;
	}
}
