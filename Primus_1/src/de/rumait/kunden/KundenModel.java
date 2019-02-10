package de.rumait.kunden;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class KundenModel {
	
	private String kundenID, name, nachname, strasse, plz, ort, stationsID, stationsName;

	public KundenModel(String kundenID, String name, String nachname, String strasse, String plz, String ort,
			String stationsID, String stationsName) {
		super();
		this.kundenID = kundenID;
		this.name = name;
		this.nachname = nachname;
		this.strasse = strasse;
		this.plz = plz;
		this.ort = ort;
		this.stationsID = stationsID;
		this.stationsName = stationsName;
	}
	
	public KundenModel() {
		
	}
	
	
	public ResultSet getAllData(Statement statement) throws SQLException {
		ResultSet abfrage = null;
		String sql= "SELECT Kunde.idKunde, Kunde.Name, Kunde.Nachname, Kunde.Strasse, Orte.PLZ, Orte.Ort, Kunde.Kunde_idShop, Shop.ShopName FROM Kunde " + 
					"INNER JOIN Orte ON Kunde.Orte_idOrte = Orte.idOrte " + 
					"INNER JOIN Shop ON Kunde.Kunde_idShop = Shop.idShop";
		
		abfrage = statement.executeQuery(sql);
		return abfrage;		
	}
	
	public ResultSet sucheKunden(Statement statement, String kundenID, String nachname, String strasse, String plz, String stationsName, String stationsID) throws SQLException {
		ResultSet abfrage = null;
		String sql ="SELECT Kunde.idKunde, Kunde.Name, Kunde.Nachname, Kunde.Strasse, Orte.PLZ, Orte.Ort, Kunde.Kunde_idShop, Shop.ShopName FROM Kunde "+
					"INNER JOIN Orte ON Kunde.Orte_idOrte = Orte.idOrte "+
					"INNER JOIN Shop ON Kunde.Kunde_idShop = Shop.idShop " + 
					"WHERE Kunde.idKunde='"+kundenID+"' OR Kunde.Nachname='"+nachname+"' OR Kunde.Strasse='"+strasse+"' OR Orte.PLZ='"+plz+"' OR Shop.ShopName='"+stationsName+"' OR Shop.idShop='"+stationsID+"'";
		abfrage = statement.executeQuery(sql);
		return abfrage;
	}
	
	public boolean datenAendern(Statement statement,String kundenID,  String name, String nachname, String strasse, String plz, String ort, String stationsID ) throws SQLException {
		
		String sql1=	"INSERT IGNORE INTO Orte VALUES(null,'"+plz+"','"+ort+"')";
		String sql2=	"UPDATE Kunde AS a "+
						"INNER JOIN Orte AS b "+
						"ON a.Orte_idOrte = b.idOrte "+ 
						"SET a.Name='"+name+"', a.Nachname='"+nachname+"', a.Strasse='"+strasse+"', a.Orte_idOrte=(SELECT Orte.idOrte FROM Orte WHERE PLZ='"+plz+"') , a.Kunde_idShop='"+stationsID+"'"+
						"WHERE a.idKunde='"+kundenID+"'";
		
		statement.execute(sql1);
		statement.execute(sql2);
		
		return true;
	}
	
	public boolean kundenLoeschen(Statement statement,String kundenID) throws SQLException {
		
		String sql ="DELETE FROM Kunde WHERE idKunde='"+kundenID+"'";
		
		if(statement.execute(sql)) {
			System.out.println("Kunde löschen erfolgreich");
			return true;
		}else {
			System.out.println("Kunde hat eine Paket Historie und kann nicht entfernt werden.");
			return false;
		}
	}
	
	

	public String getKundenID() {
		return kundenID;
	}

	public void setKundenID(String kundenID) {
		this.kundenID = kundenID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
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

	public String getStationsID() {
		return stationsID;
	}

	public void setStationsID(String stationsID) {
		this.stationsID = stationsID;
	}

	public String getStationsName() {
		return stationsName;
	}

	public void setStationsName(String stationsName) {
		this.stationsName = stationsName;
	}
	

}
