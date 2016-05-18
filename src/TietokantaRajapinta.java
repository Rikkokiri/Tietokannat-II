
public interface TietokantaRajapinta {

	/*
	 * Kaikki metodit 
	 */
	
	//.o.o.o.o.o.o.o.o.o.o.o.o. LUOMIS-, POISTAMIS- JA PÄIVITYSMETODIT .o.o.o.o.o.o.o.o.o.o.o.o.
	
	//------ Pelaaja ------------
	
	public boolean luoPelaaja(int pelaajanID, String pelaajanNimi, int puhnum, String kotipaikka);
	
	public boolean poistaPelaaja(int pelaajanID);
	
	public boolean vaihdaPelaajanPuhelinnumero(int pelaajanID, int uusiPuhnum);
	
	public boolean vaihdaPelaajanKotipaikka(int pelaajanID, int uusiKotipaikka);
	
	//------ Rata ------------
	
	/*
	 * Huom!
	 * 
	 * LUOKITUS CHAR(4)
	 * OSOITE CHAR(40)
	 */
	public boolean luoRata(int radanID, String radanLuokitus, int vaylienLkm, String osoite, String ratamestari);
	
	
	
	//------ Väylä ------------
	
	
	
	//------ Peli ------------
	
	public boolean luoPeli();
	
	//.o.o.o.o.o.o.o.o.o.o.o.o. YKSINKERTAISET HAUT .o.o.o.o.o.o.o.o.o.o.o.o.
	
	
	
}