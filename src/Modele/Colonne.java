package Modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Modele.Mapping.Classe;
import Modele.Mapping.MappingINT;
import Modele.Mapping.MappingString;

import com.mysql.jdbc.Connection;

public class Colonne {
	
	private boolean selectionnee;
	
	private int id;
	
	private Connection co;
	
	private String nomTable;
	
	private MappingString mappingString;
	
	private MappingINT mappingINT;
	
	private Classe classe;
	
	private boolean keepOrRemove; // Keep = true, Remove = false
	
	private boolean stringValues;
	
	private int nbLignesTotales;

	private String nomColonne;
	
	private String typeDeDonnee;
	
	private int nbCasesIncorrectes;
	
	private int nbCasesIncorrectesMapping;
	
	private int nbCasesIncorrectesKR;
	
	private int nbCasesRemplies;
	
	private int nbCasesVides;
	
	private float pourcentagesCasesRemplies;
	
	private String[] valeursFrequentes;
	
	private ArrayList<String> valeursListe;
	
	private ArrayList<ValeurRetiree> valeursListeSelectionnees;
	
	Colonne(int id, Connection co, String nomColonne, String nomTable, String typeDeDonnee, int nbCasesRemplies, int nbCasesVides, int nbLignesTotales, String[] valeursFrequentes, ArrayList<String> valeursListe){
		this.setId(id);
		this.setCo(co);
		this.setNomColonne(nomColonne);
		this.setNomTable(nomTable);
		this.setTypeDeDonnee(typeDeDonnee);
		this.setNbLignesTotales(nbLignesTotales);
		this.setNbCasesRemplies(nbCasesRemplies);
		this.setNbCasesVides(nbCasesVides);
		this.setValeursFrequentes(valeursFrequentes);
		this.setValeursListe(valeursListe);
		this.setSelectionnee(false);
		//this.setMapping(new Map(0, "None"));
		this.setPourcentagesCasesRemplies(this.calculerPoucentage());
		this.setKeepOrRemove(false);
		this.setClasse(new Classe(0, "None"));
		if (this.getTypeDeDonnee() == "INT"){
			this.setStringValues(false);
			this.setMappingINT(new MappingINT());
		}
		else{
			this.setStringValues(true);
			this.setMappingString(new MappingString());
		}
		this.setValeursListeSelectionnees(new ArrayList<ValeurRetiree>());
	}
	
	public String toString(){
		String toString = this.getNomColonne();	
		return toString;
	}
	
	public float calculerPoucentage(){
		
		float resultat;
		float total = this.getNbLignesTotales();
		float remplies = this.getNbCasesRemplies();
		float incorrects = this.getNbCasesIncorrectes();
		
		resultat = ((remplies - incorrects)/total) * 100;
		
		return resultat;

	}
	
	public String afficherValeursFrequentes(){
		
		String valeursFrequentes = "";
		
		for (int i = 0; i < 3; i ++){
			valeursFrequentes += this.getValeursFrequentes()[i] + ";";
		}
		
		
		return valeursFrequentes;
	}
	
	public int calculerKeep(){
		

		this.setNbCasesIncorrectesKR(0);
		int nbValeursIncorrectes = 0;
		
		String sql = "select `" + getNomColonne() + "`, count(`" + getNomColonne() + "`) as cnt ";
		
		sql +=	"from " + getNomTable() + " ";
		
		sql += "group by `" + getNomColonne() + "` ";
		
		for (int i = 0; i < this.getValeursListeSelectionnees().size(); i++){
			if (i == 0){
			sql += "HAVING `" + getNomColonne() + "` = '" + getValeursListeSelectionnees().get(i).getValeur() + "'";
			}
			else
				sql += " OR `" + getNomColonne() + "` = '" + getValeursListeSelectionnees().get(i).getValeur() + "'";
		}
		
		sql += " order by cnt desc";
		
		System.out.println(sql);

		
		ResultSet resultat = DataAuditModele.exeRequete(sql, this.getCo(), 0);
		
		this.setValeursListeSelectionnees(new ArrayList<ValeurRetiree>());
					
		try {
			while(resultat.next()){					
				this.getValeursListeSelectionnees().add(new ValeurRetiree(resultat.getString(1), resultat.getInt(2), this.getNbLignesTotales()));
				nbValeursIncorrectes += resultat.getInt(2);	
			}
		} catch (SQLException e) {
					// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		if (nbValeursIncorrectes >= getNbCasesRemplies()){
			nbValeursIncorrectes = getNbCasesRemplies();
		}				
			
		return this.getNbCasesRemplies() - nbValeursIncorrectes;
				
		}
	
