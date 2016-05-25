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
	
	/**
	 * Generoi uuden int-tyyppisen arvon parametrina annetulle taulun uudelle arvolle
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public int generoiID(String name) throws SQLException{
		Statement stmt = null;
		stmt = connection.createStatement();
		String idSarake = "";
		switch (name) {
		case "Pelaaja":
			idSarake = "pelaajan_id";
			break;

		case "Rata":
			idSarake = "radan_id";
			break;
			
		case "Peli":
			idSarake = "pelin_id";
			break;
			
		default:
			System.out.println("Nope nope nope");
			break;
		}
		String sql = "SELECT "+idSarake+" FROM " +name+" WHERE "+idSarake+"=(SELECT max("+idSarake+") FROM "+name+");";
		
		ResultSet queryResults = stmt.executeQuery(sql);
		int tmp = queryResults.getInt(idSarake);
		tmp++;
		//Print or return something?
		stmt.close();
		connection.commit();
		return tmp;
	}

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
		stmt = connection.createStatement();
		String sql = "DELETE FROM PELAAJA WHERE ID ="+ pelaajanID +";";
		stmt.executeUpdate(sql);
		stmt.close();
		connection.commit();
	}

	@Override
	public void vaihdaPelaajanPuhelinnumero(int pelaajanID, int uusiPuhnum) throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		String sql = "UPDATE PELAAJA SET PUHNUM = " + uusiPuhnum + " WHERE ID ="+ pelaajanID +";";
		stmt.executeUpdate(sql);
		stmt.close();
		connection.commit();
	}

	@Override
	public void vaihdaPelaajanKotipaikka(int pelaajanID, String uusiKotipaikka) throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		String sql = "UPDATE PELAAJA SET KOTIPAIKKA = " + uusiKotipaikka + " WHERE ID ="+ pelaajanID +";";
		stmt.executeUpdate(sql);
		stmt.close();
		connection.commit();
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
	public void luoPeli(int radan_id, String paivamaara) throws SQLException {
		//TODO ID:n auto generointi. T�ll� hetkell� oittaa vaan random luvun

		Statement stmt = connection.createStatement();
		stmt.executeUpdate("INSERT INTO Peli(pelin_id, radan_id, paivamaara)" +
				"VALUES(" + generoiID("Peli") + "," + radan_id + "," + paivamaara + ")");
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
	 * Palauttaa listauksen yksittäisten pelaajien kokonaistuloksista pelissä pelin_id.
	 * 
	 */

	//Työn alla! :) - Pilvi 

	public ResultSet pelinLopputulos(int pelin_id) throws SQLException {
		
		Statement statement = null;
		statement = connection.createStatement();
		String sqlQuery = "SELECT Pelaaja.nimi, kokonaistulos.summa "
						+ "FROM Pelaaja, ( SELECT pelin_id, pelaajan_id, SUM(heittojen_lkm) AS summa "
										+ "FROM Suoritus "
										+ "WHERE Suoritus.pelin_id = " + pelin_id
										+ " GROUP BY pelaajan_id) AS kokonaistulos "
						+ "WHERE kokonaistulos.pelaajan_id = Pelaaja.pelaajan_id "
						+ "ORDER BY kokonaistulos.summa;";

		ResultSet queryResults = statement.executeQuery(sqlQuery);
		connection.commit();
		return queryResults;
	}

	@Override
	public ResultSet radanEnnatys(int radan_id) throws SQLException {
		// TODO Auto-generated method stub
		ResultSet queryResults = null;
		Statement stmt = null;
		stmt = connection.createStatement();
		queryResults = stmt.executeQuery("SELECT * FROM SUORITUS WHERE RadanID = "+radan_id+" AND Heittojen_lkm = SELECT MIN(Heittojen_lkm) FROM SUORITUS WHERE RadanID = "+radan_id+";");
		stmt.close();
		return queryResults;
	}

	@Override
	public void korjaaHeittojenLkm(int pelaajan_id, int pelin_id, int radan_id, int vaylannumero, int heittojen_lkm)
			throws SQLException {
		// TODO Auto-generated method stub
	}

	@Override
	public ResultSet PelaajanTiedot(int pelaaja_id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Connection getConnection(){
		return connection;
	}
	
	@Override
	public void luoPelaaja(String pelaajanNimi, String puhnum, String kotipaikka) throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		String sql = "INSERT INTO Pelaaja VALUES (" + generoiID("Pelaaja") + ", " + pelaajanNimi + ", " + puhnum + ", " + kotipaikka + ");";
		stmt.executeUpdate(sql);
		stmt.close();
		connection.commit();
		
	}
	@Override
	public void luoRata(String luokitus, int vaylienLkm, String osoite, String ratamestari) throws SQLException {
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("INSERT INTO Rata(radan_id, luokitus, vaylien_lkm, osoite, ratamestari)" +
				"VALUES (" + generoiID("Rata") + "," + luokitus + "," + vaylienLkm + "," + osoite + "," + ratamestari +
				");");
		connection.commit();
		
	}
}