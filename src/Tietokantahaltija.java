import java.sql.*;

public class Tietokantahaltija implements TietokantaRajapinta {
	
	private static Connection connection = null;

	//------- Constructor -----------------------
	
	public Tietokantahaltija(){
		try{
			Class.forName("org.sqlite.JBC");
			connection = DriverManager.getConnection("jdbc:sqlite:frisbee.db");
			connection.setAutoCommit(false);
		} catch (Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage());
			System.exit(0); //This or something else?
		}
	
		System.out.println("Opened database successfully. Yasss.");
	}
	//-------------------------------------------

	@Override
	public boolean luoPelaaja(int pelaajanID, String pelaajanNimi, String puhnum, String kotipaikka) {
		Statement stmt = null;
		try{
			stmt = connection.createStatement();
			String sql = "INSERT INTO Pelaaja VALUES (" + pelaajanID + ", " + pelaajanNimi + ", " + puhnum + ", " + kotipaikka + ");";
			stmt.executeUpdate(sql);
			stmt.close();
			connection.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean poistaPelaaja(int pelaajanID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean vaihdaPelaajanPuhelinnumero(int pelaajanID, int uusiPuhnum) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean vaihdaPelaajanKotipaikka(int pelaajanID, int uusiKotipaikka) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean luoRata(int radanID, String radanLuokitus, int vaylienLkm, String osoite, String ratamestari) throws SQLException {
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("INSERT INTO Rata(radan_id, luokitus, vaylien_lkm, osoite, ratamestari)" +
				"VALUES (" + radanID + "," + radanLuokitus + "," + vaylienLkm + "," + osoite + "," + ratamestari +
				");");
		connection.commit();
		return true;
	}

	@Override
	public boolean luoPeli(int radan_id, String paivamaara) throws SQLException {
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("INSERT INTO Peli(pelin_id, radan_id, paivamaara)" +
				"VALUES(" + (int)(Math.random()*1000000000) + "," + radan_id + "," + paivamaara + ")");
		return false;
	}

	@Override
	public boolean vaihdaRatamestari(String uusiRatamestari) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean luoVäylä(int radan_id, int par, int numero, int pituus) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pelaajaPeliin(int pelin_id, int pelaajan_id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean poistaPelaajaPelista() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean luoSuoritus(int pelaajan_id, int pelin_id, int radan_id, int vaylannumero, int heittojen_lkm)
			throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean poistaSuoritus(int pelaajan_id, int pelin_id, int radan_id, int vaylannumero) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Palauttaa listauksen yksittäisten pelaajien kokonaistuloksista pelissä pelin_id.
	 * 
	 */
	
	//Työn alla! :) - Pilvi 
	
	public void pelinLopputulos(int pelin_id) throws SQLException {
		// TODO Auto-generated method stub
		
		Statement statement = null;
		
		try{
			
			statement = connection.createStatement();
			
			String sqlQuery = "";
			
			ResultSet queryResults = statement.executeQuery(sqlQuery);
			
			//Print or return something?
			
			statement.close();
			connection.commit();
			
		} catch (Exception e){
			
			//TODO Mitä tehdään, jos kysely ei jostain syystä onnistunut?
			//Heitetäänkö poikkeus vai palautetaanko metodin käyttäjälle jokin arvo?
			
		}
		
	}

	@Override
	public void radanEnnatys(int radan_id) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	
	
}