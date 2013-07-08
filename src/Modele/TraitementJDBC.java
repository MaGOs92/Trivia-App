package Modele;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import au.com.bytecode.opencsv.CSVWriter;

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
	
	// Fonction qui renvoit le nombre de lignes vides pour le champ passé en paramètre
	public int nbLignesVides(int index) throws SQLException{
		
		String sqlVarchar = "SELECT COUNT(" + this.getMetadata().getColumnName(index) + ") FROM " + getNomTable() + " WHERE " + this.getMetadata().getColumnName(index) + " = ?";
		
		PreparedStatement preparedStatement = getConnexion().prepareStatement(sqlVarchar);
		
		preparedStatement.setString(1, "");
		
		ResultSet monResultat = preparedStatement.executeQuery();
		int nb = 0;
		
		while(monResultat.next()){
			nb = monResultat.getInt(1);
		}
		
		return nb;
		
	}
	
	// Fonction qui renvoit les 3 valeurs les plus fréquentes pour le champ passé en paramètre
	public String[] valeursFrequentes(int index) throws SQLException{
		
		 String sql = "select " + this.getMetadata().getColumnName(index) + ", count(" + this.getMetadata().getColumnName(index) + ") as cnt ";
				sql +=	"from " + getNomTable() + " ";
				sql +=	"group by " + this.getMetadata().getColumnName(index) + " ";
				sql += "having count(" + this.getMetadata().getColumnName(index) + ") > 1 ";
				sql +=	"order by cnt desc";
		
		ResultSet resultat = exeRequete(sql, this.getConnexion(), 0);
		
		String [] valeursFrequentes = new String [3];
		
		int i = 0;
		
		if (this.getMetadata().getColumnTypeName(index) == "VARCHAR"){
			
			while(resultat.next() && i < 3){
				
				valeursFrequentes[i] = "Value : " + resultat.getString(1).replaceAll("\\W", "") + "\t number of entries : " + resultat.getInt(2);
				i++;
				
			}
		}
		
		else{
			
			while(resultat.next() && i < 3){
				valeursFrequentes[i] = "Value : " + resultat.getInt(1) + "\t number of entries : " + resultat.getInt(2);
				i++;
			}
			
		}
		
		return valeursFrequentes;
				
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
										this.getMetadata().getColumnTypeName(i+1), 
										this.getNbLignesTotales() - this.nbLignesVides(i+1),
										this.nbLignesVides(i+1),
										this.getNbLignesTotales(),
										this.valeursFrequentes(i+1));
			
		}
		
		return tabColonne;
	
	}
	
	public String[] dataAudit(){
		
		String [] dataAudit = new String [this.getTabColonne().length + 2];
		
		dataAudit[0] = "Data Audit Summar " + this.getNomTable() + ", Total number of Entries : " + this.getNbLignesTotales();
		dataAudit[1] = "Variable ; Storage ; Number of filled entries ; number of empty entries ; Total filled entries ; Frequent values";
		for (int i = 2; i < this.getTabColonne().length + 2; i++){
			dataAudit[i] = getTabColonne()[i-2].getNomColonne() + ";" + getTabColonne()[i-2].getTypeDeDonnee() + ";" + getTabColonne()[i-2].getNbCasesRemplies()
					+ ";" + getTabColonne()[i-2].getNbCasesVides() + ";" + getTabColonne()[i-2].getPourcentagesCasesRemplies() + ";" + getTabColonne()[i-2].afficherValeursFrequentes();
		}
		
		return dataAudit;
	}
	
	public void genererFichierCSV(String[] dataAudit, String chemin) throws IOException{
		
		String pref = "DataAudit_";
		
		chemin = chemin.substring(0, chemin.lastIndexOf("\\") + 1);
		
		chemin = chemin + pref + this.getNomTable() + ".csv";
		
		CSVWriter writer = new CSVWriter(new FileWriter(chemin));
	     // feed in your array (or convert your data to an array)
		for (int i = 0; i < this.getTabColonne().length + 2; i++){
	     String[] entries = dataAudit[i].split(";");
	     writer.writeNext(entries);
		}
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("Fichier de data audit généré : " + chemin);
		
	}
	
}
