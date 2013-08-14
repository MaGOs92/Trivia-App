package Modele;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Modele.Mapping.Classe;
import Modele.Mapping.MappingINT;
import Modele.Mapping.MappingString;
import au.com.bytecode.opencsv.CSVWriter;

import com.mysql.jdbc.Connection;


public class DataAuditModele {
	
	private Connection connexion;
	
	private String nomTable;
	
	private ResultSetMetaData metadata;
	
	private int nbLignesTotales;
	
	private int nbColonnesTotales;
	
	private Colonne[] tabColonne;
	
	private String nomClient;
	
	private MappingString[] tabMappingString;
	
	private MappingINT[] tabMappingINT;
	
	private Classe[] tabClasse;
 	
	
	
	public Classe[] getTabClasse() {
		return tabClasse;
	}

	public void setTabClasse(Classe[] tabClasse) {
		this.tabClasse = tabClasse;
	}

	public MappingString[] getTabMappingString() {
		return tabMappingString;
	}

	public void setTabMappingString(MappingString[] tabMappingString) {
		this.tabMappingString = tabMappingString;
	}

	public MappingINT[] getTabMappingINT() {
		return tabMappingINT;
	}

	public void setTabMappingINT(MappingINT[] tabMappingINT) {
		this.tabMappingINT = tabMappingINT;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

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

	public DataAuditModele(Connection co, String nomTable) throws SQLException{
		
		this.setConnexion(co);
		
		this.setNomTable(nomTable);
		
		String sql  = "SELECT * FROM " + nomTable;
	
		ResultSet resultat = exeRequete(sql, co, 1);
		
		this.setMetadata(resultat.getMetaData());
		
		this.setNbColonnesTotales(getMetadata().getColumnCount());
		
		resultat.last();
		
		this.setNbLignesTotales(resultat.getRow());
		
		this.setTabColonne(this.remplissageColonne());
		
		this.setTabClasse(this.tabClasse());
		
		this.setTabMappingString(this.tabMappingString());
		
		this.setTabMappingINT(this.tabMappingINT());

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
		
		String sqlVarchar = "SELECT COUNT(`" + this.getMetadata().getColumnName(index) + "`) FROM " + getNomTable() + " WHERE `" + this.getMetadata().getColumnName(index) + "` = ?";
		
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
		
		 String sql = "select `" + this.getMetadata().getColumnName(index) + "`, count(`" + this.getMetadata().getColumnName(index) + "`) as cnt ";
				sql +=	"from " + getNomTable() + " ";
				sql +=	"group by `" + this.getMetadata().getColumnName(index) + "` ";
				sql += "having count(`" + this.getMetadata().getColumnName(index) + "`) > 1 ";
				sql +=	"order by cnt desc";
		
		ResultSet resultat = exeRequete(sql, this.getConnexion(), 0);
		
		String [] valeursFrequentes = new String [9];
		
		int i = 0;

		if (this.getMetadata().getColumnTypeName(index) == "VARCHAR"){
			
			while(resultat.next() && i < 9){
				
				if (resultat.getString(1) != ""){
										
					valeursFrequentes[i] = resultat.getString(1);
					i++;
					valeursFrequentes[i] = "" + resultat.getInt(2);
					i++;
					valeursFrequentes[i] = "" + Math.round(((float) resultat.getInt(2)/(float) this.getNbLignesTotales())*100) + " %";
					i++;
				}
						
			}
		}
		
		else{
			
			while(resultat.next() && i < 9){
				
				valeursFrequentes[i] = "" + resultat.getInt(1);
				i++;
				valeursFrequentes[i] = "" + resultat.getInt(2);
				i++;
				valeursFrequentes[i] = "" + Math.round(((float) resultat.getInt(2)/(float) this.getNbLignesTotales())*100) + " %";
				i++;
			}
		}
		
		return valeursFrequentes;
				
	}
	
	// Tableau pour remplir la liste de valeurs fréquentes
	public ArrayList<String> valeursListe(int index) throws SQLException{
		
		 String sql = "select `" + this.getMetadata().getColumnName(index) + "`, count(`" + this.getMetadata().getColumnName(index) + "`) as cnt ";
			sql +=	"from " + getNomTable() + " ";
			sql +=	"group by `" + this.getMetadata().getColumnName(index) + "` ";
			//sql += "having count(`" + this.getMetadata().getColumnName(index) + "`) > 1 ";
			sql +=	"order by cnt desc";
	
	ResultSet resultat = exeRequete(sql, this.getConnexion(), 0);
	
	ArrayList<String> valeursListe = new ArrayList<String>();
	
	
	if (this.getMetadata().getColumnTypeName(index) == "VARCHAR")
	{
		while(resultat.next()){
			if (resultat.getString(1) != ""){
				valeursListe.add(resultat.getString(1));
			}
		}
	}
	else
	{
		while(resultat.next()){
			
			valeursListe.add("" + resultat.getInt(1));
		}
			
	}
	
	return valeursListe;
	}		
	
	public Colonne[] remplissageColonne() throws SQLException{
		
		Colonne [] tabColonne = new Colonne [getNbColonnesTotales()];
		
		for (int i = 0; i < this.getNbColonnesTotales() ; i++)
		{ 			
			tabColonne [i] = new Colonne(i + 1,
										this.getConnexion(),										
										this.getMetadata().getColumnName(i+1),
										this.getNomTable(),
										this.getMetadata().getColumnTypeName(i+1), 
										this.getNbLignesTotales() - this.nbLignesVides(i+1),
										this.nbLignesVides(i+1),
										this.getNbLignesTotales(),
										this.valeursFrequentes(i+1),
										this.valeursListe(i+1));
			
		}
		
		return tabColonne;
	
	}
	
	public int getNbLignesSelectionnee(){
		int j = 0;
		for (int i = 0; i < this.getNbColonnesTotales(); i ++){
			if (this.getTabColonne()[i].isSelectionnee())
				j++;
		}
		return j;
	}
	
	public Classe[] tabClasse(){
		
		String[] nomClasse = {"None", "Identifier", "Indicator", "Quantity", "Date", "Text", "Code"
		};
		
		Classe[] tabClasse = new Classe[nomClasse.length];
		
		for (int i = 0; i < nomClasse.length; i ++){
			tabClasse[i] = new Classe(i, nomClasse[i]);
		}

		return tabClasse;
	}
	
	public MappingString[] tabMappingString(){
		
		MappingString[] tab = new MappingString[30];
		
		tab[0] = new MappingString(0, "None", this.getTabClasse()[0], this.getNomTable(), this.getConnexion());
		tab[1] = new MappingString(1, "ID", this.getTabClasse()[1], this.getNomTable(), this.getConnexion());
		tab[2] = new MappingString(2, "Country", this.getTabClasse()[2], this.getNomTable(), this.getConnexion());
		tab[3] = new MappingString(3, "Company name", this.getTabClasse()[5], this.getNomTable(), this.getConnexion());
		tab[4] = new MappingString(4, "Physical address L1", this.getTabClasse()[5], this.getNomTable(), this.getConnexion());
		tab[5] = new MappingString(5, "Physical address L2", this.getTabClasse()[5], this.getNomTable(), this.getConnexion());
		tab[6] = new MappingString(6, "Physical address L3", this.getTabClasse()[5], this.getNomTable(), this.getConnexion());
		tab[7] = new MappingString(7, "Physical city", this.getTabClasse()[5], this.getNomTable(), this.getConnexion());
		tab[8] = new MappingString(8, "Physical state", this.getTabClasse()[2], this.getNomTable(), this.getConnexion());
		tab[9] = new MappingString(9, "Zip postal code", this.getTabClasse()[6], this.getNomTable(), this.getConnexion());
		tab[10] = new MappingString(10, "Phone number", this.getTabClasse()[6], this.getNomTable(), this.getConnexion());
		tab[11] = new MappingString(11, "Employee at site", this.getTabClasse()[3], this.getNomTable(), this.getConnexion());
		tab[12] = new MappingString(12, "Employee total", this.getTabClasse()[3], this.getNomTable(), this.getConnexion());
		tab[13] = new MappingString(13, "Annual sales", this.getTabClasse()[3], this.getNomTable(), this.getConnexion());
		tab[14] = new MappingString(14, "Website", this.getTabClasse()[5], this.getNomTable(), this.getConnexion());
		tab[15] = new MappingString(15, "Industry code (NAF/APE)", this.getTabClasse()[6], this.getNomTable(), this.getConnexion());
		tab[16] = new MappingString(16, "Industry code (NACE)", this.getTabClasse()[6], this.getNomTable(), this.getConnexion());
		tab[17] = new MappingString(17, "Descr. Industry code", this.getTabClasse()[5], this.getNomTable(), this.getConnexion());
		tab[18] = new MappingString(18, "ID contact", this.getTabClasse()[1], this.getNomTable(), this.getConnexion());
		tab[19] = new MappingString(19, "Title", this.getTabClasse()[2], this.getNomTable(), this.getConnexion());
		tab[20] = new MappingString(20, "Contact first name", this.getTabClasse()[5], this.getNomTable(), this.getConnexion());
		tab[21] = new MappingString(21, "Contact last name", this.getTabClasse()[5], this.getNomTable(), this.getConnexion());
		tab[22] = new MappingString(22, "Contact email", this.getTabClasse()[5], this.getNomTable(), this.getConnexion());
		tab[23] = new MappingString(23, "Internal marketability indicator", this.getTabClasse()[2], this.getNomTable(), this.getConnexion());
		tab[24] = new MappingString(24, "Opt/Out flag", this.getTabClasse()[2], this.getNomTable(), this.getConnexion());
		tab[25] = new MappingString(25, "Text", this.getTabClasse()[5], this.getNomTable(), this.getConnexion());
		tab[26] = new MappingString(26, "Code", this.getTabClasse()[6], this.getNomTable(), this.getConnexion());
		tab[27] = new MappingString(27, "Date", this.getTabClasse()[4], this.getNomTable(), this.getConnexion());
		tab[28] = new MappingString(28, "Indicator", this.getTabClasse()[2], this.getNomTable(), this.getConnexion());
		tab[29] = new MappingString(29, "Quantity", this.getTabClasse()[3], this.getNomTable(), this.getConnexion());
		
		return tab;
	}
	
	public MappingINT[] tabMappingINT(){
		
		MappingINT[] tab = new MappingINT[13];
		
		tab[0] = new MappingINT(0, "None", this.getTabClasse()[0], this.getNomTable(), this.getConnexion());
		tab[1] = new MappingINT(1, "ID", this.getTabClasse()[1], this.getNomTable(), this.getConnexion());
		tab[2] = new MappingINT(2, "Zip postal code", this.getTabClasse()[6], this.getNomTable(), this.getConnexion());
		tab[3] = new MappingINT(3, "Phone number", this.getTabClasse()[6], this.getNomTable(), this.getConnexion());
		tab[4] = new MappingINT(4, "Industry code (Sic4)", this.getTabClasse()[6], this.getNomTable(), this.getConnexion());
		tab[5] = new MappingINT(5, "Industry code (NAICS)", this.getTabClasse()[6], this.getNomTable(), this.getConnexion());
		tab[6] = new MappingINT(6, "Employee at site", this.getTabClasse()[3], this.getNomTable(), this.getConnexion());
		tab[7] = new MappingINT(7, "Employee total", this.getTabClasse()[3], this.getNomTable(), this.getConnexion());
		tab[8] = new MappingINT(8, "Annual sales", this.getTabClasse()[3], this.getNomTable(), this.getConnexion());
		tab[9] = new MappingINT(9, "ID contact", this.getTabClasse()[1], this.getNomTable(), this.getConnexion());
		tab[10] = new MappingINT(10, "Quantity", this.getTabClasse()[3], this.getNomTable(), this.getConnexion());
		tab[11] = new MappingINT(11, "Code", this.getTabClasse()[6], this.getNomTable(), this.getConnexion());
		tab[12] = new MappingINT(12, "Indicator", this.getTabClasse()[2], this.getNomTable(), this.getConnexion());

		return tab;
		
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
	
	public String nomDuFichierPDF(String chemin){
		
		String pref = "DataAudit_";
		
		chemin = chemin.substring(0, chemin.lastIndexOf("\\") + 1);
		
		chemin = chemin + pref + this.getNomTable() + ".pdf";
		
		return chemin;
	}
	
}
