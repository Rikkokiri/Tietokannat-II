import java.sql.Connection;
import java.sql.DriverManager;

public class Tietokantahaltija implements TietokantaRajapinta {
	
	private static Connection connection = null;

	//------- Constructor -----------------------
	
	public Tietokantahaltija(){
		try{
			Class.forName("org.sqlite.JBC");
			connection = DriverManager.getConnection("jdbc:sqlite:frisbee.db");
			
		} catch (Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage());
			System.exit(0); //This or something else?
		}
	
		System.out.println("Opened database successfully. Yasss.");
	}
	//-------------------------------------------

	@Override
	public boolean luoPelaaja(int pelaajanID, String pelaajanNimi, int puhnum, String kotipaikka) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean poistaPelaaja(int pelaajanID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean vaihdaPelaajanPuhelinnumero(int pelaajanID, int uusiPuhnum) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean vaihdaPelaajanKotipaikka(int pelaajanID, int uusiKotipaikka) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean luoRata(int radanID, String radanLuokitus, int vaylienLkm, String osoite, String ratamestari) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean luoPeli() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}