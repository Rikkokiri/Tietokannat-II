import java.sql.*;

public class Tietokantahaltija implements TietokantaRajapinta {
	
	private static Connection connection = null;

	//------- Constructor -----------------------
	
	public Tietokantahaltija(){
		try{
			Class.forName("org.sqlite.JBC");
			connection = DriverManager.getConnection("jdbc:sqlite:frisbee.db");
			connection.setAutoCommit(false);
		} catch (Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage());
			System.exit(0); //This or something else?
		}
	
		System.out.println("Opened database successfully. Yasss.");
	}
	//-------------------------------------------

	@Override
	public boolean luoPelaaja(int pelaajanID, String pelaajanNimi, int puhnum, String kotipaikka) {
		Statement stmt = null;
		try{
			stmt = connection.createStatement();
			String sql = "INSERT INTO Pelaaja";
		}catch(Exception e) {
			
		}
		
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