package DBLayer;

import java.sql.*;

import ModelLayer.Customer;

public class DBCustomer implements IFDBCustomer 
{
	private Connection con;
	public DBCustomer()
	{
		con = DBConnection.getInstance().getDBCon();
	}
	
	@Override
	public int insertCustomer(Customer customer) 
	{
		String name = customer.getName();
		String ssn = customer.getSSN();
		String phoneno = customer.getPhoneno();
		String email = customer.getEmail();
		String address = customer.getAddress();
		String zipCode = customer.getZipCode();
		
		String insert = "INSERT INTO Customer(name, ssn, phoneno, email, address, zipCode) " +
						"VALUES('" + name + "', '" + ssn + "', '" + phoneno + "', '" + email + "', '" + address + "', '" + zipCode + "')";
		System.out.println(insert);
		
		return executeQuery(insert);
	}

	@Override
	public Customer findCustomer(String phoneno) 
	{
		Customer customer = singleWhere("phoneno = " + phoneno);
		return customer;
	}

	@Override
	public int updateCustomer(Customer customer, String phonenumber) 
	{
		String name = customer.getName();
		String ssn = customer.getSSN();
		String phoneno = customer.getPhoneno();
		String email = customer.getEmail();
		String address = customer.getAddress();
		String zipCode = customer.getZipCode();
		
		String update = "UPDATE Customer SET name = '" + name + "', ssn = '" + ssn + "', phoneno = '" + phoneno + "', email = '" + email + "', address = '" +
		address + "', zipCode = '" + zipCode + "' WHERE phoneno = " + phonenumber;

		return executeQuery(update);
	}

	@Override
	public int removeCustomer(String phoneno) 
	{
		String remove = "DELETE Customer WHERE phoneno = " + phoneno;
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
	
	//singlewhere is used when only one customer object is to be build TODO make it possible to build associated objects as well
	private Customer singleWhere(String wClause) {
		ResultSet results;
		Customer customer = new Customer();
		String query = buildQuery(wClause);
		System.out.println("DBCustomer -singleWhere " + query);
		try { // read from customer
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			if (results.next()) {
				customer = buildCustomer(results);
			}//end if
			stmt.close();
		}//end try
		catch (Exception c) {
			System.out.println("Query exception - select customer : " + c);
			c.printStackTrace();
		}
		return customer;
	}

	//Method to build a customer object
	private Customer buildCustomer(ResultSet results) 
	{
		Customer customer = new Customer();

		try {
			customer.setName(results.getString(1));
			customer.setSSN(results.getString(2));
			customer.setPhoneno(results.getString(3));
			customer.setEmail(results.getString(4));
			customer.setAddress(results.getString(5));
			customer.setZipCode(results.getString(6));
		} 
		catch (Exception c) {
			System.out.println("building customer object");
		}

		return customer;
	}

	//method to build the query
	private String buildQuery(String wClause) 
	{
		String query = "SELECT * FROM Customer";

		if (wClause.length() > 0) query = query + " WHERE " + wClause;

		return query;
	}
	
}
