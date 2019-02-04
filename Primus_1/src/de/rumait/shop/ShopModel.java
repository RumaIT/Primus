package de.rumait.shop;

import java.sql.Statement;

public class ShopModel {

	// Attribute für ShopAnzeige Konstruktor
	private String idShop, shopName, strasse, benutzername;

	// Standart Konstruktor
	public ShopModel() {

	}

	// ------Konstruktor für die ShopAnzeige im TableView (ObservableList)
	public ShopModel(String idShop, String shopName, String strasse, String benutzername) {
		this.idShop = idShop;
		this.shopName = shopName;
		this.strasse = strasse;
		this.benutzername = benutzername;
	}

	public void createShopMember(Statement statement, String ort, String plz, String benutzername, String passwort,
			String shopName, String strasse, String hausnummer) {

		try {

			String sqlOrt = "INSERT IGNORE INTO Orte VALUES (null,'" + plz + "','" + ort + "')";

			String sqluser = "INSERT INTO Shop VALUES (null," + "(SELECT idOrte FROM Orte WHERE PLZ='" + plz + "'),'"
					+ benutzername + "','" + passwort + "','" + shopName + "','" + strasse + " " + hausnummer + "')";

			System.out.println("Creating...");

			statement.executeUpdate(sqlOrt);

			System.out.println("Ort created!");

			System.out.println("Creating....");

			statement.executeUpdate(sqluser);
			System.out.println("Shop user created!");

		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	public void shopMemberLoeschen(Statement statement, String id) {

		// String sql1 ="SELECT * FROM Shop";

		String sql = "DELETE FROM Shop WHERE idShop='" + id + "'";

		try {

			// statement.execute(sql1);
			statement.execute(sql);

			System.out.println("User deleted!");

		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	public String getIdShop() {
		return idShop;
	}

	public void setIdShop(String idShop) {
		this.idShop = idShop;
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

	public String getBenutzername() {
		return benutzername;
	}

	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}

}
