package DBLayer;
import ModelLayer.PartOrder;

public interface IFDBPartOrder {
	//Inserts data from a partOrder object into the database
	public int insertPartOrder(PartOrder partOrder, String orderNO);

	//Recovers data about a partOrder from the database
	public PartOrder findPartOrder(String orderNO, String productEAN, boolean retrieveAssociation);

	//Updates a partOrder in the database ???Update a single partorder necessary???
	public int updataPartOrder(PartOrder partOrder, String ordeNO, String productEAN);

	//Removes a partOrder from the database
	public int removePartOrder(String orderNO, String productEAN);
}
