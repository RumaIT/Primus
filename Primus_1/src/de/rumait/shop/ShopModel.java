package de.rumait.shop;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.rumait.databse.Database;
import de.rumait.mainLogin.LoginController;

public class ShopModel {

	// Attribute für ShopAnzeige Konstruktor
	private String stationID, shopName, strasse, benutzerName, passwort, plz, ort;

	// Standart Konstruktor
	public ShopModel() {

	}

	// ------Konstruktor für die ShopAnzeige im TableView (ObservableList)
	public ShopModel(String stationID, String shopName, String benutzerName, String passwort, String strasse,
			String plz, String ort) {
		this.stationID = stationID;
		this.shopName = shopName;
		this.benutzerName = benutzerName;
		this.passwort = passwort;
		this.strasse = strasse;
		this.plz = plz;
		this.ort = ort;

	}

	public boolean createShopMember(Statement statement, String ort, String plz, String benutzername, String passwort,
			String shopName, String strasse) throws SQLException {

		try {

			String sqlOrt = "INSERT IGNORE INTO Orte VALUES (null,'" + plz + "','" + ort + "')";
			String sqluser = "INSERT INTO Shop VALUES (null," + "(SELECT idOrte FROM Orte WHERE PLZ='" + plz + "'),'"
					+ benutzername + "','" + passwort + "','" + shopName + "','" + strasse + "')";

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

	public void shopMemberLoeschen(Statement statement, String id) throws SQLException {

		String sql = "DELETE FROM Shop WHERE idShop='" + id + "'";
		try {

			statement.execute(sql);
			System.out.println("User deleted!");

		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	public ResultSet getAllStations(Statement statement) throws SQLException {

		ResultSet resultset = null;
		String sql = "SELECT Shop.idShop, Shop.ShopName, Shop.Benutzername, Shop.Passwort, Shop.Strasse, Orte.PLZ, Orte.Ort From Shop "
				+ "Inner join Orte on Shop.Orte_idOrte = Orte.idOrte";
		System.out.println("Stationen abfragen...");
		try {
			resultset = statement.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("Fehler beim abfragen der Stationen");
			e.printStackTrace();
		}
		System.out.println("Stationenabfrage erfolgreich");
		return resultset;

	}

	public ResultSet getStationsFromSearch(Statement statement, String stationID, String stationName) throws SQLException {
		ResultSet resultSet = null;

		String sql = "SELECT Shop.idShop, Shop.ShopName, Shop.Benutzername, Shop.Passwort, Shop.Strasse, Orte.PLZ, Orte.Ort FROM Shop "
				+ "INNER JOIN Orte ON Shop.Orte_idOrte = Orte.idOrte " + "WHERE idShop='" + stationID
				+ "' OR ShopName='" + stationName + "'";
		
			resultSet = statement.executeQuery(sql);
		return resultSet;
	}

	public boolean changeStationInfo(Statement statement, String idShop, String stationName, String benutzerName,
			String passwort, String strasse, String plz, String ort) throws SQLException {
		String sqlOrt = "INSERT IGNORE INTO Orte VALUES (null, '" + plz + "','" + ort + "')";
		String sql = "UPDATE Shop " + "SET Shop.ShopName ='" + stationName + "' , Shop.Benutzername ='" + benutzerName
				+ "', Shop.Passwort='" + passwort + "', Shop.Strasse='" + strasse
				+ "', Shop.Orte_idOrte=(SELECT Orte.idOrte FROM Orte WHERE PLZ='" + plz + "') " + "WHERE Shop.idShop='"
				+ idShop + "'";

		System.out.println("Daten werden geändert....");

		statement.execute(sqlOrt);

		statement.execute(sql);
		System.out.println("Daten Änderung erfolgreich!");
		return true;
	}

	public boolean deleteStationEntery(Database databaseObject, String passwort, String stationID) throws Exception {

		if (databaseObject.userLogin(LoginController.username, passwort)) {
			if (databaseObject.checkConnection()) {
				Statement statement = databaseObject.getStatement();
				String sql = "DELETE FROM Shop WHERE idShop='" + stationID + "'";
				statement.execute(sql);
				System.out.println("Löschen refolgreich");
				return true;
			}
		}
		System.out.println("Admin Passwort falsch");
		return false;
	}

	public String getStationID() {
		return stationID;
	}

	public void setStationID(String stationID) {
		this.stationID = stationID;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getBenutzerName() {
		return benutzerName;
	}

	public void setBenutzerName(String benutzerName) {
		this.benutzerName = benutzerName;
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

}
