package TestLayer;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import DBLayer.DBConnection;

public class DBConnectionTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetInstance() {
		DBConnection dbCon = DBConnection.getInstance();
		if (dbCon != null) System.out.println("Success");
		else
			fail("Not yet implemented");
	}
}
