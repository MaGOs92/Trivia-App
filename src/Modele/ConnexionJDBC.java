package Modele;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.*;

public class ConnexionJDBC {

	private String server;
	
	private String port;
	
	private String database;
	
	private String user;
	
	private String password;
	
	ConnexionJDBC(){

	}
	
	public ConnexionJDBC(String server, String port, String database, String user, String password){
		
		this.server = server;
		this.port = port;
		this.database = database;
		this.user = user;
		this.password = password;
	}
	
	

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	// Ouverture de la connection
	public Connection openConnection() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) {
			System.out.println("Driver JDBC introuvable");
			e.printStackTrace();
		}
	 	
		Connection connection = null;
		
	 
		try {
			connection = (Connection)  DriverManager.getConnection("jdbc:mysql://" + this.getServer() + ":" + this.getPort() + "/" + this.getDatabase(), this.getUser(), this.getPassword());
	 
		} catch (SQLException e) {
			System.out.println("Connection échouée, vérifier les paramètres de connection à la base de donnée.");
			e.printStackTrace();
		}
	 
		if (connection != null) {
			System.out.println("Connection réussie");
		} else {
			System.out.println("Connection échouée");
		}
		
		return connection;
	}

	// Fermeture de la connection
	public void closeConnection(Connection co){
		try {
			co.close();
			System.out.println("Connexion fermée!");
		}
		catch (SQLException e) {
			System.out.println("Impossible de fermer la connexion");
		}	
	}
	
}
