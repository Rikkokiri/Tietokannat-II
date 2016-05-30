import java.sql.*;

/**
 * Tietokannan testaamiseen tarkoitttu luokka
 *
 */
public class TietokantaTesti {

	/**{@link Tietokantahaltija}-luokan ilmentym‰*/
	public static Tietokantahaltija tkh;
	/**{@link Connection}-luokan ilmentym‰*/
	public static Connection connection; 
	
	/**Juoksutin*/
	public static void main(String[] args){
		try{
			tkh = new Tietokantahaltija();
			connection = tkh.getConnection();
					
			tulostaTaulut();
			
			// ----- Metodien testaus ------ //
			
//			System.out.println(tkh.generoiID("Pelaaja")); // -Toimii
//			System.out.println(tkh.generoiID("Rata")); // -Toimii
//			System.out.println(tkh.generoiID("Peli")); // Toimii
//			
//			tkh.luoPelaaja("Kalle", "34733101", "Tampere"); // -Toimii
//			tkh.poistaPelaaja(12071996); // -Toimii
			
//			tkh.luoRata("Lideon frisbeegolfrata","A1", 18, "Lieto", "Niilo"); // -Toimii
//			tkh.luoRata(23, "Lauste", "AA1" , 21, "Lauste", "Hannu"); // -Toimii
			
//			tkh.vaihdaPelaajanPuhelinnumero(141293, "0501337912"); // -Toimii
//			tkh.vaihdaPelaajanKotipaikka(141293, "Turku"); // -Toimii
			
//			tkh.luoPeli(23, "25.05.2016"); // -Toimii
			
//			tkh.vaihdaRatamestari(12760, "Pelle Hermanni"); // -Toimii
			
//			for (int i = 1; i < 23; i++){
//				tkh.luoVayla(23, 3, i, (int)(30 + Math.random()*120.0)); //Toimii
//			}
			
//			tkh.pelaajaPeliin(3, 141293); //-Toimii
//			tkh.pelaajaPeliin(3, 12071994);
//			tkh.poistaPelaajaPelista(12071994, 3); // -Toimii
			
//			for ( int i = 1; i < 23; i++){
//				tkh.luoSuoritus(141293, 3, 23, i, 1+(int)(Math.random()*4)); // -Toimii
//			}
//			tkh.poistaSuoritus(141293, 3, 23, 22); //-Toimii
//			tkh.luoSuoritus(141293, 3, 23, 22, 3);
			
//			tkh.korjaaHeittojenLkm(141293, 3, 23, 22, 4); // -Toimii
			
//			tulostaTaulut();
			
			System.out.println("\nRadan enn‰tys");
			tulostaResultSet(tkh.radanEnnatys(12760));
			System.out.println("\nPelaajien tiedot");
			tulostaResultSet(tkh.pelaajanTiedot(141293));
			System.out.println("\nRadan tiedot");
			tulostaResultSet(tkh.radanTiedot(12760));
			System.out.println("\nV‰yl‰n tiedot");
			tulostaResultSet(tkh.vaylanTiedot(23, 10));
			System.out.println("\nPelaajan suoritukset v‰yl‰ll‰");
			tulostaResultSet(tkh.pelaajanSuorituksetVaylalla(141293, 12760, 4));
			System.out.println("\nPelin voittaja");
			tulostaResultSet(tkh.pelinVoittaja(1));
			System.out.println("\nPelin pelaajien tiedot");
			tulostaResultSet(tkh.pelinPelaajienTiedot(1));
			System.out.println("\nPelaajan enn‰tys radalla");
			tulostaResultSet(tkh.pelaajanEnnatysRadalla(141293, 12760));
			System.out.println("\nRadan par");
			tulostaResultSet(tkh.radanPar(12760));
			
			//Testataan pelin lopputulosek testaamista
			TulostaPelinTulos(1);
			
		} catch (Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage());
			System.exit(0); //This or something else?
		}
	
	}
	/**<b>Testaamiseen tarkoitettu metodi</b>
	 * Tulostaa tietokannan taulut
	 * @throws SQLException
	 */
	public static void tulostaTaulut() throws SQLException{

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
		
		// VÔøΩylÔøΩ -taulu
		
		rs = stmt.executeQuery("SELECT * FROM Vayla");
		
		System.out.println("\nV‰yl‰");
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

	}
	/**<b>Testaamiseen tarkoitettu metodi</b>
	 * Tulostaa parametrina annetun pelin tiedot
	 * @param pelin_id int
	 */
	public static void TulostaPelinTulos(int pelin_id){
		
		try {
			ResultSet lopputulos = tkh.pelinLopputulos(pelin_id);

			//TODO Tulosta my√∂s pelin p√§iv√§m√§√§r√§
			System.out.println("Pelin " + pelin_id + " lopputulos");
			
			while( lopputulos.next() ){
				String pelaajan_nimi = lopputulos.getString("nimi");
				int tulos = lopputulos.getInt("summa");
				
				//TODO Teen siistimpi tulostus
				System.out.println(pelaajan_nimi + " | " + tulos);
			}
			
		} catch (SQLException e) {
			System.out.println("Jokin meni pieleen. Pelin lopputulosta ei pystyt√§ tulostamaan.");
			e.printStackTrace();
		}
		
	}
	
	/**<b>Testaamiseen tarkoitettu metodi</b>
	 * Tulostaa parametrina annetun resultsetin tiedot
	 * @param rs tulostettava ResultSet
	 */
	public static void tulostaResultSet(ResultSet rs){
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			if(rsmd.getColumnCount()==1){
				System.out.println(rsmd.getColumnName(1)+"\n"+rs.getString(1));
			}else{
				String names = "";
				for(int i = 1; i < rsmd.getColumnCount();i++){
					if(i == rsmd.getColumnCount()){
						names+=" " + rsmd.getColumnName(i);
					}else{
						names+=" " + rsmd.getColumnName(i) + "\t |";
					}
				}
				while(rs.next()){
					String tmp = "";
					for(int i = 1; i < rsmd.getColumnCount(); i++){
						tmp += rs.getString(i) +"\t";
					}
					System.out.println(tmp);
				}
			}
			rs.getStatement().close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
