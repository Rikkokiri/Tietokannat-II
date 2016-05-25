import java.sql.ResultSet;
import java.sql.SQLException;

public interface TietokantaRajapinta {

	/*
	 * T√§ll√§ hetkell√§ kaikki metodit palauttavat boolean-arvon, joka kertoo onnistuiko pyydetty operaatio.
	 * T√§m√§ k√§yt√§nt√∂ ei ole v√§ltt√§m√§t√∂n.
	 * 
	 * Mit√§ mielt√§ olette?
	 *  
	 */
	
	//.o.o.o.o.o.o.o.o.o.o.o.o. LUOMIS-, POISTAMIS- JA P√ÑIVITYSMETODIT .o.o.o.o.o.o.o.o.o.o.o.o.
	
	//------ Pelaaja ------------
	/**
	 * Luo tietokantaan uuden pelaajan parametrina annettujen arvojen mukaisesti Pelaaja-tableen
	 * @param pelaajanID Pelaajan ID
	 * @param pelaajanNimi String
	 * @param puhnum String, maksimissaan 13 merkki‰
	 * @param kotipaikka String, maksimissaan 30 merkki‰
	 * @throws SQLException
	 */
	public void luoPelaaja(int pelaajanID, String pelaajanNimi, String puhnum, String kotipaikka) throws SQLException;
	
	
	/**
	 * Luo tietokantaan uuden pelaajan parametrina annettujen arvojen mukaisesti Pelaaja-tableen
	 * METODIN TƒYTYY GENEROIDA SEURAAVA VAPAA ID
	 * @param pelaajanNimi String
	 * @param puhnum String, maksimissaan 13 merkki‰
	 * @param kotipaikka String, maksimissaan 30 merkki‰
	 * @throws SQLException
	 */
	public void luoPelaaja(String pelaajanNimi, String puhnum, String kotipaikka) throws SQLException;
	
	/**
	 * Poistetaan parametrina annettu pelaaja edellytt‰en ettei kyseist‰ pelaajaa ilmene muissa tauluissa
	 * @param pelaajanID
	 * @throws SQLException
	 */
	public void poistaPelaaja(int pelaajanID) throws SQLException;
	
	/**
	 * Vaihdetaan parametrina annetun pelaajan puhelinnumero parametrina annetuksi
	 * @param pelaajanID
	 * @param uusiPuhnum String, maksimissaan 13 merkki‰
	 * @throws SQLException
	 */
	public void vaihdaPelaajanPuhelinnumero(int pelaajanID, int uusiPuhnum) throws SQLException;
	
	/**
	 * Vaihtaa parametrina annetun pelaajan kotipaikka parametrina annetuksi
	 * @param pelaajanID
	 * @param uusiKotipaikka String, maksimissaan 30 merkki‰
	 * @throws SQLException
	 */
	public void vaihdaPelaajanKotipaikka(int pelaajanID, String uusiKotipaikka) throws SQLException;
	
	//------ Rata ------------
	
	/*
	 * Huom!
	 * 
	 * LUOKITUS CHAR(4)
	 * OSOITE CHAR(40)
	 */
	/**
	 * 
	 * @param radan_id int
	 * @param luokitus
	 * @param vaylienLkm
	 * @param osoite
	 * @param ratamestari
	 * @throws SQLException
	 */
	public void luoRata(int radan_id, String luokitus, int vaylienLkm, String osoite, String ratamestari) throws SQLException;
	
	/**
	 * Luo uuden Radan tietokantaan parametrina annettujen arvojen mukaan
	 * HUOM: INT ID TƒYTYY GENEROIDA METODISSA!
	 * @param luokitus
	 * @param vaylienLkm
	 * @param osoite
	 * @param ratamestari
	 * @throws SQLException
	 */
	public void luoRata(String luokitus, int vaylienLkm, String osoite, String ratamestari) throws SQLException;

	/**
	 * Vaihtaa parametrina annetun radan ratamestarin parametrina annetuksi
	 * @param radan_id
	 * @param uusiRatamestari
	 * @throws SQLException
	 */
	public void vaihdaRatamestari(int radan_id ,String uusiRatamestari) throws SQLException;
	
	//------ V√§yl√§ ------------
	/**
	 * Luo tietokantaan parametrina annettujen arvojen mukaisen vaylan
	 * @param radan_id
	 * @param par
	 * @param numero
	 * @param pituus
	 * @throws SQLException
	 */
	public void luoVayla(int radan_id, int par, int numero, int pituus) throws SQLException;
	
	//------ Peli ------------
	/**
	 * Luo tietokantaan parametreina annettujen arvojen mukaisen Pelin
	 * @param radan_id
	 * @param paivamaara
	 * @throws SQLException
	 */
	public void luoPeli(int radan_id, String paivamaara) throws SQLException;
	
