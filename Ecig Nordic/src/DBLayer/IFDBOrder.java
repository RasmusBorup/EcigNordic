package DBLayer;
import java.text.ParseException;

import ModelLayer.Order;

public interface IFDBOrder {
	//Inserts data from an Order object into the database
	public int insertSalesOrder(Order Order);

	//Recovers data about an Order from the database
	public Order findSalesOrder(String orderNO, boolean retrieveAssociation);

	//Updates an Order in the database
	public int updataSalesOrder(Order Order, String orderNO);

	//Removes an Order from the database
	public int removeSalesOrder(String orderNO);
}
