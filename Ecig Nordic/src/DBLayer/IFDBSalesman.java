package DBLayer;
import ModelLayer.Salesman;

public interface IFDBSalesman {
	//Inserts data from a salesman object into the database
	public int insertSalesman(Salesman salesman);

	//Recovers data about a salesman from the database
	public Salesman findSalesman(String salesmanID);

	//Updates a salesman in the database
	public int updateSalesman(Salesman salesman, String ean);

	//Removes a salesman from the database
	public int removeSalesman(String salesmanID);
}
