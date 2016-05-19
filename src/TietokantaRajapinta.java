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
	
	
	//------ Suoritus ----------
	
	//.o.o.o.o.o.o.o.o.o.o.o.o. YKSINKERTAISET HAUT .o.o.o.o.o.o.o.o.o.o.o.o.
	
	
	
}