	public int calculerRemove(){
		
		this.setNbCasesIncorrectesKR(0);
		int nbValeursIncorrectes = 0;
		
		String sql = "select `" + getNomColonne() + "`, count(`" + getNomColonne() + "`) as cnt ";
		
				sql +=	"from " + getNomTable() + " ";
				
				sql += "group by `" + getNomColonne() + "` ";
				
				for (int i = 0; i < this.getValeursListeSelectionnees().size(); i++){
					if (i == 0){
					sql += "HAVING `" + getNomColonne() + "` = '" + getValeursListeSelectionnees().get(i).getValeur() + "'";
					}
					else
						sql += " OR `" + getNomColonne() + "` = '" + getValeursListeSelectionnees().get(i).getValeur() + "'";
				}
				
				sql += " order by cnt desc";
				
				System.out.println(sql);
		
		ResultSet resultat = DataAuditModele.exeRequete(sql, this.getCo(), 0);
		
		this.setValeursListeSelectionnees(new ArrayList<ValeurRetiree>());
			
		try {
			while(resultat.next()){
				this.getValeursListeSelectionnees().add(new ValeurRetiree(resultat.getString(1), resultat.getInt(2), this.getNbLignesTotales()));
				nbValeursIncorrectes += resultat.getInt(2);				
				}
			} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
				
		if (nbValeursIncorrectes >= getNbCasesRemplies()){
			nbValeursIncorrectes = getNbCasesRemplies();
			}				
			
		return nbValeursIncorrectes;
		
	}
	
	public String[] transformerListeTab(){
		
		String[] tab = new String[this.getValeursListeSelectionnees().size()*3];
		int j = 0;
		for (int i = 0; i < this.getValeursListeSelectionnees().size()*3; i+=3){
			tab[i] = this.getValeursListeSelectionnees().get(j).getValeur();
			tab[i+1] = "" + this.getValeursListeSelectionnees().get(j).getCount();
			tab[i+2] = this.getValeursListeSelectionnees().get(j).getPourcentage();
			j++;
		}
		
		return tab;
	}


	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public int getNbCasesIncorrectesMapping() {
		return nbCasesIncorrectesMapping;
	}

	public void setNbCasesIncorrectesMapping(int nbCasesIncorrectesMapping) {
		this.nbCasesIncorrectesMapping = nbCasesIncorrectesMapping;
	}

	public int getNbCasesIncorrectesKR() {
		return nbCasesIncorrectesKR;
	}

	public void setNbCasesIncorrectesKR(int nbCasesIncorrectesKR) {
		this.nbCasesIncorrectesKR = nbCasesIncorrectesKR;
	}

	public boolean isKeepOrRemove() {
		return keepOrRemove;
	}

	public void setKeepOrRemove(boolean keepOrRemove) {
		this.keepOrRemove = keepOrRemove;
	}

	public ArrayList<String> getValeursListe() {
		return valeursListe;
	}

	public void setValeursListe(ArrayList<String> valeursListe) {
		this.valeursListe = valeursListe;
	}


	public ArrayList<ValeurRetiree> getValeursListeSelectionnees() {
		return valeursListeSelectionnees;
	}

	public void setValeursListeSelectionnees(
			ArrayList<ValeurRetiree> valeursListeSelectionnees) {
		this.valeursListeSelectionnees = valeursListeSelectionnees;
	}

	public String getNomTable() {
		return nomTable;
	}

	public void setNomTable(String nomTable) {
		this.nomTable = nomTable;
	}

	
	public Connection getCo() {
		return co;
	}

	public void setCo(Connection co) {
		this.co = co;
	}

	public int getNbLignesTotales() {
		return nbLignesTotales;
	}

	public void setNbLignesTotales(int nbLignesTotales) {
		this.nbLignesTotales = nbLignesTotales;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MappingString getMappingString() {
		return mappingString;
	}

	public void setMappingString(MappingString mappingString) {
		this.mappingString = mappingString;
	}

	public MappingINT getMappingINT() {
		return mappingINT;
	}

	public void setMappingINT(MappingINT mappingINT) {
		this.mappingINT = mappingINT;
	}

	public int getNbCasesIncorrectes() {
		return nbCasesIncorrectes;
	}

	public void setNbCasesIncorrectes(int nbCasesIncorrectes) {
		this.nbCasesIncorrectes = nbCasesIncorrectes;
	}

	public boolean isSelectionnee() {
		return selectionnee;
	}

	public void setSelectionnee(boolean selectionnee) {
		this.selectionnee = selectionnee;
	}

	public String getNomColonne() {
		return nomColonne;
	}

	public void setNomColonne(String nomColonne) {
		this.nomColonne = nomColonne;
	}

	public String getTypeDeDonnee() {
		return typeDeDonnee;
	}

	public void setTypeDeDonnee(String typeDeDonnee) {
		this.typeDeDonnee = typeDeDonnee;
	}

	public int getNbCasesRemplies() {
		return nbCasesRemplies;
	}

	public void setNbCasesRemplies(int nbCasesRemplies) {
		this.nbCasesRemplies = nbCasesRemplies;
	}

	public int getNbCasesVides() {
		return nbCasesVides;
	}

	public void setNbCasesVides(int nbCasesVides) {
		this.nbCasesVides = nbCasesVides;
	}

	public float getPourcentagesCasesRemplies() {
		return pourcentagesCasesRemplies;
	}

	public void setPourcentagesCasesRemplies(float pourcentagesCasesRemplies) {
		this.pourcentagesCasesRemplies = pourcentagesCasesRemplies;
	}

	public String[] getValeursFrequentes() {
		return valeursFrequentes;
	}

	public void setValeursFrequentes(String[] valeursFrequentes) {
		this.valeursFrequentes = valeursFrequentes;
	}

	public boolean isStringValues() {
		return stringValues;
	}

	public void setStringValues(boolean stringValues) {
		this.stringValues = stringValues;
	}
	
	
	
}
