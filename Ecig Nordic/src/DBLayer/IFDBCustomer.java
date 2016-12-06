package DBLayer;
import ModelLayer.Customer;

public interface IFDBCustomer {
	//Inserts data from a customer object into the database
	public int insertCustomer(Customer customer);

	//Recovers data about a customer from the database
	public Customer findCustomer(String phoneno);

	//Updates a customer in the database
	public int updateCustomer(Customer customer, String phoneno);

	//Removes a customer from the database
	public int removeCustomer(String phoneno);
}
