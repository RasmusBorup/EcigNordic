package DBLayer;

import java.sql.*;

import ModelLayer.Salesman;

public class DBSalesman implements IFDBSalesman 
{
	private Connection con;
	public DBSalesman()
	{
		con = DBConnection.getInstance().getDBCon();
	}
	
	@Override
	public int insertSalesman(Salesman salesman) 
	{
		String name = salesman.getName();
		String ssn = salesman.getSSN();
		String phoneno = salesman.getPhoneno();
		String email = salesman.getEmail();
		String address = salesman.getAddress();
		String zipCode = salesman.getZipCode();
		String salesmanID = salesman.getSalesmanID();
		String paymentLevel = salesman.getPaymentLevel();
		
		String insert = "INSERT INTO Salesman(name, ssn, phoneno, email, address, zipCode, salesmanID, paymentLevel) " +
						"VALUES('" + name + "', '" + ssn + "', '" + phoneno + "', '" + email + "', '" + address + "', '" + zipCode + "', '" + salesmanID + "', '" + paymentLevel  + "')";
		System.out.println(insert);
		
		return executeQuery(insert);
	}

	@Override
	public Salesman findSalesman(String salesmanID) //TODO check if anything was found
	{
		Salesman salesman = singleWhere("salesmanID = " + salesmanID);
		return salesman;
	}

	@Override
	public int updateSalesman(Salesman salesman, String salesmanID) 
	{
		String name = salesman.getName();
		String ssn = salesman.getSSN();
		String phoneno = salesman.getPhoneno();
		String email = salesman.getEmail();
		String address = salesman.getAddress();
		String zipCode = salesman.getZipCode();
		String paymentLevel = salesman.getPaymentLevel();
		
		String update = "UPDATE Salesman SET name = '" + name + "', ssn = '" + ssn + "', phoneno = '" + phoneno + "', email = '" + email + "', address = '" + address + "', zipCode = '" + zipCode + "', paymentLevel = '" + paymentLevel + "' WHERE salesmanID = " + salesmanID;

		return executeQuery(update);
	}

	@Override
	public int removeSalesman(String salesmanID) //TODO check for relations to orders and figure out what to do
	{		
		String remove = "DELETE Salesman Where salesmanID = " + salesmanID;

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
	
	//singlewhere is used when only one salesman object is to be build TODO make it possible to build associated objects as well
	private Salesman singleWhere(String wClause) {
		ResultSet results;
		Salesman salesman = new Salesman();
		String query = buildQuery(wClause);
		System.out.println("DBSalesman -singelWhere " + query);
		try { // read from salesman
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			if (results.next()) {
				salesman = buildSalesman(results);
			}//end if
			stmt.close();
		}//end try
		catch (Exception c) {
			System.out.println("Query exception - select salesman : " + c);
			c.printStackTrace();
		}
		return salesman;
	}

	//Method to build a salesman object
	private Salesman buildSalesman(ResultSet results) 
	{
		Salesman salesman = new Salesman();

		try {
			salesman.setName(results.getString(1));
			salesman.setSSN(results.getString(2));
			salesman.setPhoneno(results.getString(3));
			salesman.setEmail(results.getString(4));
			salesman.setAddress(results.getString(5));
			salesman.setZipCode(results.getString(6));
			salesman.setSalesmanID(results.getString(7));
			salesman.setPaymentLevel(results.getString(8));
		} 
		catch (Exception c) {
			System.out.println("building salesman object");
		}

		return salesman;
	}

	//method to build the query
	private String buildQuery(String wClause) 
	{
		String query = "SELECT * FROM Salesman";

		if (wClause.length() > 0) query = query + " WHERE " + wClause;

		return query;
	}
	
//	//miscWhere is used when more than one salesman is selected and built
//	private ArrayList miscWhere(String wClause) {
//		ResultSet results;
//		ArrayList list = new ArrayList();
//
//		String query = buildQuery(wClause);
//		System.out.println("DbSalesman " + query);
//		try {
//			Statement stmt = con.createStatement();
//			stmt.setQueryTimeout(5);
//			results = stmt.executeQuery(query);
//
//			int snr = 0;
//			while (results.next()) {
//				Salesman salesman = new Salesman();
//				salesman = buildSalesman(results);
//				list.add(salesman);
//			}//end while
//			stmt.close();
//		}//end try
//		catch (Exception c) {
//			System.out.println("Query exception - select salesman : " + c);
//			c.printStackTrace();
//		}
//		return list;
//
//	}
}
