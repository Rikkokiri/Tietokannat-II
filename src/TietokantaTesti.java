import java.sql.DriverManager;
import java.sql.*;

public class TietokantaTesti {

	
	
	public static void main(String[] args){
		
		//TODO Pitääkö testissä tehdä oma connection, vai tehäänkö Tietokantahaltija.getConnection()?
		Connection connection = null;
		
		try{
			Tietokantahaltija tkh = new Tietokantahaltija();
			connection = tkh.getConnection();
			
			// Tulostetaan kaikki taulut
			
			Statement stmt = connection.createStatement();
			ResultSet rs =stmt.executeQuery("SELECT * FROM Pelaaja;");
			
			System.out.println("pelaajan_id  | nimi       | puhnum     | kotipaikka" );
			while (rs.next()){
				
				int id = rs.getInt("pelaajan_id");
				String name = rs.getString("nimi");
				String phone = rs.getString("puhnum");
				String kotip = rs.getString("kotipaikka");
				
				System.out.print(id);
				for (int i = 0; i < 15-Integer.toString(id).length();i++){
					System.out.print(" ");
				}
				System.out.print(name);
				for (int i = 0 ; i < 13-name.length(); i++){
					System.out.print(" ");
				}
				System.out.print(phone);
				for (int i = 0 ; i < 13-phone.length(); i++){
					System.out.print(" ");
				}
				System.out.print(kotip);
				for (int i = 0 ; i < 13-kotip.length(); i++){
					System.out.print(" ");
				}
				System.out.println("");
			}
		} catch (Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage());
			System.exit(0); //This or something else?
		}
	
	}
	
}
