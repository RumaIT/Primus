package de.rumait.databse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	public static final String DATABASE_NAME = "k85506_primus";
	public static final String IPADRESS = "46.38.249.185:3306/"; // Public IP von Hosting
	public static final String CONNECTION_STRING = "jdbc:mysql://" + IPADRESS + DATABASE_NAME;

	public static final String USERNAME = "k85506_primus";
	public static final String PASSWORD = "sonne1";

	private Connection connection;
	private Statement statement;

	// Methode für die Überprüfung der Verbindung

	public boolean checkConnection() {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			// Verbindung zu Datenbank herstellen

			connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
			statement = connection.createStatement();

			System.out.println("Datenbankverbindung Erfolgreich");

			// Verbindung steht

			return true;

		} catch (Exception e) {

			// Fehlgeschalgen

			return false;

		}

	}

	// Methode zur Überprüfung der Benutzerdaten in der Datenbank

	public boolean userLogin(String username, String password) throws Exception {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String sql = "Select * from centrallogin where username =? and password =?";

		preparedStatement = connection.prepareStatement(sql);

		preparedStatement.setString(1, username);
		preparedStatement.setString(2, password);

		resultSet = preparedStatement.executeQuery();

		// Wenn der Cursor zum eintrag fährt (es gibt einen mit diesem username und
		// passwort) dann return true
		if (resultSet.next()) {
			return true;
		} else {
			return false;
		}
	}
	
	public void closeConnection() throws SQLException {
		connection.close();
		System.out.println("Connection closed");
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

}
