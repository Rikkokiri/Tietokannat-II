import java.sql.*;

public class TietokantaTesti {

	public static Tietokantahaltija tkh;
	
	public static void main(String[] args){
		
		//TODO Pit��k� testiss� tehd� oma connection, vai teh��nk� Tietokantahaltija.getConnection()?
		Connection connection = null;
		
		try{
			tkh = new Tietokantahaltija();
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
	
	
	public void TulostaPelinTulos(int pelin_id){
		
		try {
			ResultSet lopputulos = tkh.pelinLopputulos(pelin_id);
			
			//TODO Tulosta myös pelin päivämäärä
			System.out.println("Pelin " + pelin_id + " lopputulos");
			
			while( lopputulos.next() ){
				String pelaajan_nimi = lopputulos.getString("nimi");
			}
			
		} catch (SQLException e) {
			System.out.println("Jokin meni pieleen. Pelin lopputulosta ei pystytä tulostamaan.");
			e.printStackTrace();
		}
		
	}
	

}
