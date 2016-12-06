package DBLayer;
import ModelLayer.Product;

public interface IFDBProduct {
	//Inserts data from a product object into the database
	public int insertProduct(Product product);

	//Recovers data about a product from the database
	public Product findProduct(String ean);

	//Updates a product in the database
	public int updataProduct(Product product, String ean);

	//Removes a product from the database
	public int removeProduct(String ean);

}