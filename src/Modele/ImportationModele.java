package Modele;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import au.com.bytecode.opencsv.CSVReader;

import com.mysql.jdbc.Connection;

public class ImportationModele {
	
	// Fonction qui renvoit le chemin du fichier CSV à importer
	public static String cheminDuCSV(){
		
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
	public static String nomDeLaTable(String fichierCSV){
			
		String nomTable = fichierCSV.substring(fichierCSV.lastIndexOf("\\") + 1, fichierCSV.indexOf('.'));
		
		nomTable = nomTable.replaceAll("\\W", "");
		
		System.out.println("Nom de la table : " + nomTable);
		
		return nomTable;
	}
	

	// Fonction qui renvoit un tableau contenant le type de chaque colonne de la table
	public static String[] typeDeDonnees(FileReader fr) throws IOException{
		
		CSVReader reader = new CSVReader(fr, ';');
				
		String [] [] tabDonnee = new String [6][];
		
		int i = 0;
		
		// Récupération des 5 premières lignes de données dans un tableau.		

		while((tabDonnee[i] = reader.readNext()) != null && i < 5)
		   {
			  i++;
		   }
		reader.close();
		
		System.out.println("Affichage des 5 premières lignes de la table sur lesquelles seront effectués les tests :");
		
		for (i = 0 ; i < 6; i ++){
			for (int j = 0; j < tabDonnee[0].length; j++)
				System.out.print(tabDonnee[i][j] + "\t");
			System.out.println("");
		}
				
		
		String[] type = new String[tabDonnee[0].length];
		
		for ( i = 0; i < tabDonnee[0].length ; i ++)
		   {
			   int j = 1;
			   boolean estEntier = false;
			   
			   //boolean estFloat = false;
			   
			   while (estUnEntier(tabDonnee[j][i]) && j < 5){
				   //System.out.println(tabDonnee[j][i]);
				   estEntier = true;
				   j++;
			   }
			   
			   j = 1;
			   /*
			   while (estUnFloat(tabDonnee[j][i]) && j < 5){
				   estFloat = true;
				   j++;
			   }
			   */
			   if (estEntier){
				   type[i] = "INTEGER";
			   }
			   /*
			   else if (estFloat){
				   type[i] = "FLOAT";
			   }
			   */
			   else
				   type[i] = "VARCHAR(100)";
			   
		   }
		   
		   return type;
	
	}
	
	// Renvoit la lecture du fichier CSV
	public static FileReader lecteurDeFichier(String fichierCSV){
		
		FileReader fr = null;
		
		try{
			fr = new FileReader(fichierCSV);
			}
		catch (FileNotFoundException e)
			{
			   System.out.println("Le fichier est introuvable !");
			}
		
		return fr;
		
	}
	
	
	// Destruction de la table pour être sur quel n'existe pas déja quand on va la recréer
	public static void deleteTable(Connection co, String nomTable){
		
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
	
	// Fonction reccueillant le nom des champs de la table et leur type et assemble une chaine de caractères
	public static String champsDeLaTable(String [] type, String [] nomTable){
		
		String champs ="";
		
		for (int i = 0; i < type.length; i ++)
			/*if (i == 0){
				champs += "ID INTEGER NOT NULL PRIMARY KEY, ";
			}*/
			if (i == type.length - 1){
				champs += nomTable[i] + " " + type[i];
			}
			else
				champs += nomTable[i] + " " + type[i] + ", ";
		
		System.out.println(champs);
		
		return champs;
	}
	
	// Fonction reccueillant le nom des colonnes de la table
	public static String[] nomDesColonnes(FileReader fr) throws IOException{
		
			CSVReader reader = new CSVReader(fr, ';');
		  
			String[] noms;
		 
			noms = reader.readNext();
			
			reader.close();
		  
		  for (int i = 0 ; i < noms.length ; i++){
			  			  
				noms[i] = noms[i].replaceAll("\\W","");
				
				 if (noms[i].length() > 60){
					  noms[i] = noms[i].substring(0, 40);
				  }
				 
				 noms[i] += "_" + i;

		  }		  
		   
		return noms;
	}
	
	// Fonction permettant de tester si un String est un entier
	public static boolean estUnEntier(String chaine) {
		try {
			Integer.parseInt(chaine);
		} catch (NumberFormatException e){
			return false;
		}
 
		return true;
	}
	
	// Fonction permettant de tester si un String est un Float
	/*
	public static boolean estUnFloat(String chaine){
		try {
			Float.parseFloat(chaine);
		}
		catch (NumberFormatException e){
			return false;
		}
		return true;
	}
	*/
	// Fonction de création de la table dans laquelle va être importé le fichier
	public static void createTable(Connection co, String nomTable, String champs){
		
		String requeteCreationTable = "CREATE TABLE "+ nomTable +"(";
		requeteCreationTable += champs;
		requeteCreationTable += ")";
		
		System.out.println(requeteCreationTable);
		
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
	public static void loadFichierCSV(Connection co, String nomTable, String fichierCSV){
		
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
		
		System.out.println("Le fichier a été importé dans la table " + nomTable + ".");
	}
	
	public static void ajoutID(String nomTable, Connection co){
		
		String reqID = "ALTER TABLE " + nomTable + " ADD ID INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY FIRST";
		
		try {
			Statement ajoutID = co.prepareStatement(reqID);
			
			ajoutID.execute(reqID);
		}
		
		catch(SQLException e) {
			System.out.println("Le fichier n'a pas été importé.");
			e.printStackTrace();
			return;
		}
		
		System.out.println("Une clef primaire a été ajoutée.");
	}
	
	
	// C:\Users\guillaumefay\Desktop\CSV\tudla.csv
	public static void main (String[] args) throws IOException{
		
		ConnexionModele main = new ConnexionModele("localhost", "3306", "dbtriviacsv", "root", "root");
		
		Connection co = main.openConnection();
		
		String chemin = cheminDuCSV();
		
		String nomTable = nomDeLaTable(chemin);
				
		deleteTable(co, nomTable);
			
		createTable(co,nomTable, champsDeLaTable(typeDeDonnees(lecteurDeFichier(chemin)), nomDesColonnes(lecteurDeFichier(chemin))));
		
		loadFichierCSV(co,nomTable, chemin);
		
		ajoutID(nomTable, co);
		
		try {
			DataAuditModele traitement = new DataAuditModele(co, nomTable);
			traitement.genererFichierCSV(traitement.dataAudit(), chemin);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		main.closeConnection(co);
	

	}

}
