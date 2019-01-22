package de.rumait.spedition;

import java.sql.Statement;


public class SpeditionModel {
	
	private String spediID, spediName, spediUserName, spediStrasse;
	
	
	public SpeditionModel(String spediID, String spediName, String spediUserName, String spediStrasse) {
		
		this.spediID = spediID;
		this.spediName = spediName;
		this.spediUserName = spediUserName;
		this.spediStrasse = spediStrasse;
		
	}
	
	public SpeditionModel() {
		
	}
	

	
public void createSpeditionsMember(Statement statement, String ort, String plz, String benutzername, String passwort, String speditionName, String strasse, String hausnummer){
		
		try {
		
		String sqlOrt = "INSERT IGNORE INTO Orte VALUES (null,'"+plz+"','"+ort+"')";

		String sqluser ="INSERT INTO Spedition VALUES (null,"+"(SELECT idOrte FROM Orte WHERE PLZ='"+plz+"'),'"+benutzername+"','"+passwort+"','"+speditionName+"','"+strasse+" "+hausnummer+"')";
		
			System.out.println("Creating...");
			
			statement.executeUpdate(sqlOrt);
			
			System.out.println("Ort created!");
			
			System.out.println("Creating....");
			
			statement.executeUpdate(sqluser);
			System.out.println("Spedition created!");
			
			
		}catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}


public void speditionMemberLoeschen(Statement statement, String id) {
	
	//String sql1 ="SELECT * FROM Shop";
	
	String sql ="DELETE FROM Spedition WHERE idSpedition='"+id+"'";
	
	
	
	try {
		
	//	statement.execute(sql1);
		statement.execute(sql);
		
		System.out.println("User deleted!");

	} catch (Exception e) {
		System.out.println(e.getStackTrace());
	}	
}

public void getDataFromDatabase() {
	
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

public String getSpediUserName() {
	return spediUserName;
}

public void setSpediUserName(String spediUserName) {
	this.spediUserName = spediUserName;
}

public String getSpediStrasse() {
	return spediStrasse;
}

public void setSpediStrasse(String spediStrasse) {
	this.spediStrasse = spediStrasse;
}


	
	

}
