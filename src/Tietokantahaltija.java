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
		Statement stmt = null;
		try{
			stmt = connection.createStatement();
			String sql = "DELETE FROM PELAAJA WHERE ID ="+ pelaajanID +";";
			stmt.executeUpdate(sql);
			stmt.close();
			connection.commit();
		}catch(Exception e) {
			//Jokin meni pieleen metodissa.
			//T�st� johtuen poistoa ei toteuteta
			// -> palautetaan false
			return false;
		}
		//Metodin suorittaminen onnistui, t�ten palautetaan true
		return true;
	}

	@Override
	public boolean vaihdaPelaajanPuhelinnumero(int pelaajanID, int uusiPuhnum) {
		Statement stmt = null;
		try{
			stmt = connection.createStatement();
			String sql = "UPDATE PELAAJA SET PUHNUM = " + uusiPuhnum + " WHERE ID ="+ pelaajanID +";";
			stmt.executeUpdate(sql);
			stmt.close();
			connection.commit();
		}catch(Exception e) {
			//Jokin meni pieleen metodissa.
			//T�st� johtuen poistoa ei toteuteta
			// -> palautetaan false
			return false;
		}
		//Metodin suorittaminen onnistui, t�ten palautetaan true
		return true;
	}

	@Override
	public boolean vaihdaPelaajanKotipaikka(int pelaajanID, String uusiKotipaikka) {
		Statement stmt = null;
		try{
			stmt = connection.createStatement();
			String sql = "UPDATE PELAAJA SET KOTIPAIKKA = " + uusiKotipaikka + " WHERE ID ="+ pelaajanID +";";
			stmt.executeUpdate(sql);
			stmt.close();
			connection.commit();
		}catch(Exception e) {
			//Jokin meni pieleen metodissa.
			//T�st� johtuen poistoa ei toteuteta
			// -> palautetaan false
			return false;
		}
		//Metodin suorittaminen onnistui, t�ten palautetaan true
		return true;
	}

	@Override
	public boolean luoRata(int radanID, String radanLuokitus, int vaylienLkm, String osoite, String ratamestari) throws SQLException {
		//TODO ID:n autogenerointi
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("INSERT INTO Rata(radan_id, luokitus, vaylien_lkm, osoite, ratamestari)" +
				"VALUES (" + radanID + "," + radanLuokitus + "," + vaylienLkm + "," + osoite + "," + ratamestari +
				");");
		connection.commit();
		return true;
	}

	@Override
	public boolean luoPeli(int radan_id, String paivamaara) throws SQLException {
		//TODO ID:n auto generointi. T�ll� hetkell� oittaa vaan random luvun
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("INSERT INTO Peli(pelin_id, radan_id, paivamaara)" +
				"VALUES(" + (int)(Math.random()*1000000000) + "," + radan_id + "," + paivamaara + ")");
		connection.commit();
		return true;
	}

	@Override
	public boolean vaihdaRatamestari(String uusiRatamestari) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean luoVayla(int radan_id, int par, int numero, int pituus) throws SQLException {
		//TODO ID:n autogeneointi?
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("INSERT INTO Vayla(radan_id, par, numero, pituus)" +
				"VALUES (" + radan_id + "," + par + "," + numero + "," + pituus +
				");");
		connection.commit();
		return true;
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
		statement = connection.createStatement();
			
		String sqlQuery = "FROM Pelaaja, Suoritus"
						+ "WHERE Suoritus.pelin_id =" + pelin_id
								+ "AND Pelaaja.id = Suoritus.pelaajan_id"
								+ "AND kokonaistulos = ( SUM(heittojen_lkm)"
						+ "FROM Suoritus"
						+ "GROUP BY pelaajan_id)"
						+ "ORDER BY kokonaistulos;";
			
		ResultSet queryResults = statement.executeQuery(sqlQuery);
		statement.close();
		connection.commit();
		
	}

	@Override
	public void radanEnnatys(int radan_id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean korjaaHeittojenLkm(int pelaajan_id, int pelin_id, int radan_id, int vaylannumero, int heittojen_lkm)
			throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}