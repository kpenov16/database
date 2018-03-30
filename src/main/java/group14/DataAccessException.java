package group14;

public class DataAccessException extends Exception{
	private static final long serialVersionUID = 2533111595924705448L;
	public DataAccessException(String message, Exception ex) { super(message, ex); }    
	public DataAccessException(Exception ex) { super(ex); }
}
