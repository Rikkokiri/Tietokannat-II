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
	 * Generoi uuden int-tyyppisen arvon parametrina annetulle taulun uudelle arvolle<br>
	 * K�ytett�v�t String-tyyppiset arvot ovat:
	 * <ul><li>Pelaaja</li>
	 * <li>Rata</li>
	 * <li>Peli</li></ul>
	 * @param name Jokin aiemmin mainituista
	 * @return int
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
		connection.commit();
		stmt.close();
		return tmp;
	}

	//===================== PELAAJA =================================
	
	@Override
	public void luoPelaaja(int pelaajanID, String pelaajanNimi, String puhnum, String kotipaikka) throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		String sql = "INSERT INTO Pelaaja VALUES (" + pelaajanID + ", '" + pelaajanNimi + "', '" + puhnum + "', '" + kotipaikka + "');";
		stmt.executeUpdate(sql);
		connection.commit();
		stmt.close();

	}

	@Override
	public void luoPelaaja(String pelaajanNimi, String puhnum, String kotipaikka) throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		String sql = "INSERT INTO Pelaaja VALUES (" + generoiID("Pelaaja") + ", '" + pelaajanNimi + "', '" + puhnum + "', '" + kotipaikka + "');";
		stmt.executeUpdate(sql);
		connection.commit();
		stmt.close();
	}
	
	@Override
	public void poistaPelaaja(int pelaajanID) throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		String sql = "DELETE FROM Pelaaja " +
				"WHERE pelaajan_id=" + pelaajanID +
				" AND NOT EXISTS( SELECT * FROM Pelaamassa, Suoritus "+
				"WHERE Pelaamassa.pelaajan_id=" + pelaajanID +
				" AND Suoritus.pelaajan_id=" + pelaajanID + ");";
		stmt.executeUpdate(sql);
		connection.commit();
		stmt.close();
	}

	@Override
	public void vaihdaPelaajanPuhelinnumero(int pelaajanID, String uusiPuhnum) throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		String sql = "UPDATE Pelaaja SET puhnum ='" + uusiPuhnum + "' WHERE pelaajan_id ="+ pelaajanID +";";
		stmt.executeUpdate(sql);
		connection.commit();
		stmt.close();
	}

	@Override
	public void vaihdaPelaajanKotipaikka(int pelaajanID, String uusiKotipaikka) throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		String sql = "UPDATE Pelaaja SET kotipaikka ='" + uusiKotipaikka + "' WHERE pelaajan_id ="+ pelaajanID +";";
		stmt.executeUpdate(sql);
		connection.commit();
		stmt.close();
	}

	//===================== RATA =================================
	
	@Override
	public void luoRata(int radanID, String nimi, String radanLuokitus, int vaylienLkm, String osoite, String ratamestari) throws SQLException {
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("INSERT INTO Rata(radan_id, nimi, luokitus, vaylien_lkm, osoite, ratamestari)" +
				"VALUES (" + radanID + ",'" + nimi + "','" + radanLuokitus + "'," + vaylienLkm + ",'" + osoite + "','" + ratamestari +
				"');");
		connection.commit();
		stmt.close();
	}

	@Override
	public void luoRata(String nimi, String luokitus, int vaylienLkm, String osoite, String ratamestari) throws SQLException {
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("INSERT INTO Rata(radan_id, nimi, luokitus, vaylien_lkm, osoite, ratamestari)" +
				"VALUES (" + generoiID("Rata") + ",'" + nimi + "','" + luokitus + "'," + vaylienLkm + ",'"
				+ osoite + "','" + ratamestari +
				"');");
		connection.commit();
		
	}
	
	@Override
	public void vaihdaRatamestari(int radan_id, String uusiRatamestari) throws SQLException {
		Statement stmt = connection.createStatement();
		String SQL = "UPDATE Rata SET ratamestari='"+uusiRatamestari+"' WHERE radan_id="+radan_id+";";
		stmt.executeUpdate(SQL);
		connection.commit();
		stmt.close();
	}
	
	@Override
	public void luoVayla(int radan_id, int par, int numero, int pituus) throws SQLException {
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("INSERT INTO Vayla(radan_id, par, numero, pituus)" +
				"VALUES (" + radan_id + "," + par + "," + numero + "," + pituus +
				");");
		connection.commit();
		stmt.close();
	}
	
	//===================== PELI =================================
	
	@Override
	public void luoPeli(int radan_id, String paivamaara) throws SQLException {
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("INSERT INTO Peli(pelin_id, radan_id, paivamaara)" +
				"VALUES(" + generoiID("Peli") + "," + radan_id + ",'" + paivamaara + "')");
		connection.commit();
		stmt.close();
	}

	@Override
	public  void pelaajaPeliin(int pelin_id, int pelaajan_id) throws SQLException {
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("INSERT INTO Pelaamassa(pelin_id , pelaajan_id) "
				+ "VALUES (" + pelin_id+", " + pelaajan_id+");");
		connection.commit();
		stmt.close();
	}

	@Override
	public void poistaPelaajaPelista(int pelaajan_id, int peli_id) throws SQLException {
		Statement stmt = connection.createStatement();
		String SQL = "DELETE FROM Pelaamassa WHERE pelaajan_id="+pelaajan_id+" AND pelin_id="+peli_id+";";
		stmt.executeUpdate(SQL);
		connection.commit();
		stmt.close();
	}
	
	//===================== SUORITUS =================================

	@Override
	public void luoSuoritus(int pelaajan_id, int pelin_id, int radan_id, int vaylannumero, int heittojen_lkm)
			throws SQLException {
		Statement stmt = connection.createStatement();
		String SQL = "INSERT INTO Suoritus(pelaajan_id, pelin_id, radan_id, vaylannumero, heittojen_lkm)"
				+ "	VALUES(" + pelaajan_id + "," + pelin_id + "," + radan_id + "," + vaylannumero + "," + heittojen_lkm + ");";
		stmt.executeUpdate(SQL);
		connection.commit();
		stmt.close();
	}

	@Override
	public void poistaSuoritus(int pelaajan_id, int pelin_id, int radan_id, int vaylannumero) throws SQLException {
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("DELETE FROM Suoritus WHERE pelaajan_id = " + pelaajan_id + " AND pelin_id=" + pelin_id 
				+ " AND radan_id=" + radan_id + " AND vaylannumero=" + vaylannumero + ";");
		connection.commit();
		stmt.close();
	}

	@Override
	public void korjaaHeittojenLkm(int pelaajan_id, int pelin_id, int radan_id, int vaylannumero, int heittojen_lkm)
			throws SQLException {
		Statement stmt = connection.createStatement();
		String sql = "UPDATE Suoritus set heittojen_lkm = "+heittojen_lkm+" WHERE pelaajan_id = "+pelaajan_id+" AND pelin_id = "+pelin_id+" AND radan_id = "+radan_id+" AND vaylannumero = "+vaylannumero+";";
		stmt.executeUpdate(sql);
		stmt.close();
		connection.commit();
	}
	
	
	//===================== KYSELYT =================================
	
	/* PELIN LOPPUTULOS(non-Javadoc)
	 * @see TietokantaRajapinta#pelinLopputulos(int)
	 */
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
	
	/* PELIN VOITTAJA (non-Javadoc)
	 * @see TietokantaRajapinta#pelinVoittaja(int)
	 */
	@Override
	public ResultSet pelinVoittaja(int pelin_id) throws SQLException {
		
		Statement stmt = connection.createStatement();
		String query = "SELECT Pelaaja.nimi, MIN(kokonaistulos.summa) "
				+ "FROM Pelaaja, (SELECT pelin_id, pelaajan_id, SUM(heittojen_lkm) AS summa "
				+ "FROM Suoritus "
				+ "WHERE Suoritus.pelin_id = " + pelin_id
				+ " GROUP BY pelaajan_id) kokonaistulos "
				+ "WHERE kokonaistulos.pelaajan_id = Pelaaja.pelaajan_id;";
		
		ResultSet rs = stmt.executeQuery(query);
		return rs;
	}

	/* RADAN ENNÄTYS
	 * @see TietokantaRajapinta#radanEnnatys(int)
	 */
	@Override
	public ResultSet radanEnnatys(int radan_id) throws SQLException {
		Statement stmt = connection.createStatement();
			
		String query = "SELECT Pelaaja.nimi, MIN(kokonaistulos.summa), Peli.paivamaara "
					+ "FROM Pelaaja, Peli, (SELECT pelin_id, pelaajan_id, SUM(heittojen_lkm) AS summa "
										+ "FROM Suoritus "
										+ "WHERE Suoritus.pelin_id IN " 
														+ "(SELECT pelin_id "
														+ "FROM Peli "
														+ "WHERE radan_id = " + radan_id + ") "
										+ "GROUP BY pelaajan_id, pelin_id) kokonaistulos "
					+ "WHERE kokonaistulos.pelaajan_id = Pelaaja.pelaajan_id AND kokonaistulos.pelin_id = Peli.pelin_id;";
		
		ResultSet rs = stmt.executeQuery(query);
		return rs;
	}

	/* PELAAJAN TIEDOT
	 * @see TietokantaRajapinta#pelaajanTiedot(int)
	 */
	@Override
	public ResultSet pelaajanTiedot(int pelaaja_id) throws SQLException {
		Statement stmt = connection.createStatement();
		String sql = "SELECT * FROM Pelaaja WHERE pelaajan_id = "+pelaaja_id+";";
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}
	
	/* RADAN TIEDOT
	 * @see TietokantaRajapinta#radanTiedot(int)
	 */
	@Override
	public ResultSet radanTiedot(int radan_id) throws SQLException {
		Statement stmt = connection.createStatement();
		String sql = "SELECT * FROM Rata WHERE radan_id = "+radan_id+";";
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}

	/* VÄYLÄN TIEDOT
	 * @see TietokantaRajapinta#vaylanTiedot(int, int)
	 */
	@Override
	public ResultSet vaylanTiedot(int radan_id, int vaylan_numero) throws SQLException {
		Statement stmt = connection.createStatement();
		String sql = "SELECT * FROM Vayla WHERE numero = "+vaylan_numero+" AND radan_id = "+radan_id+";";
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}

	/* PELAAJAN SUORITUKSET VÄYLÄLLÄ
	 * @see TietokantaRajapinta#pelaajanSuorituksetVaylalla(int, int, int)
	 */
	@Override
	public ResultSet pelaajanSuorituksetVaylalla(int pelaajan_id, int radan_id, int vaylan_numero) throws SQLException {
		
		Statement stmt = connection.createStatement();
		String query = "SELECT Pelaaja.nimi, Suoritus.heittojen_lkm "
					+ "FROM Pelaaja, Suoritus "
					+ "WHERE Suoritus.pelaajan_id = " + pelaajan_id
						+ " AND Suoritus.radan_id = " + radan_id
						+ " AND Suoritus.vaylannumero = " + vaylan_numero 
						+ " AND Pelaaja.pelaajan_id = Suoritus.pelaajan_id"
						+ " AND Peli.pelin_id = Suoritukset.pelin_id";
		
		ResultSet rs = stmt.executeQuery(query);
		return rs;
	}
	
	/* PELAAJAN ENNÄTYS RADALLA
	 * @see TietokantaRajapinta#pelaajanEnnatysRadalla(int, int)
	 */
	@Override
	public ResultSet pelaajanEnnatysRadalla(int pelaajan_id, int radan_id) throws SQLException {
		
		Statement stmt = connection.createStatement();
		String query = "SELECT Pelaaja.nimi, MIN(kokonaistulos.summa), Peli.paivamaara "
					+ "FROM Pelaaja, Peli, (SELECT pelin_id, pelaajan_id, SUM(heittojen_lkm) AS summa "
											+ "FROM Suoritus "
											+ "WHERE Suoritus.pelaajan_id = "+ pelaajan_id + " AND Suoritus.pelin_id IN "
													+ "(SELECT pelin_id "
													+ "FROM Peli "
													+ "WHERE radan_id = " + radan_id + ")"
											+ "GROUP BY pelin_id) kokonaistulos "
					+ "WHERE kokonaistulos.pelaajan_id = Pelaaja.pelaajan_id AND kokonaistulos.pelin_id = Peli.pelin_id;";
		
		ResultSet rs = stmt.executeQuery(query);
		return rs;
	}
	
	/* PELIN PELAAJIEN TIEDOT
	 * @see TietokantaRajapinta#pelinPelaajienTiedot(int)
	 */
	@Override
	public ResultSet pelinPelaajienTiedot(int pelin_id) throws SQLException {
		Statement stmt = connection.createStatement();
		String sql = "SELECT nimi, puhnum, kotipaikka "
				+ "FROM Pelaaja JOIN Pelaamassa USING ( pelaajan_id ) "
				+ "WHERE pelin_id = " + pelin_id + ";";
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}

	/* RADAN PAR
	 * @see TietokantaRajapinta#radanPar(int)
	 */
	@Override
	public ResultSet radanPar(int radan_id) throws SQLException{
		Statement stmt = connection.createStatement();
		String SQL = "SELECT sum(par) FROM Vayla WHERE radan_id="+radan_id+";";
		ResultSet rs = stmt.executeQuery(SQL);
		return rs;
	}
	
	
	//===================== APUMETODIT =================================
	
	public Connection getConnection(){
		return connection;
	}
	
}