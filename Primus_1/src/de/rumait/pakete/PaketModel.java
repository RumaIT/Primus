package de.rumait.pakete;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PaketModel {
	
	private String paketID, kundenID, gewicht, breite, hoehe, verfolgungsID, status, Datum, uhrzeit, stationsID, zuweisungsID;

	

	public PaketModel(String paketID, String kundenID, String gewicht, String breite, String hoehe,
			String verfolgungsID, String status, String datum, String uhrzeit) {
		super();
		this.paketID = paketID;
		this.kundenID = kundenID;
		this.gewicht = gewicht;
		this.breite = breite;
		this.hoehe = hoehe;
		this.verfolgungsID = verfolgungsID;
		this.status = status;
		Datum = datum;
		this.uhrzeit = uhrzeit;
	}
	
	public PaketModel() {
		
	}

	public PaketModel(String paketID, String stationsID, String zuweisungsID) {
		super();
		this.paketID = paketID;
		this.stationsID = stationsID;
		this.zuweisungsID = zuweisungsID;
	}

	public ResultSet getAllPackgesFromDatabase(Statement statement) throws SQLException {
		
		ResultSet abfrage = null;
		
		String sql = "SELECT Pakete.idPakete, Kunde.idKunde, Pakete.Gewicht, Pakete.Breite, Pakete.Hoehe, PaketVerfolgung.idPaketVerfolgung, PaketVerfolgung.Status, PaketVerfolgung.Datum, PaketVerfolgung.Uhrzeit FROM Pakete " + 
				"INNER JOIN PaketVerfolgung ON Pakete.idPakete = PaketVerfolgung.Pakete_idPakete " + 
				"INNER join Kunde ON Pakete.Kunde_idKunde = Kunde.idKunde";
				
		abfrage =statement.executeQuery(sql);		
		
		return abfrage;
	}
	
	public ResultSet searchForPackagesOnDatabse(Statement statement, String paketID, String verfolgungsID, String nachname) throws SQLException {
		
		ResultSet abfrage = null;
		
		String sql = "SELECT Pakete.idPakete, Kunde.idKunde, Pakete.Gewicht, Pakete.Breite, Pakete.Hoehe, PaketVerfolgung.idPaketVerfolgung, PaketVerfolgung.Status, PaketVerfolgung.Datum, PaketVerfolgung.Uhrzeit FROM Pakete " + 
				"INNER JOIN PaketVerfolgung ON Pakete.idPakete = PaketVerfolgung.Pakete_idPakete " + 
				"INNER join Kunde ON Pakete.Kunde_idKunde = Kunde.idKunde " + 
				"WHERE Pakete.idPakete='"+paketID+"' OR PaketVerfolgung.idPaketVerfolgung='"+verfolgungsID+"' OR Kunde.Nachname='"+nachname+"'";
		
		abfrage = statement.executeQuery(sql);
		
		return abfrage;
	}
	
	public boolean getVersicherung (Statement statement, String paketID) throws SQLException {
		
		boolean versicherung = false;
	
		String sql = "SELECT Pakete.Versicherung FROM Pakete " + 
				"WHERE Pakete.idPakete='"+paketID+"'";
		
		ResultSet abfrage = statement.executeQuery(sql);
		
			while (abfrage.next()) {
				
				versicherung = abfrage.getBoolean(1);
				
			}
		return versicherung;
		
	}
	
	public boolean paketeAendern(Statement statement, String kundenID, String gewicht, String breite, String hoehe, String status, String datum, String uhrzeit, String versicherung, String paketID) throws SQLException {
		
		
		
		String sql = "UPDATE Pakete AS a "+
				  "INNER JOIN PaketVerfolgung AS b "+
				  "ON a.idPakete = b.Pakete_idPakete "+
				  "SET a.Kunde_idKunde='"+kundenID+"' , a.Gewicht='"+gewicht+"', a.Breite='"+breite+"', "+
				  "a.Hoehe='"+hoehe+"', b.Status='"+status+"', b.Datum='"+datum+"', b.Uhrzeit='"+uhrzeit+"', a.Versicherung='"+versicherung+"' "+
				  "WHERE a.idPakete='"+paketID+"'";
		
		statement.execute(sql);
		return true;
	
	}
	
	public boolean paketLoeschen(Statement statement, String paketID) throws SQLException {
		
		String sql = 	"DELETE FROM SpeditionPaketZuweisung " +
						"WHERE Pakete_idPakete='"+paketID+"'";
		
		String sql2= 	"DELETE FROM ShopPaketZuweisung " + 
						"WHERE Pakete_idPakete='"+paketID+"'";
				
		String sql3=	"DELETE FROM PaketVerfolgung " + 
						"WHERE Pakete_idPakete='"+paketID+"'";
				
		String sql4=	"DELETE FROM Pakete" + 
						"WHERE Pakete.idPakete='"+paketID+"'";
		
		statement.execute(sql);
		statement.execute(sql2);
		statement.execute(sql3);
		statement.execute(sql4);
		
		return true;
	}
	
	

	public String getPaketID() {
		return paketID;
	}

	public void setPaketID(String paketID) {
		this.paketID = paketID;
	}

	public String getKundenID() {
		return kundenID;
	}

	public void setKundenID(String kundenID) {
		this.kundenID = kundenID;
	}

	public String getGewicht() {
		return gewicht;
	}

	public void setGewicht(String gewicht) {
		this.gewicht = gewicht;
	}

	public String getBreite() {
		return breite;
	}

	public void setBreite(String breite) {
		this.breite = breite;
	}

	public String getHoehe() {
		return hoehe;
	}

	public void setHoehe(String hoehe) {
		this.hoehe = hoehe;
	}

	public String getVerfolgungsID() {
		return verfolgungsID;
	}

	public void setVerfolgungsID(String verfolgungsID) {
		this.verfolgungsID = verfolgungsID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDatum() {
		return Datum;
	}

	public void setDatum(String datum) {
		Datum = datum;
	}

	public String getUhrzeit() {
		return uhrzeit;
	}

	public void setUhrzeit(String uhrzeit) {
		this.uhrzeit = uhrzeit;
	}
	public String getStationsID() {
		return stationsID;
	}

	public void setStationsID(String stationsID) {
		this.stationsID = stationsID;
	}

	public String getZuweisungsID() {
		return zuweisungsID;
	}

	public void setZuweisungsID(String zuweisungsID) {
		this.zuweisungsID = zuweisungsID;
	}

}
