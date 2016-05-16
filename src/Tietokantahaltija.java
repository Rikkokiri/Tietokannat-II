import java.sql.Connection;
import java.sql.DriverManager;

public class Tietokantahaltija{
	
	private static Connection connection = null;

	public static void main(String[] args){
		
		//Form connection to the database
		formDatabaseConnection();

	}
	
	
	
	/**
	 * Method for forming the connection to the database.
	 */
	public static void formDatabaseConnection(){ //The method could also return a boolean?
		
		try{
			Class.forName("org.sqlite.JBC");
			connection = DriverManager.getConnection("jdbc:sqlite:frisbee.db");
			
		} catch (Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage());
			System.exit(0); //This or something else?
		}
	
		System.out.println("Opened database successfully. Yasss.");
		
	}
}