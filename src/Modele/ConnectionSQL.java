package Modele;
import java.sql.*;

public class ConnectionSQL {
	
	public static Connection openConnection (String url) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) {
			System.out.println("Driver JDBC introuvable");
			e.printStackTrace();
		}
	 	
		Connection connection = null;
	 
		try {
			connection = DriverManager.getConnection("jdbc:mysql://" + url);
	 
		} catch (SQLException e) {
			System.out.println("Connection �chou�e, v�rifier les param�tres de connection � la base de donn�e.");
			e.printStackTrace();
		}
	 
		if (connection != null) {
			System.out.println("Connection r�ussie");
		} else {
			System.out.println("Connection �chou�e");
		}
		
		return connection;
	}

	public static ResultSet exec1Requete (String requete, Connection co, int type){
		ResultSet res=null;
		try {
			Statement st;
			if (type==0){
				st=co.createStatement();}
			else {
				st=co.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
					       	ResultSet.CONCUR_READ_ONLY);
				};
			res= st.executeQuery(requete);
		}
		catch (SQLException e){
			System.out.println("Probl�me lors de l'ex�cution de la requete : "+requete);
		};
		return res;
	}

	public static void closeConnection(Connection co){
		try {
			co.close();
			System.out.println("Connexion ferm�e!");
		}
		catch (SQLException e) {
			System.out.println("Impossible de fermer la connexion");
		}	
	}
	
}
