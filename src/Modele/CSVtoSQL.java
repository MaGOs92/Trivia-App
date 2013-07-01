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

import com.mysql.jdbc.Connection;

public class CSVtoSQL {
	
	static final int LIGNES_TEST = 5;
	
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

	// Fermeture de la connection
	public void closeConnection(Connection co){
		try {
			co.close();
			System.out.println("Connexion ferm�e!");
		}
		catch (SQLException e) {
			System.out.println("Impossible de fermer la connexion");
		}	
	}

	// Fonction qui renvoit le chemin du fichier CSV � importer
	public String cheminDuCSV(){
		
		System.out.print("Chemin du fichier CSV � importer : ");
		
		Scanner sc = new Scanner(System.in);
		
		String fichierCSV = sc.nextLine();
		
		File fic = new File(fichierCSV);
		
		// Boucle permettant de v�rifier si le fichier est bien un fichier CSV et si la taille du fichier est bien inf�rieure � 100 M�ga
		
		while (!fichierCSV.endsWith(".csv") || fic.length() >= 104857600){
			
			if (!fichierCSV.endsWith(".csv")){
			
			System.out.println("Le fichier doit �tre un fichier CSV.");
			
			}
			
			else{
				
				System.out.println("La taille maximum du fichier doit �tre inf�rieure � 100 Mo.");	
			}
			
			System.out.print("Chemin du fichier CSV � importer : ");
			
			fichierCSV = sc.nextLine();
		}
		
		sc.close();
		
		return fichierCSV;
		
	}
	// La table dans laquelle sera import� le contenu du fichier portera le m�me nom que ce dernier.
	public String nomDeLaTable(String fichierCSV){
			
		String nomTable = fichierCSV.substring(fichierCSV.lastIndexOf("\\") + 1, fichierCSV.indexOf('.'));

		
		if (nomTable.indexOf('-') != -1){nomTable.replace('-','_');}
		if (nomTable.indexOf('?') != -1){nomTable.replace('?','_');}
		if (nomTable.indexOf('(') != -1){nomTable.replace('(','_');}
		if (nomTable.indexOf(')') != -1){nomTable.replace(')','_');}
		
		return nomTable;
	}
	
	public String[] typeDeDonnees(BufferedReader br){
		
		String donnee;
		
		String [] [] tabDonnee = new String [6][100];
		
		boolean estEntier = false;
		
		boolean estFloat = false;
		
		int i = 1;
		
		// R�cup�ration des 5 premi�res lignes de donn�es dans un tableau.
		
		try{
			
		while((donnee = br.readLine())!= null && i < 6)
		   {
			  
			  tabDonnee[i] = donnee.split(";");
		      i++;
			 
		   }
		   
		   br.close();
		}
		catch (IOException e) 
		{
			 System.out.println("Probl�me de lecture du fichier.");
		}
		
		
		for (i = 0 ; i < 6; i ++)
			for (int j = 0; j < tabDonnee[i].length; j++)
				System.out.println(tabDonnee[i][j]);
		
		
		String[] type = new String[tabDonnee[0].length];
		
		for ( i = 0; i < tabDonnee[0].length ; i ++)
		   {
				int j = 0;
			   while (estUnEntier(tabDonnee[j][i]) && j < 6){
				   estEntier = true;
				   j++;
			   }
			   j = 0;
			   /*
			   while (estUnFloat(tabDonnee[j][i]) && j < 6){
				   estFloat = true;
				   j++;
			   }
			   */
			   if (estEntier){
				   type[i] = "INTEGER";
			   }
			   else if (estFloat){
				   type[i] = "FLOAT";
			   }
			   else
				   type[i] = "VARCHAR(100)";
			   
			   for (i = 0; i < tabDonnee[0].length; i++)
				   System.out.println(type[i]);
			   
		   }
		   
		   return type;
	
	}
	
	// Renvoit la lecture du fichier CSV
	public BufferedReader lecteurDeFichier(String fichierCSV){
		
		FileReader fr = null;
		
		try{
			fr = new FileReader(fichierCSV);
			}
		catch (FileNotFoundException e)
			{
			   System.out.println("Le fichier est introuvable !");
			}
		
		BufferedReader br = new BufferedReader(fr);
		
		return br;
		
	}
	
	
	// Destruction de la table pour �tre sur quel n'existe pas d�ja quand on va la recr�er
	public void deleteTable(Connection co, String nomTable){
		
		String requeteSuppresionTable = "DROP TABLE IF EXISTS ";
		
		try{
			
			PreparedStatement statementSuppr = co.prepareStatement(requeteSuppresionTable + nomTable);
			statementSuppr.executeUpdate();
		}
		
		catch(SQLException e) {
			System.out.println("La table n'a pas �t� supprim�.");
			e.printStackTrace();
			return;
		}
		
		System.out.println("La table " + nomTable + " a �t� d�truite.");
		
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
			 System.out.println("Probl�me de lecture du fichier.");
		}
		
		return champs;
	}
	
	// Fonction permettant de tester si un String est un entier
	public boolean estUnEntier(String chaine) {
		try {
			Integer.parseInt(chaine);
		} catch (NumberFormatException e){
			return false;
		}
 
		return true;
	}
	
	// Fonction permettant de tester si un String est un Float
	public boolean estUnFloat(String chaine){
		try {
			Float.parseFloat(chaine);
		}
		catch (NumberFormatException e){
			return false;
		}
		return true;
	}
	/*
	// Fonction permettant de tester si un String est une date
	public boolean estUneDate(String chaine){
		try{
			
		}
		catch 
	}
	*/
	// Fonction de cr�ation de la table dans laquelle va �tre import� le fichier
	public void createTable(Connection co, String nomTable, String champs){
		
		String requeteCreationTable = "CREATE TABLE "+ nomTable +"(";
		requeteCreationTable += champs;
		requeteCreationTable += ")";		
		
		try{
			PreparedStatement StatementCreate = co.prepareStatement(requeteCreationTable);
			StatementCreate.executeUpdate();
			
		}
		catch(SQLException e) {
			System.out.println("La table n'a pas �t� cr�e.");
			e.printStackTrace();
			return;
		}
		
		System.out.println("La table " + nomTable + " a �t� cr�ee.");
	}
	// Fonction qui charge les donn�es du fichier CSV dans la table
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
			System.out.println("Le fichier n'a pas �t� import�.");
			e.printStackTrace();
			return;
		}
		
		System.out.println("Le fichier a �t� import� dans la table " + nomTable + ".");
	}
	
	public static void main (String[] args){

		CSVtoSQL main = new CSVtoSQL("localhost:3306/dbtriviacsv", "root", "root");
		
		Connection co = main.openConnection();
		
		String chemin = main.cheminDuCSV();
		
		String champs = main.champsDeLaTable(chemin);
		
		String nomTable = main.nomDeLaTable(chemin);
		
		main.typeDeDonnees(main.lecteurDeFichier(chemin));
		
		main.deleteTable(co, nomTable);
		
		main.createTable(co,nomTable, champs);
		
		main.loadFichierCSV(co,nomTable, chemin);
		
		main.closeConnection(co);
	

	}

}
