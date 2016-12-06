package DBLayer;

import java.sql.*;

import ModelLayer.Product;

public class DBProduct implements IFDBProduct 
{
	private Connection con;
	
	public DBProduct()
	{
		con = DBConnection.getInstance().getDBCon();
	}

	@Override
	public int insertProduct(Product product) 
	{
		String EAN = product.getEAN();
		String name = product.getName();
		String description = product.getDescription();
		String price = Double.toString(product.getPrice());
		String stock = Integer.toString(product.getStock());
		String minStock = Integer.toString(product.getMinStock());
		
		String insert = "INSERT INTO Product(EAN, name, description, price, stock, minStock) " +
						"VALUES('" + EAN + "', '" + name + "', '" + description + "', " + price + "', " + stock + ", " + minStock + ")";
		System.out.println(insert);
		
		return executeQuery(insert);
	}

	@Override
	public Product findProduct(String ean) 
	{
		Product product = singleWhere("EAN = " + ean);
		return product;
	}

	@Override
	public int updataProduct(Product product, String ean) 
	{
		String EAN = product.getEAN();
		String name = product.getName();
		String description = product.getDescription();
		String price = Double.toString(product.getPrice());
		String stock = Integer.toString(product.getStock());
		String minStock = Integer.toString(product.getMinStock());
		
		String update = "UPDATE Product SET EAN = '" + EAN + "', name = '" + name + "', description = '" + description + "', price = " + price + ", stock = " +
		stock + ", minStock = " + minStock +  " WHERE EAN = " + ean;

		return executeQuery(update);
	}

	@Override
	public int removeProduct(String ean) 
	{
		String remove = "DELETE Product WHERE EAN = " + ean;
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
	
	//singlewhere is used when only one product object is to be build TODO make it possible to build associated objects as well
	private Product singleWhere(String wClause) {
		ResultSet results;
		Product product = new Product();
		String query = buildQuery(wClause);
		System.out.println("DBProduct -singleWhere " + query);
		try { // read from product
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			if (results.next()) {
				product = buildProduct(results);
			}//end if
			stmt.close();
		}//end try
		catch (Exception c) {
			System.out.println("Query exception - select product : " + c);
			c.printStackTrace();
		}
		return product;
	}

	//Method to build a product object
	private Product buildProduct(ResultSet results) 
	{
		Product product = new Product();

		try {
			product.setEAN(results.getString(1));
			product.setName(results.getString(2));
			product.setDescription(results.getString(3));
			product.setPrice(Double.parseDouble(results.getString(4)));
			product.setStock(Integer.parseInt(results.getString(5)));
			product.setMinStock(Integer.parseInt(results.getString(6)));
		} 
		catch (Exception c) {
			System.out.println("building product object");
		}

		return product;
	}

	//method to build the query
	private String buildQuery(String wClause) 
	{
		String query = "SELECT * FROM Product";

		if (wClause.length() > 0) query = query + " WHERE " + wClause;

		return query;
	}
}
