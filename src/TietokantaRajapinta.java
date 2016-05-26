import java.sql.ResultSet;
import java.sql.SQLException;

public interface TietokantaRajapinta {

	//.o.o.o.o.o.o.o.o.o.o.o.o. LUOMIS-, POISTAMIS- JA PÄIVITYSMETODIT .o.o.o.o.o.o.o.o.o.o.o.o.
	
	//------ Pelaaja ------------
	/**
	 * Luo tietokantaan uuden pelaajan parametrina annettujen arvojen mukaisesti Pelaaja-tableen
	 * @param pelaajanID Pelaajan ID
	 * @param pelaajanNimi String
	 * @param puhnum String, maksimissaan 13 merkki�
	 * @param kotipaikka String, maksimissaan 30 merkki�
	 * @throws SQLException Pelaajan luonti ei onnistunut
	 */
	public void luoPelaaja(int pelaajanID, String pelaajanNimi, String puhnum, String kotipaikka) throws SQLException;
	
	
	/**
	 * Luo tietokantaan uuden pelaajan parametrina annettujen arvojen mukaisesti Pelaaja-tableen<br>
	 * METODIN T�YTYY GENEROIDA SEURAAVA <b>VAPAA</b> ID
	 * @param pelaajanNimi String
	 * @param puhnum String, maksimissaan 13 merkki�
	 * @param kotipaikka String, maksimissaan 30 merkki�
	 * @throws SQLException Pelaajan luonti ei onnistunut
	 */
	public void luoPelaaja(String pelaajanNimi, String puhnum, String kotipaikka) throws SQLException;
	
	/**
	 * Poistetaan parametrina annettu pelaaja
	 * <b>ALKUEHTO:</b> Pelaajasta ei saa olla ilmentymi� muissa tauluissa
	 * @param pelaajanID int
	 * @throws SQLException Pelaajan poisto ei onnistunut<br>Kysytty� pelaajaa ei mahdollisesti l�ytynyt
	 */
	public void poistaPelaaja(int pelaajanID) throws SQLException;
	
	/**
	 * Vaihdetaan parametrina annetun pelaajan puhelinnumero parametrina annetuksi
	 * @param pelaajanID int
	 * @param uusiPuhnum String, maksimissaan 13 merkki�
	 * @throws SQLException Pelaajan puhelinnumeron vaihto ei onnistunut
	 */
	public void vaihdaPelaajanPuhelinnumero(int pelaajanID, String uusiPuhnum) throws SQLException;
	
	/**
	 * Vaihtaa parametrina annetun pelaajan kotipaikka parametrina annetuksi
	 * @param pelaajanID int
	 * @param uusiKotipaikka String, maksimissaan 30 merkki�
	 * @throws SQLException Pelaajan kotipaikan vaihto ei onnistunut
	 */
	public void vaihdaPelaajanKotipaikka(int pelaajanID, String uusiKotipaikka) throws SQLException;
	
	//------ Rata ------------
	
	/**
	 * Luo uuden Radan tietokantaan parametrina annettujen arvojen mukaan
	 * HUOM: INT ID T�YTYY GENEROIDA METODISSA!
	 * @param radan_id int
	 * @param nimi
	 * @param luokitus
	 * @param vaylienLkm
	 * @param osoite
	 * @param ratamestari
	 * @throws SQLException
	 */
	public void luoRata(int radan_id, String nimi, String luokitus, int vaylienLkm, String osoite, String ratamestari) throws SQLException;
	
	/**
	 * Luo uuden Radan tietokantaan parametrina annettujen arvojen mukaan
	 * @param nimi
	 * @param luokitus
	 * @param vaylienLkm
	 * @param osoite
	 * @param ratamestari
	 * @throws SQLException
	 */
	public void luoRata(String nimi, String luokitus, int vaylienLkm, String osoite, String ratamestari) throws SQLException;

	/**
	 * Vaihtaa parametrina annetun radan ratamestarin parametrina annetuksi
	 * @param radan_id int
	 * @param uusiRatamestari String
	 * @throws SQLException Radan Ratamestarin vaihto ei onnistunut
	 */
	public void vaihdaRatamestari(int radan_id, String uusiRatamestari) throws SQLException;
	
