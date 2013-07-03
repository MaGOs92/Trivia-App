package Modele;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;


public class TraitementJDBC {
	
	private Connection connexion;
	
	private String nomTable;
	
	private ResultSetMetaData metadata;
	
	private int nbLignesTotales;
	
	private int nbColonnesTotales;
	
	private Colonne[] tabColonne;
	
	
	public Colonne[] getTabColonne() {
		return tabColonne;
	}

	public void setTabColonne(Colonne[] tabColonne) {
		this.tabColonne = tabColonne;
	}

	public Connection getConnexion() {
		return connexion;
	}

	public void setConnexion(Connection connexion) {
		this.connexion = connexion;
	}

	public ResultSetMetaData getMetadata() {
		return metadata;
	}

	public void setMetadata(ResultSetMetaData metadata) {
		this.metadata = metadata;
	}

	public String getNomTable() {
		return nomTable;
	}

	public void setNomTable(String nomTable) {
		this.nomTable = nomTable;
	}

	public int getNbLignesTotales() {
		return nbLignesTotales;
	}

	public void setNbLignesTotales(int nbLignesTotales) {
		this.nbLignesTotales = nbLignesTotales;
	}

	public int getNbColonnesTotales() {
		return nbColonnesTotales;
	}

	public void setNbColonnesTotales(int nbColonnesTotales) {
		this.nbColonnesTotales = nbColonnesTotales;
	}

	TraitementJDBC(Connection co, String nomTable) throws SQLException{
		
		this.setConnexion(co);
		
		this.setNomTable(nomTable);
		
		String sql  = "SELECT * FROM " + nomTable;
	
		ResultSet resultat = exeRequete(sql, co, 1);
		
		this.setMetadata(resultat.getMetaData());
		
		this.setNbColonnesTotales(getMetadata().getColumnCount());
		
		resultat.last();
		
		this.setNbLignesTotales(resultat.getRow());
		
		this.setTabColonne(this.remplissageColonne());

	}
	
	// Fonction pour exécuter une requête
	public static ResultSet exeRequete(String requete, Connection co, int type){
		ResultSet res = null;
		try {
			Statement st;
			if (type == 0)
			{
				st =  co.createStatement();
			}
			else 
			{
				st = co.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			}
			res = st.executeQuery(requete);
		}
		catch (SQLException e){
			System.out.println("Problème lors de l'exécution de la requete : " + requete);
		}
		return res;
	}
	
	
	public int nbLignesVides(String colonne) throws SQLException{
		
		String sqlVarchar = "SELECT COUNT(" + colonne + ") FROM " + getNomTable() + " WHERE " + colonne + " = ?";
		
		PreparedStatement preparedStatement = getConnexion().prepareStatement(sqlVarchar);
		
		preparedStatement.setString(1, "");
		
		ResultSet monResultat = preparedStatement.executeQuery();
		int nb = 0;
		
		while(monResultat.next()){
			nb = monResultat.getInt(1);
		}
		
		return nb;
		
	}
	/*
	public int nbDoublons(){
	
	}
	*/
	public Colonne[] remplissageColonne() throws SQLException{
		
		Colonne [] tabColonne = new Colonne [getNbColonnesTotales()];
		
		for (int i = 0; i < this.getNbColonnesTotales() ; i++)
		{ 			
			tabColonne [i] = new Colonne(this.getMetadata().getColumnName(i+1), 
										this.getMetadata().getColumnClassName(i+1), 
										this.getNbLignesTotales() - this.nbLignesVides(this.getMetadata().getColumnName(i+1)),
										this.nbLignesVides(this.getMetadata().getColumnName(i+1)),
										this.getNbLignesTotales());
			
		}
		
		return tabColonne;
	
	}
	
	public void dataAudit(){
		
		System.out.println("Data Audit Summar " + this.getNomTable() + ", Total number of Entries : " + this.getNbLignesTotales());
		for (int i = 0; i < this.getTabColonne().length; i++){
			System.out.println(getTabColonne()[i]);
		}
	}
	
}
