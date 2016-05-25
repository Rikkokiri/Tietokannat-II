import java.sql.*;

public class Tietokantahaltija implements TietokantaRajapinta {

	private static Connection connection = null;

	//------- Constructor -----------------------

	public Tietokantahaltija(){
		try{
			Class.forName("org.sqlite.JDBC");
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
	public void luoPelaaja(int pelaajanID, String pelaajanNimi, String puhnum, String kotipaikka) throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		String sql = "INSERT INTO Pelaaja VALUES (" + pelaajanID + ", " + pelaajanNimi + ", " + puhnum + ", " + kotipaikka + ");";
		stmt.executeUpdate(sql);
		stmt.close();
		connection.commit();

	}

	@Override
	public void poistaPelaaja(int pelaajanID) throws SQLException {
		Statement stmt = null;
<<<<<<< HEAD
		stmt = connection.createStatement();
		String sql = "DELETE FROM PELAAJA WHERE ID ="+ pelaajanID +";";
		stmt.executeUpdate(sql);
		stmt.close();
		connection.commit();
=======
		try{
			stmt = connection.createStatement();
			String sql = "DELETE FROM PELAAJA WHERE ID ="+ pelaajanID +";";
			stmt.executeUpdate(sql);
			stmt.close();
			connection.commit();
		}catch(Exception e) {
			//Jokin meni pieleen metodissa.
			//TÔøΩstÔøΩ johtuen poistoa ei toteuteta
			// -> palautetaan false
			return false;
		}
		//Metodin suorittaminen onnistui, tÔøΩten palautetaan true
		return true;
>>>>>>> origin/master
	}

	@Override
	public void vaihdaPelaajanPuhelinnumero(int pelaajanID, int uusiPuhnum) throws SQLException {
		Statement stmt = null;
<<<<<<< HEAD
		stmt = connection.createStatement();
		String sql = "UPDATE PELAAJA SET PUHNUM = " + uusiPuhnum + " WHERE ID ="+ pelaajanID +";";
		stmt.executeUpdate(sql);
		stmt.close();
		connection.commit();
=======
		try{
			stmt = connection.createStatement();
			String sql = "UPDATE PELAAJA SET PUHNUM = " + uusiPuhnum + " WHERE ID ="+ pelaajanID +";";
			stmt.executeUpdate(sql);
			stmt.close();
			connection.commit();
		}catch(Exception e) {
			//Jokin meni pieleen metodissa.
			//TÔøΩstÔøΩ johtuen poistoa ei toteuteta
			// -> palautetaan false
			return false;
		}
		//Metodin suorittaminen onnistui, tÔøΩten palautetaan true
		return true;
>>>>>>> origin/master
	}

	@Override
	public void vaihdaPelaajanKotipaikka(int pelaajanID, String uusiKotipaikka) throws SQLException {
		Statement stmt = null;
<<<<<<< HEAD
		stmt = connection.createStatement();
		String sql = "UPDATE PELAAJA SET KOTIPAIKKA = " + uusiKotipaikka + " WHERE ID ="+ pelaajanID +";";
		stmt.executeUpdate(sql);
		stmt.close();
		connection.commit();
=======
		try{
			stmt = connection.createStatement();
			String sql = "UPDATE PELAAJA SET KOTIPAIKKA = " + uusiKotipaikka + " WHERE ID ="+ pelaajanID +";";
			stmt.executeUpdate(sql);
			stmt.close();
			connection.commit();
		}catch(Exception e) {
			//Jokin meni pieleen metodissa.
			//TÔøΩstÔøΩ johtuen poistoa ei toteuteta
			// -> palautetaan false
			return false;
		}
		//Metodin suorittaminen onnistui, tÔøΩten palautetaan true
		return true;
>>>>>>> origin/master
	}

	@Override
	public void luoRata(int radanID, String radanLuokitus, int vaylienLkm, String osoite, String ratamestari) throws SQLException {
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("INSERT INTO Rata(radan_id, luokitus, vaylien_lkm, osoite, ratamestari)" +
				"VALUES (" + radanID + "," + radanLuokitus + "," + vaylienLkm + "," + osoite + "," + ratamestari +
				");");
		connection.commit();
	}

	@Override
<<<<<<< HEAD
	public void luoPeli(int radan_id, String paivamaara) throws SQLException {
		//TODO ID:n auto generointi. T‰ll‰ hetkell‰ oittaa vaan random luvun
=======
	public boolean luoPeli(int radan_id, String paivamaara) throws SQLException {
		//TODO ID:n auto generointi. TÔøΩllÔøΩ hetkellÔøΩ oittaa vaan random luvun
>>>>>>> origin/master
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("INSERT INTO Peli(pelin_id, radan_id, paivamaara)" +
				"VALUES(" + (int)(Math.random()*1000000000) + "," + radan_id + "," + paivamaara + ")");
		connection.commit();
	}

	@Override
	public void vaihdaRatamestari(String uusiRatamestari) throws SQLException {
		// TODO Auto-generated method stub
	}

	@Override
	public void luoVayla(int radan_id, int par, int numero, int pituus) throws SQLException {
		//TODO ID:n autogeneointi?
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("INSERT INTO Vayla(radan_id, par, numero, pituus)" +
				"VALUES (" + radan_id + "," + par + "," + numero + "," + pituus +
				");");
		connection.commit();
	}

	@Override
	public  void pelaajaPeliin(int pelin_id, int pelaajan_id) throws SQLException {
	}

	@Override
	public void poistaPelaajaPelista() throws SQLException {
		// TODO Auto-generated method stub
	}

	@Override
	public void luoSuoritus(int pelaajan_id, int pelin_id, int radan_id, int vaylannumero, int heittojen_lkm)
			throws SQLException {
		// TODO Auto-generated method stub
	}

	@Override
	public void poistaSuoritus(int pelaajan_id, int pelin_id, int radan_id, int vaylannumero) throws SQLException {
		// TODO Auto-generated method stub
	}

	@Override
	/**
	 * Palauttaa listauksen yksitt√§isten pelaajien kokonaistuloksista peliss√§ pelin_id.
	 * 
	 */

	//Ty√∂n alla! :) - Pilvi 

	public ResultSet pelinLopputulos(int pelin_id) throws SQLException {
		Statement statement = null;
		statement = connection.createStatement();
<<<<<<< HEAD
		String sqlQuery = "";
		ResultSet queryResults = statement.executeQuery(sqlQuery);
		//Print or return something?
		statement.close();
		connection.commit();
		return queryResults;
=======
			
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
		
>>>>>>> origin/master
	}

	@Override
	public ResultSet radanEnnatys(int radan_id) throws SQLException {
		// TODO Auto-generated method stub
		ResultSet queryResults = null;
		return queryResults;
	}

	@Override
	public void korjaaHeittojenLkm(int pelaajan_id, int pelin_id, int radan_id, int vaylannumero, int heittojen_lkm)
			throws SQLException {
		// TODO Auto-generated method stub
	}
<<<<<<< HEAD

	@Override
	public ResultSet PelaajanTiedot(int pelaaja_id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}



=======
	
	public Connection getConnection(){
		return connection;
	}
	
	
>>>>>>> origin/master
}