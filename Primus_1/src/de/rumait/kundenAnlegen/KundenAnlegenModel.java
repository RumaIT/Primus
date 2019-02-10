package de.rumait.kundenAnlegen;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
public class KundenAnlegenModel {
	
	private int stationsID;
	private String stationsName;
	

	public KundenAnlegenModel(String stationsName) {
		super();
		this.stationsName = stationsName;
	}
	
	
	public KundenAnlegenModel() {
		
	}

	public ResultSet getStations (Statement statement) throws SQLException {
		ResultSet abfrage = null;
		abfrage = statement.executeQuery("Select ShopName From Shop");
		return abfrage;
	}
	
	public boolean createNewKunden(Statement statement, String name, String nachname, String strasse, String plz, String ort, String stationsName) throws SQLException {
		
		String sql= "INSERT IGNORE INTO Orte VALUES(null,'"+plz+"','"+ort+"')";
		String sql2 ="INSERT INTO Kunde VALUES(null,(SELECT idOrte FROM Orte WHERE PLZ='"+plz+"'),'"+name+"','"+nachname+"','"+strasse+"',(SELECT idShop FROM Shop WHERE ShopName='"+stationsName+"'))";
		statement.execute(sql);
		statement.execute(sql2);
		return true;
	}
	
	
	
	@Override
	public String toString() {
		return stationsName;
		
	}

	public int getStationsID() {
		return stationsID;
	}
	public void setStationsID(int stationsID) {
		this.stationsID = stationsID;
	}
	public String getStationsName() {
		return stationsName;
	}
	public void setStationsName(String stationsName) {
		this.stationsName = stationsName;
	}
	

}
