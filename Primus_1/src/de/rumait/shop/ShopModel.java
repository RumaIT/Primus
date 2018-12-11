package de.rumait.shop;

import java.sql.Statement;

public class ShopModel {
	
	public void createShopMember(Statement statement, String ort, String plz, String benutzername, String passwort, String shopName, String strasse, String hausnummer){
		
		try {
		
		String sqlOrt = "INSERT IGNORE INTO Orte VALUES (null,'"+plz+"','"+ort+"')";

		String sqluser ="INSERT INTO Shop VALUES (null,"+"(SELECT idOrte FROM Orte WHERE PLZ='"+plz+"'),'"+benutzername+"','"+passwort+"','"+shopName+"','"+strasse+" "+hausnummer+"')";
		
			System.out.println("Creating...");
			
			statement.executeUpdate(sqlOrt);
			
			System.out.println("Ort created!");
			
			System.out.println("Creating....");
			
			statement.executeUpdate(sqluser);
			System.out.println("Shop user created!");
			
			
		}catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}
	
	
	public void shopMemberLoeschen(Statement statement, String id) {
		String sql1 = "SELECT "
		String sql ="DELETE FROM Shop WHERE idShop='"+id+"')";
		
		
		System.out.println("Fehler1");
		try {
			
			statement.execute(sql);
			
			System.out.println("Fehler2");

		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		
		
		
	}
	
	
	
	
	
}
