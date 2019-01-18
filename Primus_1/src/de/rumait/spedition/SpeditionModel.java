package de.rumait.spedition;

import java.sql.Statement;

public class SpeditionModel {
	
	
	
	
	
	
	
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


	
	

}
