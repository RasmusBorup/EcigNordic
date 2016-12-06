package DBLayer;
import java.sql.*;

public class DBConnection {
	private static final String driver = "jdbc:sqlserver://balder.ucn.dk:1433";
	private static final String databaseName = ";databaseName=dmab0913_1";

	private static final String userName = ";user=dmab0913_1";
	private static final String password = ";password=MaaGodt";
	private DatabaseMetaData dmd;
	private static Connection con;

	private static DBConnection instance = null;

	private DBConnection() {
		String url = driver + databaseName + userName + password;

		try {
			//SQL Server
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("Load af class ok");
		} catch (Exception e) {
			System.out.println("Can't find the driver");
			System.out.println(e.getMessage());
		}

		try {
			//connection to the database
			con = DriverManager.getConnection(url);
			//set autocommit
			con.setAutoCommit(true);
			dmd = con.getMetaData(); // get meta data
			System.out.println("Connection to " + dmd.getURL());
			System.out.println("Driver " + dmd.getDriverName());
			System.out.println("Database product name " + dmd.getDatabaseProductName());
		}//end try
		catch (Exception e) {
			System.out.println("Problems with the connection to the database");
			System.out.println(e.getMessage());
			System.out.println(url);
		}//end catch
	}

	public static DBConnection getInstance() {
		if (instance == null) {
			instance = new DBConnection();
		}
		return instance;
	}

	public Connection getDBCon() {
		return con;
	}

	public static void startTransaction() {
		try {
			con.setAutoCommit(false);
		} catch (Exception e) {
			System.out.println("fejl start transaction");
			System.out.println(e.getMessage());
		}
	}

	public static void commitTransaction() {
		try {
			con.setAutoCommit(true);
		} catch (Exception e) {
			System.out.println("fejl commit transaction");
			System.out.println(e.getMessage());
		}
	}

	public static void rollbackTransaction() {
		try {
			con.rollback();
			con.setAutoCommit(true);
		} catch (Exception e) {
			System.out.println("fejl rollback transaction");
			System.out.println(e.getMessage());
		}
	}

}