	//------ Väylä ------------
	/**
	 * Luo tietokantaan parametrina annettujen arvojen mukaisen vaylan
	 * @param radan_id int
	 * @param par int
	 * @param numero int
	 * @param pituus int
	 * @throws SQLException V�yl�n luonti ei onnistunut
	 */
	public void luoVayla(int radan_id, int par, int numero, int pituus) throws SQLException;
	
	//------ Peli ------------
	/**
	 * Luo tietokantaan parametreina annettujen arvojen mukaisen Pelin
	 * @param radan_id int
	 * @param paivamaara String, maksimissaan 10 merkki�
	 * @throws SQLException Pelin luonti ei onnistunut
	 */
	public void luoPeli(int radan_id, String paivamaara) throws SQLException;
	
	/**
	 * Lis�t��n parametrina annettu pelaaja parametrina annettuun peliin<br>
	 * <b>ALKUEHTO:</b> Pelin t�ytyy olla olemassa
	 * @param pelin_id int
	 * @param pelaajan_id int
	 * @throws SQLException Pelaajan lis��minen peliin ei onnistunut<br>Pelaajaa tai Peli� ei ole olemassa
	 */
	public void pelaajaPeliin(int pelin_id, int pelaajan_id) throws SQLException;
	
	/**
	 * Poistaa pelaajan pelist�
<<<<<<< HEAD
	 * @param pelaajan_id
	 * @param peli_id
	 * @throws SQLException
=======
	 * @param pelaaja_id int
	 * @param peli_id int
	 * @throws SQLException Pelaajan poistaminen pelist� ei onnistunut
>>>>>>> 9248007e6d75609def016799eddafa90308f6042
	 */
	public void poistaPelaajaPelista(int pelaajan_id, int peli_id) throws SQLException;
	
	//------ Suoritus ----------
	/**
	 * Luo parametreina annettujen arvojen mukaisen pelin tietokantaan
	 * @param pelaajan_id int
	 * @param pelin_id int
	 * @param radan_id int
	 * @param vaylannumero int
	 * @param heittojen_lkm int
	 * @throws SQLException Suorituksen luominen ei onnistunut
	 */
	public void luoSuoritus(int pelaajan_id, int pelin_id, int radan_id, int vaylannumero, int heittojen_lkm) throws SQLException;

	/**
	 * Poistetaan parametreina annettu suoritus
	 * @param pelaajan_id int
	 * @param pelin_id int
	 * @param radan_id int
	 * @param vaylannumero int
	 * @throws SQLException Suorituksen poistaminen ei onnistunut
	 */
	public void poistaSuoritus(int pelaajan_id, int pelin_id, int radan_id, int vaylannumero) throws SQLException;
	
	/**
	 * Vaihtaa parametreja vastaavan pelin heittojen lukum��r�n
	 * @param pelaajan_id int
	 * @param pelin_id int
	 * @param radan_id int
	 * @param vaylannumero int
	 * @param heittojen_lkm int
	 * @throws SQLException Heittojenlukum��r�n vaihtaminen ei onnistunut
	 */
	public void korjaaHeittojenLkm(int pelaajan_id, int pelin_id, int radan_id, int vaylannumero, int heittojen_lkm) throws SQLException;
	
	//.o.o.o.o.o.o.o.o.o.o.o.o. YKSINKERTAISET HAUT .o.o.o.o.o.o.o.o.o.o.o.o.
		
	//KYSELYT JOTKA PALAUTTAVAT RESULTSETIN
	
	//>> Yksittäisen pelin lopputulos (listaus yksittäisten pelaajien kokonaistuloksista tietyssä pelissä) ja pelin voittaja
	/**
	 * Palauttaa parametrina annettua arvoa vastaavan pelin tiedot ResultSettin�
	 * @param pelin_id int
	 * @return ResultSet, sis�lt�� kysytyn pelin lopputuloksen
	 * @throws SQLException
	 */
	public ResultSet pelinLopputulos(int pelin_id) throws SQLException;
	
