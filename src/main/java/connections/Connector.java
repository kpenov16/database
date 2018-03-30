package connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbutils.DbUtils;

//import com.mysql.jdbc.Connection;

import group14.DataAccessException;

public class Connector
{
	/**
	 * To connect to a MySQL-server
	 * 
	 * @param url must have the form
	 * "jdbc:mysql://<server>/<database>" for default port (3306)
	 * OR
	 * "jdbc:mysql://<server>:<port>/<database>" for specific port
	 * more formally "jdbc:subprotocol:subname"
	 * 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SQLException 
	 */
	public static Connection connectToDatabase(String url, String username, String password)
			throws InstantiationException, IllegalAccessException,
					ClassNotFoundException, SQLException
	{
		// call the driver class' no argument constructor
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		
		// get Connection-object via DriverManager
		return (Connection) DriverManager.getConnection(url, username, password);
	}
	
	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet resultSet = null; 
	
	public Connector(String server, int port, String database,
			String username, String password)
				throws InstantiationException, IllegalAccessException,
					ClassNotFoundException, SQLException
	{
		connection = connectToDatabase("jdbc:mysql://"+server+":"+port+"/"+database,
					username, password);
		statement = connection.createStatement();
	}
	
	private final static String SERVER = "localhost"; //"sql-lab1.cc.dtu.dk" something like this 
	private final static int PORT = 3306;
	private final static String DATABASE = "lab_database2";
	private final static String USERNAME = "root";
	private final static String PASSWORD = "";
	
	public Connector() throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException
	{
		this(SERVER, PORT, DATABASE, USERNAME, PASSWORD);
	}
	
	public static ResultSet Query(String command) throws DataAccessException
	{
		try { 
			return statement.executeQuery(command); 
		}catch (SQLException ex) { 
			throw new DataAccessException("Query", ex); 
		}
	}
	
	public static int Update(String command) throws DataAccessException
	{
		try { 
			return statement.executeUpdate(command); 
		}catch (SQLException ex) { 
			throw new DataAccessException("Update", ex); 
		}
	}	
	
	public static void Close() throws SQLException {
		DbUtils.closeQuietly(connection, statement, resultSet);
		/*
		if(resultSet != null)
			resultSet.close();
		if(statement != null)
			statement.close();
		if(connection != null)
			connection.close();
		*/				
	}
}
