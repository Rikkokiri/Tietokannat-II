import java.sql.Connection;
import java.sql.DriverManager;

public class Tietokantahaltija{

	public static void main(String[] args){
		
		Connection connection = null;
		
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