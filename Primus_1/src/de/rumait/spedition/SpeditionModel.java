package de.rumait.spedition;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SpeditionModel {

	private String spediID;
	private String spediName;
	private String spediBenutzerName;
	private String strasse;
	private String passwort, plz, ort;

	public SpeditionModel(String spediID, String spediName, String spediBenutzerName, String passwort, String spediStrasse, String plz, String ort) {
		
		this.spediID = spediID;
		this.spediName = spediName;
		this.spediBenutzerName = spediBenutzerName;
		this.strasse = spediStrasse;
		this.passwort = passwort;
		this.plz = plz;
		this.ort = ort;

	}



	public void spediMemberLoeschen(Statement statement, String id) throws SQLException {

		String sql = "DELETE FROM Spedition WHERE idSpedition='" + id + "'";
		try {

			statement.execute(sql);
			System.out.println("User deleted!");

		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}
	
	
	
	public ResultSet getAllSpeditionen(Statement statement) throws SQLException {

		ResultSet resultset = null;
		String sql = "SELECT Spedition.idSpedition, Spedition.SpeditionsName, Spedition.Benutzername, Spedition.Passwort, Spedition.Strasse, Orte.PLZ, Orte.Ort From Spedition "+
				"Inner join Orte on Spedition.Orte_idOrte = Orte.idOrte";
		
		System.out.println("Speditionen abfragen...");
		try {
			resultset = statement.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("Fehler beim abfragen der Speditionen");
			e.printStackTrace();
		}
		System.out.println("Speditionenabfrage erfolgreich");
		return resultset;

	}
	
	public ResultSet getSpeditionFromSearch(Statement statement, String spediID, String spediName) {
		ResultSet resultSet = null;

		String sql = "SELECT Spedition.idSpedition, Spedition.SpeditionsName, Spedition.Benutzername, Spedition.Passwort, Spedition.Strasse, Orte.PLZ, Orte.Ort FROM Spedition "+
				"INNER JOIN Orte ON Spedition.Orte_idOrte = Orte.idOrte  WHERE idSpedition='"+spediID+"' OR SpeditionsName='"+spediName+"'";
		try {
			System.out.println("Speditionen gezielt abfragen...");
			resultSet = statement.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("Speditionen abfragen fehlgeschlagen!");
			e.printStackTrace();
		}
		System.out.println("SSpeditionenabfrage erfolgreich");
		return resultSet;
	}
	
	public boolean changeStationInfo(Statement statement, String spediID, String spediName, String spediBenutzerName,
			String passwort, String strasse, String plz, String ort) throws SQLException {
		
		String sqlOrt = "INSERT IGNORE INTO Orte VALUES (null, '" + plz + "','" + ort + "')";
		String sql = "UPDATE Spedition SET Spedition.SpeditionsName ='"+spediName+"' , Spedition.Benutzername ='"+spediBenutzerName+"', Spedition.Passwort='"+passwort+"', "+
				 " Spedition.Strasse='"+strasse+"', Spedition.Orte_idOrte=(SELECT Orte.idOrte FROM Orte WHERE PLZ='"+plz+"') WHERE Spedition.idSpedition='"+spediID+"'";

		System.out.println("Daten werden geändert....");

		statement.execute(sqlOrt);

		statement.execute(sql);
		System.out.println("Daten Änderung erfolgreich!");
		return true;
	}
	
	public boolean createShopMember(Statement statement, String ort, String plz, String spediBenutzerName, String passwort,
			String spediName, String strasse) throws SQLException {

		try {

			String sqlOrt = "INSERT IGNORE INTO Orte VALUES (null,'" + plz + "','" + ort + "')";
			String sqluser = "INSERT INTO Spedition VALUES (null," + "(SELECT idOrte FROM Orte WHERE PLZ='" + plz + "'),'"
					+ spediBenutzerName + "','" + passwort + "','" + spediName + "','" + strasse + "')";

			System.out.println("Creating...");
			statement.executeUpdate(sqlOrt);
			System.out.println("Ort created!");

			System.out.println("Creating....");
			statement.executeUpdate(sqluser);
			System.out.println("Shop user created!");

		} catch (Exception e) {
			System.out.println(e.getStackTrace());

			return false;
		}
		return true;

	}
	

	public String getSpediID() {
		return spediID;
	}

	public void setSpediID(String spediID) {
		this.spediID = spediID;
	}

	public String getSpediName() {
		return spediName;
	}

	public void setSpediName(String spediName) {
		this.spediName = spediName;
	}


	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getSpediBenutzerName() {
		return spediBenutzerName;
	}

	public void setSpediBenutzerName(String spediBenutzerName) {
		this.spediBenutzerName = spediBenutzerName;
	}
	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public SpeditionModel() {

	}

}
