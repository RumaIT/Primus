package de.rumait.pakete;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PaketeModellStationTabelle {
	
	private String stationsID, zuweisungsID, paketID;


	public PaketeModellStationTabelle(String paketID, String stationsID, String zuweisungsID) {
		
		this.paketID = paketID;
		this.stationsID = stationsID;
		this.zuweisungsID = zuweisungsID;
	}
	
	public PaketeModellStationTabelle() {
		
	}
	
	
public ResultSet getStationPaketAll(Statement statement) throws SQLException {
		
		ResultSet abfrage = null;
		String sql = "SELECT * FROM ShopPaketZuweisung";
		abfrage = statement.executeQuery(sql);
		
	
		return abfrage;
	}

public boolean aendereStationPaketDaten(Statement statement, String stationsID, String paketID, String zuweisungsID) throws SQLException {
	
	String sql = "UPDATE ShopPaketZuweisung "+
			"SET Shop_idShop='"+stationsID+"', Pakete_idPakete='"+paketID+"' "+
			"WHERE idShopPaketZuweisung='"+zuweisungsID+"'";
	System.out.println(sql);
	statement.executeLargeUpdate(sql);
	
	return true;
}

public ResultSet stationPaketeSuchen(Statement statement, String paketID) throws SQLException {
	
	ResultSet abfrage = null;
	String sql = "SELECT * FROM ShopPaketZuweisung "+
				 "WHERE Pakete_IdPakete='"+paketID+"'";
	abfrage = statement.executeQuery(sql);		
	
	return abfrage;
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

	public String getPaketID() {
		return paketID;
	}

	public void setPaketID(String paketID) {
		this.paketID = paketID;
	}


}
