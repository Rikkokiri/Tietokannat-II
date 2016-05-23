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
	
	public boolean luoPelaaja(int pelaajanID, String pelaajanNimi, String puhnum, String kotipaikka) throws SQLException;
	
	//Voiko pelaajan itse asiassa poistaa?
	//Tätäkin täytyy pohtia.
	public boolean poistaPelaaja(int pelaajanID) throws SQLException;
	
	public boolean vaihdaPelaajanPuhelinnumero(int pelaajanID, int uusiPuhnum) throws SQLException;
	
	public boolean vaihdaPelaajanKotipaikka(int pelaajanID, int uusiKotipaikka) throws SQLException;
	
	//------ Rata ------------
	
	/*
	 * Huom!
	 * 
	 * LUOKITUS CHAR(4)
	 * OSOITE CHAR(40)
	 */
	public boolean luoRata(int radan_id, String luokitus, int vaylienLkm, String osoite, String ratamestari) throws SQLException;
	
	public boolean vaihdaRatamestari(String uusiRatamestari) throws SQLException;
	
	//------ Väylä ------------
	
	public boolean luoVäylä(int radan_id, int par, int numero, int pituus) throws SQLException;
	
	//------ Peli ------------
	
	public boolean luoPeli(int radan_id, String paivamaara) throws SQLException;
	
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
	
	public boolean pelaajaPeliin(int pelin_id, int pelaajan_id) throws SQLException;
	
	public boolean poistaPelaajaPelista() throws SQLException;
	
	//------ Suoritus ----------
	public boolean luoSuoritus(int pelaajan_id, int pelin_id, int radan_id, int vaylannumero, int heittojen_lkm) throws SQLException;
	
	//Kai suorituksen poistamisen pitää olla mahdollista?
	public boolean poistaSuoritus(int pelaajan_id, int pelin_id, int radan_id, int vaylannumero) throws SQLException;
	
	//Suorituksen muuttamisen/korjaamisen mahdollistavia metodeita?
	//Tarpeettomia?
	//public boolean korjaaHeittojenLkm(int pelaajan_id, int pelin_id, int radan_id, int vaylannumero, int heittojen_lkm) throws SQLException;
	
	//.o.o.o.o.o.o.o.o.o.o.o.o. YKSINKERTAISET HAUT .o.o.o.o.o.o.o.o.o.o.o.o.
	
	//Suunnitelmassa on luvattu seuraavat haut:
	
	//>> Yksittäisen pelin lopputulos (listaus yksittäisten pelaajien kokonaistuloksista tietyssä pelissä) ja pelin voittaja
	
	//>> Jonkin radan ennätyssuoritus
	
	
	//>> Yksittäisen pelaajan keskiarvotulos jollakin tietyllä väylällä (tieto ei tietenkään ole järin mielenkiintoinen, jos pelaajalla on vain muutama tulos väylältä)
	 
	 
	
	//Lisäksi voitaisiin valmiiksi toteuttaa...
	
		//Ideoita?
	
	
}
