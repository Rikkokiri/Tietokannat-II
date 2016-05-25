import java.sql.DriverManager;
import java.sql.*;

public class TietokantaTesti {

	public static Tietokantahaltija tkh;
	
	public static void main(String[] args){
		
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

			// Peli -taulu
			rs =stmt.executeQuery("SELECT * FROM Peli;");
			
			System.out.println("\nPeli");
			System.out.println("pelin_id    | radan_id   | paivamaara" );
			while (rs.next()){
				
				int pelinid = rs.getInt("pelin_id");
				int radanid = rs.getInt("radan_id");
				String paiv = rs.getString("paivamaara");
				
				System.out.print(pelinid);
				for (int i = 0; i < 14-Integer.toString(pelinid).length();i++){
					System.out.print(" ");
				}
				System.out.print(radanid);
				for (int i = 0; i < 13-Integer.toString(radanid).length();i++){
					System.out.print(" ");
				}
				System.out.print(paiv);
				for (int i = 0 ; i < 13-paiv.length(); i++){
					System.out.print(" ");
				}
				System.out.println("");
			}
			
			// Pelaamassa -taulu
			
			rs = stmt.executeQuery("SELECT * FROM Pelaamassa;");
			
			System.out.println("\nPelaamassa");
			System.out.println("pelin_id    | pelaajan_id" );
			while (rs.next()){
				
				int pelinid = rs.getInt("pelin_id");
				int pelaajanid = rs.getInt("pelaajan_id");
				
				System.out.print(pelinid);
				for (int i = 0; i < 14-Integer.toString(pelinid).length();i++){
					System.out.print(" ");
				}
				System.out.print(pelaajanid);
				for (int i = 0; i < 13-Integer.toString(pelaajanid).length();i++){
					System.out.print(" ");
				}
				System.out.println("");
			}
			
			//Suoritus -taulu
			
			rs = stmt.executeQuery("SELECT * FROM Suoritus;");
			
			System.out.println("\nSuoritus");
			System.out.println("pelaajan_id | pelin_id   | radan_id   | vaylannumero | heittojen_lkm" );
			while (rs.next()){
				
				int pelaajanid = rs.getInt("pelaajan_id");
				int pelinid = rs.getInt("pelin_id");
				int radanid = rs.getInt("radan_id");
				int vaylanumero =rs.getInt("vaylannumero");
				int heittojenlkm = rs.getInt("heittojen_lkm");
				
				System.out.print(pelaajanid);
				for (int i = 0; i < 14-Integer.toString(pelaajanid).length();i++){
					System.out.print(" ");
				}
				System.out.print(pelinid);
				for (int i = 0; i < 13-Integer.toString(pelinid).length();i++){
					System.out.print(" ");
				}
				System.out.print(radanid);
				for (int i = 0; i < 13-Integer.toString(radanid).length();i++){
					System.out.print(" ");
				}
				System.out.print(vaylanumero);
				for (int i = 0; i < 15-Integer.toString(vaylanumero).length();i++){
					System.out.print(" ");
				}
				System.out.print(heittojenlkm);
				for (int i = 0; i < 13-Integer.toString(heittojenlkm).length();i++){
					System.out.print(" ");
				}
				System.out.println("");
			}
			
			// Väylä -taulu
			
			rs = stmt.executeQuery("SELECT * FROM Vayla");
			
			System.out.println("\nVäylä");
			System.out.println("radan_id    | par        | numero     | pituus" );
			while (rs.next()){
				
				int radanid= rs.getInt("radan_id");
				int par = rs.getInt("par");
				int numero = rs.getInt("numero");
				int pituus = rs.getInt("pituus");
				
				System.out.print(radanid);
				for (int i = 0; i < 14-Integer.toString(radanid).length();i++){
					System.out.print(" ");
				}
				System.out.print(par);
				for (int i = 0; i < 13-Integer.toString(par).length();i++){
					System.out.print(" ");
				}
				System.out.print(numero);
				for (int i = 0; i < 13-Integer.toString(numero).length();i++){
					System.out.print(" ");
				}
				System.out.print(pituus);
				for (int i = 0; i < 13-Integer.toString(pituus).length();i++){
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
		
		//tkh.
		
	}
	

}
