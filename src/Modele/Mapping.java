package Modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mysql.jdbc.Connection;

public class Mapping {
	
	private int id;

	private String nom;
	
	private Colonne colonne;
	
	private String nomTable;
	
	private Connection co;
	
	public static final Matcher WEB  = Pattern.compile(
			new StringBuilder()
				.append("((?:(http|https|Http|Https|rtsp|Rtsp):")
				.append("\\/\\/(?:(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)")
					.append("\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,64}(?:\\:(?:[a-zA-Z0-9\\$\\-\\_")
					.append("\\.\\+\\!\\*\\'\\(\\)\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,25})?\\@)?)?")
					.append("((?:(?:[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}\\.)+")   // named host
					.append("(?:")   // plus top level domain
					.append("(?:aero|arpa|asia|a[cdefgilmnoqrstuwxz])")
					.append("|(?:biz|b[abdefghijmnorstvwyz])")
					.append("|(?:cat|com|coop|c[acdfghiklmnoruvxyz])")
					.append("|d[ejkmoz]")
					.append("|(?:edu|e[cegrstu])")
					.append("|f[ijkmor]")
					.append("|(?:gov|g[abdefghilmnpqrstuwy])")
					.append("|h[kmnrtu]")
					.append("|(?:info|int|i[delmnoqrst])")
					.append("|(?:jobs|j[emop])")
					.append("|k[eghimnrwyz]")
					.append("|l[abcikrstuvy]")
					.append("|(?:mil|mobi|museum|m[acdghklmnopqrstuvwxyz])")
					.append("|(?:name|net|n[acefgilopruz])")
					.append("|(?:org|om)")
					.append("|(?:pro|p[aefghklmnrstwy])")
					.append("|qa")
					.append("|r[eouw]")
					.append("|s[abcdeghijklmnortuvyz]")
					.append("|(?:tel|travel|t[cdfghjklmnoprtvwz])")
					.append("|u[agkmsyz]")
					.append("|v[aceginu]")
					.append("|w[fs]")
					.append("|y[etu]")
					.append("|z[amw]))")
					.append("|(?:(?:25[0-5]|2[0-4]") // or ip address
					.append("[0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(?:25[0-5]|2[0-4][0-9]")
					.append("|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(?:25[0-5]|2[0-4][0-9]|[0-1]")
					.append("[0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(?:25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}")
					.append("|[1-9][0-9]|[0-9])))")
					.append("(?:\\:\\d{1,5})?)") // plus option port number
					.append("(\\/(?:(?:[a-zA-Z0-9\\;\\/\\?\\:\\@\\&\\=\\#\\~")  // plus option query params
					.append("\\-\\.\\+\\!\\*\\'\\(\\)\\,\\_])|(?:\\%[a-fA-F0-9]{2}))*)?")
					.append("(?:\\b|$)").toString()
				).matcher("");
	
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
			return nbValeursIncorrectes;
		}
		// L' id doit être unique
		else if (this.getId() == 1){
		
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
			
			System.out.println("Mapping value : adresse 1");
			return nbValeursIncorrectes;
		}
		
		// Adresse 2 ??
		else if (this.getId() == 5){
			return nbValeursIncorrectes;
		}
		
		// Adresse 3 ??
		else if (this.getId() == 6){
			return nbValeursIncorrectes;
		}
		
		// La ville ne doit pas contenir de chiffre
		else if (this.getId() == 7){
			
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
			
			if (this.getColonne().getTypeDeDonnee() == "INT"){
				return this.getColonne().getNbCasesRemplies();
			}
			
			else {
			
			 String sql = "select " + this.getColonne().getNomColonne() + " ";
				sql +=	"from " + this.getNomTable();
				
			ResultSet resultat = DataAuditModele.exeRequete(sql, this.getCo(), 0);
		
			try {
				while (resultat.next()){
					if (!WEB.matches()){
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
		
		// SIC4
		else if (this.getId() == 12){
			return nbValeursIncorrectes;
		}
		
		// NAICS6
		else if (this.getId() == 13){
			return nbValeursIncorrectes;
		}
		
		// NAF
		else if (this.getId() == 14){
			return nbValeursIncorrectes;
		}
		
		// APE
		else if (this.getId() == 15){
			return nbValeursIncorrectes;
		}
		
		// Desc Industrie code
		else if (this.getId() == 16){
			return nbValeursIncorrectes;
		}
		
		// Employee at site
		else if (this.getId() == 17){
			return nbValeursIncorrectes;
		}
		
		// Employee total
		else if (this.getId() == 18){
			return nbValeursIncorrectes;
		}
		
		// Annual sales
		else if (this.getId() == 19){
			return nbValeursIncorrectes;
		}
		
		// ID Contact
		else if (this.getId() == 20){
			return nbValeursIncorrectes;
		}
		
		// Title
		else if (this.getId() == 21){
			return nbValeursIncorrectes;
		}
		
		// Contact first name
		else if (this.getId() == 22){
			return nbValeursIncorrectes;
		}
		
		// Contact last name
		else if (this.getId() == 23){
			return nbValeursIncorrectes;
		}
		
		// Contact phone
		else if (this.getId() == 24){
			return nbValeursIncorrectes;
		}
		
		// Contact email
		else if (this.getId() == 25){
			return nbValeursIncorrectes;
		}
		
		// Internal marketability indicator
		else if (this.getId() == 26){
			return nbValeursIncorrectes;
		}
		
		// Opt/Out flag
		else{
			return nbValeursIncorrectes;
		}
	}

	
}
