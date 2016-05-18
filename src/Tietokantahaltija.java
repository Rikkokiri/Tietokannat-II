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
	
	
	
}