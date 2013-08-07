package Modele;

import java.sql.ResultSet;
import java.sql.SQLException;


import com.mysql.jdbc.Connection;

public class Mapping {
	
	private int id;

	private String nom;
	
	private Colonne colonne;
	
	private String nomTable;
	
	private Connection co;
	
	public Mapping(){
		
	}
	
	public Colonne getColonne() {
		return colonne;
	}



	public void setColonne(Colonne colonne) {
		this.colonne = colonne;
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


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}

	
	@Override
	public String toString() {
		return getNom();
	}


	Mapping(int id, String nom){
		this.setId(id);
		this.setNom(nom);
	}
	
	// Cette fonction renvoit le nombre de valeur incorrects pour chaque type de mapping
	
	public int CalculerValeursIncorrects(Colonne colonne, String nomTable, Connection co){
		
		this.setColonne(colonne);
		this.setNomTable(nomTable);
		this.setCo(co);
		
		
		int nbValeursIncorrectes = 0;
		
		if (this.getId() == 0){
			this.getColonne().setClasse("None");
			return nbValeursIncorrectes;
		}
		// L' id doit être unique
		else if (this.getId() == 1){
			
			this.getColonne().setClasse("Identifier");
		
		 String sql = "select count(" + colonne.getNomColonne() + ") as cnt ";
			sql +=	"from " + nomTable + " ";
			sql +=	"group by " + colonne.getNomColonne() + " ";
			sql += "having count(" + colonne.getNomColonne() + ") > 1 ";
			sql +=	"order by cnt desc";
	
			ResultSet resultat = DataAuditModele.exeRequete(sql, this.getCo(), 0);
	
		
			try {
				while(resultat.next()){
				
					nbValeursIncorrectes += resultat.getInt(1) - 1;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (nbValeursIncorrectes >= this.getColonne().getNbCasesRemplies()){
				nbValeursIncorrectes = this.getColonne().getNbCasesRemplies();
			}
			
			System.out.println("Mapping value : id");
		
			return nbValeursIncorrectes;
			
		}
		

		
		// Le pays ne doit pas contenir de chiffre et de ponctuation
		else if (this.getId() == 2){
			
			this.getColonne().setClasse("Indicator");
			
			if (this.getColonne().getTypeDeDonnee() == "INT"){
				return this.getColonne().getNbCasesRemplies();
			}
			
			else {
			
			 String sql = "select " + this.getColonne().getNomColonne() + " ";
				sql +=	"from " + this.getNomTable();
				
			ResultSet resultat = DataAuditModele.exeRequete(sql, this.getCo(), 0);
		
			try {
				while (resultat.next()){
					if (resultat.getString(1).indexOf("\\d") != -1 || resultat.getString(1).indexOf("\\p{Punct}") != -1){
						nbValeursIncorrectes ++;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (nbValeursIncorrectes >= this.getColonne().getNbCasesRemplies()){
				nbValeursIncorrectes = this.getColonne().getNbCasesRemplies();
			}
				
			System.out.println("Mapping value : country");
			
			return nbValeursIncorrectes;
				
			}
			
		}
		// L'entreprise ne doit pas contenir de ponctuation
		else if (this.getId() == 3){
			
			this.getColonne().setClasse("Text");
			
			if (this.getColonne().getTypeDeDonnee() == "INT"){
				return this.getColonne().getNbCasesRemplies();
			}
			
			else {
			
			 String sql = "select " + this.getColonne().getNomColonne() + " ";
				sql +=	"from " + this.getNomTable();
				
			ResultSet resultat = DataAuditModele.exeRequete(sql, this.getCo(), 0);
		
			try {
				while (resultat.next()){
					if (resultat.getString(1).indexOf("\\p{Punct}") != -1){
						nbValeursIncorrectes ++;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (nbValeursIncorrectes >= this.getColonne().getNbCasesRemplies()){
				nbValeursIncorrectes = this.getColonne().getNbCasesRemplies();
			}
			
			System.out.println("Mapping value : company");
				
			return nbValeursIncorrectes;
				
			}
		}
		
		// Adresse 1 ??
		else if (this.getId() == 4){
			
			this.getColonne().setClasse("Text");
			
			System.out.println("Mapping value : adresse 1");
			return nbValeursIncorrectes;
		}
		
		// Adresse 2 ??
		else if (this.getId() == 5){
			
			this.getColonne().setClasse("Text");
			
			return nbValeursIncorrectes;
		}
		
		// Adresse 3 ??
		else if (this.getId() == 6){
			
			this.getColonne().setClasse("Text");
			
			return nbValeursIncorrectes;
		}
		
		// La ville ne doit pas contenir de chiffre
		else if (this.getId() == 7){
			
			this.getColonne().setClasse("Text");
			
			if (this.getColonne().getTypeDeDonnee() == "INT"){
				return this.getColonne().getNbCasesRemplies();
			}
			
			else {
			
			 String sql = "select " + this.getColonne().getNomColonne() + " ";
				sql +=	"from " + this.getNomTable();
			
			ResultSet resultat = DataAuditModele.exeRequete(sql, this.getCo(), 0);
		
			try {
				while (resultat.next()){
					if (resultat.getString(1).indexOf("\\d") != -1){
						nbValeursIncorrectes ++;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (nbValeursIncorrectes >= this.getColonne().getNbCasesRemplies()){
				nbValeursIncorrectes = this.getColonne().getNbCasesRemplies();
			}
				
			return nbValeursIncorrectes;
				
			}
		}
		// L'état ne doit pas contenir de chiffres
		else if (this.getId() == 8){
			
			this.getColonne().setClasse("Indicator");
			
			if (this.getColonne().getTypeDeDonnee() == "INT"){
				return this.getColonne().getNbCasesRemplies();
			}
			
			else {
			
			 String sql = "select " + this.getColonne().getNomColonne() + " ";
				sql +=	"from " + this.getNomTable();
				
			ResultSet resultat = DataAuditModele.exeRequete(sql, this.getCo(), 0);
		
			try {
				while (resultat.next()){
					if (resultat.getString(1).indexOf("\\d") != -1){
						nbValeursIncorrectes ++;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (nbValeursIncorrectes >= this.getColonne().getNbCasesRemplies()){
				nbValeursIncorrectes = this.getColonne().getNbCasesRemplies();
			}
				
			return nbValeursIncorrectes;
				
			}
		}
		// Le zip code doit contenir des chiffres
		
		else if (this.getId() == 9){
			
			this.getColonne().setClasse("Code");
			
			if (this.getColonne().getTypeDeDonnee() == "INT"){
				return this.getColonne().getNbCasesRemplies();
			}
			
			else {
			
			 String sql = "select " + this.getColonne().getNomColonne() + " ";
				sql +=	"from " + this.getNomTable();
				
			ResultSet resultat = DataAuditModele.exeRequete(sql, this.getCo(), 0);
		
			try {
				while (resultat.next()){
					if (resultat.getString(1).indexOf("\\d") == -1){
						nbValeursIncorrectes ++;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (nbValeursIncorrectes >= this.getColonne().getNbCasesRemplies()){
				nbValeursIncorrectes = this.getColonne().getNbCasesRemplies();
			}
				
			return nbValeursIncorrectes;
				
			}
		}
		// Le num de téléphone doit contenir des chiffres
		
		else if (this.getId() == 10){
			
			this.getColonne().setClasse("Code");
			
			if (this.getColonne().getTypeDeDonnee() == "INT"){
				return this.getColonne().getNbCasesRemplies();
			}
			
			else {
			
			 String sql = "select " + this.getColonne().getNomColonne() + " ";
				sql +=	"from " + this.getNomTable();
				
			ResultSet resultat = DataAuditModele.exeRequete(sql, this.getCo(), 0);
		
			try {
				while (resultat.next()){
					if (resultat.getString(1).indexOf("\\d") == -1){
						nbValeursIncorrectes ++;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (nbValeursIncorrectes >= this.getColonne().getNbCasesRemplies()){
				nbValeursIncorrectes = this.getColonne().getNbCasesRemplies();
			}
				
			return nbValeursIncorrectes;
				
			}
		}
		
		// Website Regex Website
		else if (this.getId() == 11){
			
			this.getColonne().setClasse("Text");
			
			if (this.getColonne().getTypeDeDonnee() == "INT"){
				return this.getColonne().getNbCasesRemplies();
			}
			
			else {
			
			 String sql = "select " + this.getColonne().getNomColonne() + " ";
				sql +=	"from " + this.getNomTable();
				
			ResultSet resultat = DataAuditModele.exeRequete(sql, this.getCo(), 0);
		
			try {
				while (resultat.next()){

						nbValeursIncorrectes ++;
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (nbValeursIncorrectes >= this.getColonne().getNbCasesRemplies()){
				nbValeursIncorrectes = this.getColonne().getNbCasesRemplies();
			}
				
			return nbValeursIncorrectes;
				
			}

		}
		
		// SIC4
		else if (this.getId() == 12){
			
			this.getColonne().setClasse("Code");
			
			return nbValeursIncorrectes;
		}
		
		// NAICS6
		else if (this.getId() == 13){
			
			this.getColonne().setClasse("Code");
			
			return nbValeursIncorrectes;
		}
		
		// NAF
		else if (this.getId() == 14){
			
			this.getColonne().setClasse("Code");
			
			return nbValeursIncorrectes;
		}
		
		// APE
		else if (this.getId() == 15){
			
			this.getColonne().setClasse("Code");
			
			return nbValeursIncorrectes;
		}
		
		// Desc Industrie code
		else if (this.getId() == 16){
			
			this.getColonne().setClasse("Code");
			
			return nbValeursIncorrectes;
		}
		
		// Employee at site
		else if (this.getId() == 17){
			
			this.getColonne().setClasse("Quantity");
			
			return nbValeursIncorrectes;
		}
		
		// Employee total
		else if (this.getId() == 18){
			
			this.getColonne().setClasse("Quantity");
			
			return nbValeursIncorrectes;
		}
		
		// Annual sales
		else if (this.getId() == 19){
			
			this.getColonne().setClasse("Quantity");
			
			return nbValeursIncorrectes;
		}
		
		// ID Contact
		else if (this.getId() == 20){
			
			this.getColonne().setClasse("Identifier");
			
			return nbValeursIncorrectes;
		}
		
		// Title
		else if (this.getId() == 21){
			
			this.getColonne().setClasse("Indicator");
			
			return nbValeursIncorrectes;
		}
		
		// Contact first name
		else if (this.getId() == 22){
			
			this.getColonne().setClasse("Text");
			
			return nbValeursIncorrectes;
		}
		
		// Contact last name
		else if (this.getId() == 23){
			
			this.getColonne().setClasse("Text");
			
			return nbValeursIncorrectes;
		}
		
		// Contact phone
		else if (this.getId() == 24){
			
			this.getColonne().setClasse("Code");
			
			return nbValeursIncorrectes;
		}
		
		// Contact email
		else if (this.getId() == 25){
			
			this.getColonne().setClasse("Text");
			
			return nbValeursIncorrectes;
		}
		
		// Internal marketability indicator
		else if (this.getId() == 26){
			
			this.getColonne().setClasse("Indicator");
			
			return nbValeursIncorrectes;
		}
		
		// Opt/Out flag
		else{
			
			this.getColonne().setClasse("Indicator");
			
			return nbValeursIncorrectes;
		}
	}

	
}