	//>> Yksittäisen pelin voittajan nimi ja tulos (= heittojen yhteismäärä)
	/** Palauttaa parametrina annettua arvoa vastaavan pelin voittaja
	 * @param pelin_id int
	 * @return ResultSet, sis�lt�� kysytyn pelin voittajann
	 * @throws SQLException
	 */
	public ResultSet pelinVoittaja(int pelin_id) throws SQLException;
	
	//>> Jonkin radan ennätyssuoritus
	/**
	 * Palauttaa parametrina annettua arvoa vastaavan radan ennn�tyksen ResultSettin�
	 * @param radan_id int
	 * @return ResultSet, sis�lt�� kysytyn radan enn�tyksen
	 * @throws SQLException
	 */
	public ResultSet radanEnnatys(int radan_id) throws SQLException;
	
	//>> Yksittäisen pelaajan keskiarvotulos jollakin tietyllä väylällä (tieto ei tietenkään ole järin mielenkiintoinen, jos pelaajalla on vain muutama tulos väylältä)
	/**
	 * Palauttaa parametrina annettua arvoa vastaavan pelaajan tiedot ResultSettin�
	 * @param pelaaja_id int
	 * @return ResultSet, sis�lt�� kysytyn pelaajan tiedot
	 * @throws SQLException
	 */
	public ResultSet pelaajanTiedot(int pelaaja_id) throws SQLException;
	
	/**
	 * Palauttaa parametrina annettua vastaavan radan tiedot
	 * @param radan_id int
	 * @return ResultSet, sis�lt�� kysytyn radan tiedot
	 * @throws SQLException
	 */
	public ResultSet radanTiedot(int radan_id) throws SQLException;

	/**
	 * Palauttaa parametria annetun v�yl�n tiedot
	 * @param radan_id int
	 * @param vaylan_numero int
	 * @return ResultSet, sis�lt�� kysytyn v�yl�n tiedot
	 * @throws SQLException
	 */
	public ResultSet vaylanTiedot(int radan_id, int vaylan_numero) throws SQLException;
	
	/**
	 * Palauttaa parametrina annetun pelaajan suoritukset pyydetylt� v�yl�lt�
	 * @param pelaajan_id int
	 * @param radan_id int
	 * @param vaylan_numero int
	 * @return ResultSet, sis�lt�� kysytyn pelaajan suoritukset kysytyll� v�yl�ll�
	 * @throws SQLException
	 */
	public ResultSet pelaajanSuorituksetVaylalla(int pelaajan_id, int radan_id, int vaylan_numero) throws SQLException;
	
	/**
	 * Palauttaa pelaajan henkilökohtaisen ennätyksen pyydetyllä radalla.
	 * @param pelaajan_id int 
	 * @param radan_id int
	 * @return ResultSet, sisältää kysytyn pelaaan nimen, kokonaistuloksen ja suorituksen päivämäärän
	 * @throws SQLException
	 */
	public ResultSet pelaajanEnnatysRadalla(int pelaajan_id, int radan_id) throws SQLException;
	
	/**
	 * Palauttaa pelaajan kaikkien aikaan parhaan tuloksen (vähiten heittoja) ja radan, jolla tulos on tehty.
	 * @param pelaajan_id
	 * @param radan_id
	 * @return
	 * @throws SQLException
	 */
	public ResultSet pelaajanEnnatys(int pelaajan_id, int radan_id) throws SQLException;
	
	/**
	 * Palauttaa parametrina annetun pelin pelaajien tiedot
	 * @param pelin_id
	 * @return ResultSet, sis�lt�� pelin pelaajien nimen, puhelinnumeron ja kotipaikan
	 * @throws SQLException
	 */
	public ResultSet pelinPelaajienTiedot(int pelin_id) throws SQLException;
	
	/**
	 * Palauttaa parametrina kysytyn radan v�ylien yhteisen par
	 * @param radan_id int
	 * @return ResultSet, sis�lt�� Radan Par
	 * @throws SQLException
	 */
	public ResultSet radanPar(int radan_id) throws SQLException;
}
