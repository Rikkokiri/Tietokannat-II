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
			
			// Pelaaja -taulu
			
			ResultSet rs =stmt.executeQuery("SELECT * FROM Pelaaja;");
			
			System.out.println("Pelaaja");
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
			
			// Rata -taulu
			
			rs = stmt.executeQuery("SELECT * FROM Rata;");
			
			System.out.println("\nRata");
			System.out.println("radan_id     | nimi                    | luokitus   | vaylien_lkm | osoite     | ratamestari" );
			while (rs.next()){
				
				int id = rs.getInt("radan_id");
				String name = rs.getString("nimi");
				String luokitus = rs.getString("luokitus");
				int vaylienlkm = rs.getInt("vaylien_lkm");
				String osoite = rs.getString("osoite");
				String rm = rs.getString("ratamestari");
				
				System.out.print(id);
				for (int i = 0; i < 15-Integer.toString(id).length();i++){
					System.out.print(" ");
				}
				System.out.print(name);
				for (int i = 0 ; i < 26-name.length(); i++){
					System.out.print(" ");
				}
				System.out.print(luokitus);
				for (int i = 0 ; i < 13-luokitus.length(); i++){
					System.out.print(" ");
				}
				System.out.print(vaylienlkm);
				for (int i = 0 ; i < 14-Integer.toString(vaylienlkm).length(); i++){
					System.out.print(" ");
				}
				System.out.print(osoite);
				for (int i = 0 ; i < 13-osoite.length(); i++){
					System.out.print(" ");
				}
				System.out.print(rm);
				for (int i = 0 ; i < 13-rm.length(); i++){
					System.out.print(" ");
				}
				System.out.println("");
				
			}
			
			//Testataan pelin lopputulosek testaamista
			TulostaPelinTulos(1);
			
		} catch (Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage());
			System.exit(0); //This or something else?
		}
	
	}
	
	
	public static void TulostaPelinTulos(int pelin_id){
		
		try {
			ResultSet lopputulos = tkh.pelinLopputulos(pelin_id);
			
			//TODO Tulosta myös pelin päivämäärä
			System.out.println("Pelin " + pelin_id + " lopputulos");
			
			while( lopputulos.next() ){
				String pelaajan_nimi = lopputulos.getString("nimi");
				int tulos = lopputulos.getInt("kokonaistulos");
				
				//TODO Teen siistimpi tulostus
				System.out.println(pelaajan_nimi + " | " + tulos);
			}
			
		} catch (SQLException e) {
			System.out.println("Jokin meni pieleen. Pelin lopputulosta ei pystytä tulostamaan.");
			e.printStackTrace();
		}
		
	}
	

}
