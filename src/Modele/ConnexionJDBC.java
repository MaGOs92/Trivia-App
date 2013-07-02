package Modele;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.*;

public class ConnexionJDBC {

	private String url;
	
	private String user;
	
	private String password;
	
	ConnexionJDBC(){

	}
	
	ConnexionJDBC(String url, String user, String password){
		
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	
	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
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
			connection = (Connection)  DriverManager.getConnection("jdbc:mysql://" + this.getUrl(), this.getUser(), this.getPassword());
	 
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
	
	// Fonction pour exécuter une requête
	public static ResultSet exec1Requete (String requete, Connection co, int type){
		ResultSet res = null;
		try {
			Statement st;
			if (type == 0){
				st = (Statement) co.createStatement();
				}
			else {
				st = (Statement) co.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
					       	ResultSet.CONCUR_READ_ONLY);
				}
			res = st.executeQuery(requete);
		}
		catch (SQLException e){
			System.out.println("Problème lors de l'exécution de la requete : "+requete);
		};
		return res;
	}
}
