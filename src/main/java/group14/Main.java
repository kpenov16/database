package group14;

import java.sql.ResultSet;
import java.sql.SQLException;

import connections.Connector;

public class Main {
	
	public static void main( String[] args ) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, DataAccessException {
	
		Connector connector = new Connector(
				"localhost", 3306, "lab_database2", "root", ""
				);
	
		ResultSet result = Connector.Query("SELECT * FROM operatoer");
		
		while(result.next()) {
			System.out.format("id: %d, name: %s, ini: %s%n", 
								result.getInt("opr_id"),
								result.getString("opr_navn"),
								result.getString("ini"));
		}		
		Connector.Close();		
		System.out.format("%s%n","Hello group 14!");
	
	}
	
}
