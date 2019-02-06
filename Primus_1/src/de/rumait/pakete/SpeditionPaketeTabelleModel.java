package de.rumait.pakete;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SpeditionPaketeTabelleModel {
	
	private String speditionID,paketID, zuweisungsID;


	public SpeditionPaketeTabelleModel(String speditionID, String paketID, String zuweisungsID) {
		this.setSpeditionID(speditionID);
		this.paketID = paketID;
		this.zuweisungsID = zuweisungsID;
	}
	
	
	public ResultSet getAllInfoFromDatabase(Statement statement) throws SQLException {
		ResultSet abfrage = null;
		String sql = "SELECT * FROM SpeditionPaketZuweisung";
		abfrage = statement.executeQuery(sql);
		return abfrage;
	}
	
	public SpeditionPaketeTabelleModel() {
		
		
	}
	
	public ResultSet searchTableSpeditionPakete(Statement statement, String paketID) throws SQLException {
		ResultSet abfrage = null;
		String sql = "SELECT * FROM SpeditionPaketZuweisung "+
					"WHERE Pakete_idPakete='"+paketID+"'";
		abfrage = statement.executeQuery(sql);
		return abfrage;
	}
	
	public boolean aendereDaten(Statement statement, String speditionID, String paketID, String zuweisungsID) throws SQLException {
		
		String sql = "UPDATE SpeditionPaketZuweisung " + 
				"SET Spedition_idSpedition='"+speditionID+"', Pakete_idPakete='"+paketID+"' "+
				"WHERE idSpeditionPaketZuweisung='"+zuweisungsID+"'";
		statement.execute(sql);
		return true;
	}

	public String getPaketID() {
		return paketID;
	}

	public void setPaketID(String paketID) {
		this.paketID = paketID;
	}

	public String getZuweisungsID() {
		return zuweisungsID;
	}

	public void setZuweisungsID(String zuweisungsID) {
		this.zuweisungsID = zuweisungsID;
	}


	public String getSpeditionID() {
		return speditionID;
	}


	public void setSpeditionID(String speditionID) {
		this.speditionID = speditionID;
	}
	

}
