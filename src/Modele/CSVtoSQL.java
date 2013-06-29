package Modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mysql.jdbc.Connection;

public class CSVtoSQL {
	
	private String url;
	
	private String user;
	
	private String password;
	
	CSVtoSQL(){

	}
	
	CSVtoSQL(String url, String user, String password){
		
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

	// Fonction qui renvoit le chemin du fichier CSV à importer
	public String cheminDuCSV(){
		
		System.out.print("Chemin du fichier CSV à importer : ");
		
		Scanner sc = new Scanner(System.in);
		
		String fichierCSV = sc.nextLine();
		
		File fic = new File(fichierCSV);
		
		// Boucle permettant de vérifier si le fichier est bien un fichier CSV et si la taille du fichier est bien inférieure à 100 Méga
		
		while (!fichierCSV.endsWith(".csv") || fic.length() >= 104857600){
			
			if (!fichierCSV.endsWith(".csv")){
			
			System.out.println("Le fichier doit être un fichier CSV.");
			
			}
			
			else{
				
				System.out.println("La taille maximum du fichier doit être inférieure à 100 Mo.");	
			}
			
			System.out.print("Chemin du fichier CSV à importer : ");
			
			fichierCSV = sc.nextLine();
		}
		
		sc.close();
		
		return fichierCSV;
		
	}
	// La table dans laquelle sera importé le contenu du fichier portera le même nom que ce dernier.
	// Si l'expression régulière échoue, le nom de la table sera tableDefault
	public String nomDeLaTable(String fichierCSV){
		
		String nomTable;
		
		Pattern pat = Pattern.compile("\\");
		
		String[] sisi = pat.split(fichierCSV);
		
		nomTable = sisi[sisi.length-1];
		
		if (nomTable.indexOf('-') != -1){nomTable.replace('-','_');}
		if (nomTable.indexOf('?') != -1){nomTable.replace('?','_');}
		if (nomTable.indexOf('(') != -1){nomTable.replace('(','_');}
		if (nomTable.indexOf(')') != -1){nomTable.replace(')','_');}
		
		return nomTable;
	}
	// Destruction de la table pour être sur quel n'existe pas déja quand on va la recréer
	public void deleteTable(Connection co, String nomTable){
		
		String requeteSuppresionTable = "DROP TABLE IF EXISTS ";
		
		try{
			
			PreparedStatement statementSuppr = co.prepareStatement(requeteSuppresionTable + nomTable);
			statementSuppr.executeUpdate();
		}
		
		catch(SQLException e) {
			System.out.println("La table n'a pas été supprimé.");
			e.printStackTrace();
			return;
		}
		
		System.out.println("La table " + nomTable + " a été détruite.");
		
	}
	// Fonction reccueillant le nom des champs de la table et leur type
	public String champsDeLaTable(String fichierCSV){
		
		String champs ="";
		
		try
		{
			FileReader fr = new FileReader(fichierCSV);
			
			BufferedReader br = new BufferedReader(fr);
		  
			String chaine;
		 
			chaine = br.readLine();
		  
			String[] tabChaine = chaine.split(";");
		  
		  for (int i = 0 ; i < tabChaine.length ; i++){
			  
			  if (i == 0){
				  champs += "id INTEGER NOT NULL PRIMARY KEY,";
			  }
			  else if (i == tabChaine.length-1){
				  
				 if (tabChaine[i].indexOf('-') != -1 || tabChaine[i].indexOf('?') != -1 || tabChaine[i].indexOf('(') != -1 || tabChaine[i].indexOf(')') != -1){
					  tabChaine[i] = tabChaine[i].replace('-','_');
					  tabChaine[i] = tabChaine[i].replace('?','_');
					  tabChaine[i] = tabChaine[i].replace('(','_');
					  tabChaine[i] = tabChaine[i].replace(')','_');
				 }
				 if (tabChaine[i].length() > 60){
					  tabChaine[i] = tabChaine[i].substring(0, 40);
				  }
				  champs += tabChaine[i] + " VARCHAR(100)";

			  }
			  else{
				  
				  if (tabChaine[i].indexOf('-') != -1 || tabChaine[i].indexOf('?') != -1 || tabChaine[i].indexOf('(') != -1 || tabChaine[i].indexOf(')') != -1){
					  tabChaine[i] = tabChaine[i].replace('-','_');
					  tabChaine[i] = tabChaine[i].replace('?','_');
					  tabChaine[i] = tabChaine[i].replace('(','_');
					  tabChaine[i] = tabChaine[i].replace(')','_');
				  }
				  if (tabChaine[i].length() > 60){
					  tabChaine[i] = tabChaine[i].substring(0, 40);
				  }
				  
				  champs += tabChaine[i] + " VARCHAR(100),";

			  }
		  }
		  
		   br.close();
		   System.out.println(champs);
		   
		   System.out.println(fichierCSV);
		   
		}
		catch (FileNotFoundException e)
		{
		   System.out.println("Le fichier est introuvable !");
		}
		catch (IOException e) 
		{
			 System.out.println( e );
		}
		
		return champs;
	}
	// Fonction de création de la table dans laquelle va être importé le fichier
	public void createTable(Connection co, String nomTable, String champs){
		
		String requeteCreationTable = "CREATE TABLE "+ nomTable +"(";
		requeteCreationTable += champs;
		requeteCreationTable += ")";		
		
		try{
			PreparedStatement StatementCreate = co.prepareStatement(requeteCreationTable);
			StatementCreate.executeUpdate();
			
		}
		catch(SQLException e) {
			System.out.println("La table n'a pas été crée.");
			e.printStackTrace();
			return;
		}
		
		System.out.println("La table " + nomTable + " a été créee.");
	}
	// Fonction qui charge les données du fichier CSV dans la table
	public void loadFichierCSV(Connection co, String nomTable, String fichierCSV){
		
		String as = new String ("\\"); 
		String das = new String("\\\\");
		
		String fichierCSVdoubleBS = fichierCSV.replace(as, das);
		
		String requeteLoadData = "LOAD DATA LOCAL INFILE '" + fichierCSVdoubleBS + "' INTO TABLE " + nomTable + " FIELDS TERMINATED BY ';' IGNORE 1 LINES";
		
		try{
			Statement StatementLoad = co.prepareStatement(requeteLoadData);

			
			StatementLoad.execute(requeteLoadData);

		}
		catch(SQLException e) {
			System.out.println("Le fichier n'a pas été importé.");
			e.printStackTrace();
			return;
		}
		
		System.out.println("Le fichier a été importé dans la table " + nomTable);
	}
	
	public static void main (String[] args){

		CSVtoSQL main = new CSVtoSQL("localhost:3306/dbtriviacsv", "root", "root");
		
		Connection co = main.openConnection();
		
		String chemin = main.cheminDuCSV();
		
		String champs = main.champsDeLaTable(chemin);
		
		String nomTable = main.nomDeLaTable(chemin);
		
		main.deleteTable(co, nomTable);
		
		main.createTable(co,nomTable, champs);
		
		main.loadFichierCSV(co,nomTable, chemin);
		
		main.closeConnection(co);
	

	}

}