	/**
	 * Lis‰t‰‰n parametrina annettu pelaaja parametrina annettuun peliin
	 * ALKUEHTO: Pelin t‰ytyy olla olemassa
	 * @param pelin_id
	 * @param pelaajan_id
	 * @throws SQLException
	 */
	public void pelaajaPeliin(int pelin_id, int pelaajan_id) throws SQLException;
	
	/**
	 * Poistaa pelaajan pelist‰
	 * @param pelaaja_id
	 * @param peli_id
	 * @throws SQLException
	 */
	public void poistaPelaajaPelista(int pelaaja_id, int peli_id) throws SQLException;
	
	//------ Suoritus ----------
	/**
	 * Luo parametreina annettujen arvojen mukaisen pelin tietokantaan
	 * @param pelaajan_id
	 * @param pelin_id
	 * @param radan_id
	 * @param vaylannumero
	 * @param heittojen_lkm
	 * @throws SQLException
	 */
	public void luoSuoritus(int pelaajan_id, int pelin_id, int radan_id, int vaylannumero, int heittojen_lkm) throws SQLException;
	
	//Kai suorituksen poistamisen pit√§√§ olla mahdollista?
	/**
	 * Poistetaan parametreina annettu suoritus
	 * @param pelaajan_id
	 * @param pelin_id
	 * @param radan_id
	 * @param vaylannumero
	 * @throws SQLException
	 */
	public void poistaSuoritus(int pelaajan_id, int pelin_id, int radan_id, int vaylannumero) throws SQLException;
	
	//Suorituksen muuttamisen/korjaamisen mahdollistavia metodeita?
	//Tarpeettomia?
	// - Hyvinkin tarpeellisia, aika monta kertaa merkannut vahingossa v‰‰rin -Ville
	/**
	 * Vaihtaa parametreja vastaavan pelin heittojen lukum‰‰r‰n
	 * @param pelaajan_id
	 * @param pelin_id
	 * @param radan_id
	 * @param vaylannumero
	 * @param heittojen_lkm
	 * @throws SQLException
	 */
	public void korjaaHeittojenLkm(int pelaajan_id, int pelin_id, int radan_id, int vaylannumero, int heittojen_lkm) throws SQLException;
	
	//.o.o.o.o.o.o.o.o.o.o.o.o. YKSINKERTAISET HAUT .o.o.o.o.o.o.o.o.o.o.o.o.
	
		//Pit√§isik√∂ n√§iden palauttaa ResultSet vai tulostaa haun tulokset? Vai antaa palaute jossain muussa muodossa?
	
	//KYSELYT JOTKA PALAUTTAVAT RESULTSETIN
	
	//>> Yksitt√§isen pelin lopputulos (listaus yksitt√§isten pelaajien kokonaistuloksista tietyss√§ peliss√§) ja pelin voittaja
	/**
	 * Palauttaa parametrina annettua arvoa vastaavan pelin tiedot ResultSettin‰
	 * @param pelin_id
	 * @return
	 * @throws SQLException
	 */
	public ResultSet pelinLopputulos(int pelin_id) throws SQLException;
	
	//>> Jonkin radan enn√§tyssuoritus
	/**
	 * Palauttaa parametrina annettua arvoa vastaavan radan ennn‰tyksen ResultSettin‰
	 * @param radan_id
	 * @return
	 * @throws SQLException
	 */
	public ResultSet radanEnnatys(int radan_id) throws SQLException;
	
	//>> Yksitt√§isen pelaajan keskiarvotulos jollakin tietyll√§ v√§yl√§ll√§ (tieto ei tietenk√§√§n ole j√§rin mielenkiintoinen, jos pelaajalla on vain muutama tulos v√§yl√§lt√§)
	/**
	 * Palauttaa parametrina annettua arvoa vastaavan pelaajan tiedot ResultSettin‰
	 * @param pelaaja_id
	 * @return
	 * @throws SQLException
	 */
	public ResultSet pelaajanTiedot(int pelaaja_id) throws SQLException;
	
	/**
	 * Palauttaa parametrina annettua vastaavan radan tiedot
	 * @param radan_id
	 * @return
	 * @throws SQLException
	 */
	public ResultSet radanTiedot(int radan_id) throws SQLException;

	/**
	 * 
	 * @param radan_id
	 * @param vaylan_numero
	 * @return
	 * @throws SQLException
	 */
	public ResultSet vaylanTiedot(int radan_id, int vaylan_numero) throws SQLException;
	
	/**
	 * 
	 * @param pelaajan_id
	 * @param radan_id
	 * @param vaylan_numero
	 * @return
	 * @throws SQLException
	 */
	public ResultSet pelaajanSuorituksetVaylalla(int pelaajan_id, int radan_id, int vaylan_numero) throws SQLException;
	
}
