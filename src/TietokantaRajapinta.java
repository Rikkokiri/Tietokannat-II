import java.sql.ResultSet;
import java.sql.SQLException;

public interface TietokantaRajapinta {

	/*
	 * Tällä hetkellä kaikki metodit palauttavat boolean-arvon, joka kertoo onnistuiko pyydetty operaatio.
	 * Tämä käytäntö ei ole välttämätön.
	 * 
	 * Mitä mieltä olette?
	 *  
	 */
	
	//.o.o.o.o.o.o.o.o.o.o.o.o. LUOMIS-, POISTAMIS- JA PÄIVITYSMETODIT .o.o.o.o.o.o.o.o.o.o.o.o.
	
	//------ Pelaaja ------------
	
	public void luoPelaaja(int pelaajanID, String pelaajanNimi, String puhnum, String kotipaikka) throws SQLException;
	
	//Voiko pelaajan itse asiassa poistaa?
	//Tätäkin täytyy pohtia.
	public void poistaPelaaja(int pelaajanID) throws SQLException;
	
	public void vaihdaPelaajanPuhelinnumero(int pelaajanID, int uusiPuhnum) throws SQLException;
	
	public void vaihdaPelaajanKotipaikka(int pelaajanID, String uusiKotipaikka) throws SQLException;
	
	//------ Rata ------------
	
	/*
	 * Huom!
	 * 
	 * LUOKITUS CHAR(4)
	 * OSOITE CHAR(40)
	 */
	public void luoRata(int radan_id, String luokitus, int vaylienLkm, String osoite, String ratamestari) throws SQLException;
	
	public void vaihdaRatamestari(String uusiRatamestari) throws SQLException;
	
	//------ Väylä ------------
	
	public void luoVayla(int radan_id, int par, int numero, int pituus) throws SQLException;
	
	//------ Peli ------------
	
	public void luoPeli(int radan_id, String paivamaara) throws SQLException;
	
	//Pitäisikö peli kuitenkin voida poistaa?
	
	//Seuraavat koskevat Pelaamassa-taulua...
	/*
	 * CREATE TABLE Pelaamassa(
	pelin_id INT NOT NULL,
	pelaajan_id INT NOT NULL,
	FOREIGN KEY(pelin_id) REFERENCES Peli(pelin_id),
	FOREIGN KEY(pelaajan_id) REFERENCES Pelaaja(pelaajan_id)
	);
	 */
	
	public void pelaajaPeliin(int pelin_id, int pelaajan_id) throws SQLException;
	
	public void poistaPelaajaPelista() throws SQLException;
	
	//------ Suoritus ----------
	public void luoSuoritus(int pelaajan_id, int pelin_id, int radan_id, int vaylannumero, int heittojen_lkm) throws SQLException;
	
	//Kai suorituksen poistamisen pitää olla mahdollista?
	public void poistaSuoritus(int pelaajan_id, int pelin_id, int radan_id, int vaylannumero) throws SQLException;
	
	//Suorituksen muuttamisen/korjaamisen mahdollistavia metodeita?
	//Tarpeettomia?
	// - Hyvinkin tarpeellisia, aika monta kertaa merkannut vahingossa v��rin -Ville
	public void korjaaHeittojenLkm(int pelaajan_id, int pelin_id, int radan_id, int vaylannumero, int heittojen_lkm) throws SQLException;
	
	//.o.o.o.o.o.o.o.o.o.o.o.o. YKSINKERTAISET HAUT .o.o.o.o.o.o.o.o.o.o.o.o.
	
		//Pitäisikö näiden palauttaa ResultSet vai tulostaa haun tulokset? Vai antaa palaute jossain muussa muodossa?
	
	//Suunnitelmassa on luvattu seuraavat haut:
	
	//>> Yksittäisen pelin lopputulos (listaus yksittäisten pelaajien kokonaistuloksista tietyssä pelissä) ja pelin voittaja
	public ResultSet pelinLopputulos(int pelin_id) throws SQLException;
	
	//>> Jonkin radan ennätyssuoritus
	public ResultSet radanEnnatys(int radan_id) throws SQLException;
	
	//>> Yksittäisen pelaajan keskiarvotulos jollakin tietyllä väylällä (tieto ei tietenkään ole järin mielenkiintoinen, jos pelaajalla on vain muutama tulos väylältä)
	 
	public ResultSet PelaajanTiedot(int pelaaja_id) throws SQLException;
	
	//Lisäksi voitaisiin valmiiksi toteuttaa...
	
		//Ideoita?
	
	
}
