package Modele;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CSVtoSQL {
	
	public static void main (String[] args) throws IOException{

		System.out.println("-------- Test d'importation d'un fichier dans la base de donnée ------------");
		
		System.out.print("Chemin du fichier CSV à importer : ");
		
		Scanner sc = new Scanner(System.in);
		
		String fichierCSV = sc.nextLine();
		
		sc.close();
		
		String table = "";
		
		// Lecture de la première ligne du fichier CSV pour la construction de la table
		
		try
		{
			FileReader fr = new FileReader(fichierCSV);
			
			BufferedReader br = new BufferedReader(fr);
		   
		   String chaine;
		 
		  chaine = br.readLine();
		  
		  String[] tabChaine = chaine.split(";");
		  
		  for (int i = 0 ; i < tabChaine.length ; i++){
			  
			  if (i == 0){
				  table += "id INTEGER NOT NULL PRIMARY KEY,";
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
				  table += tabChaine[i] + " VARCHAR(100)";

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
				  
				  table += tabChaine[i] + " VARCHAR(100),";

			  }
		  }
		  
		   br.close();
		   System.out.println(table);
		   
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
		
			ConnectionSQL connection = new ConnectionSQL();
			
			openConnection("localhost:3066, root, root");
		
			String requeteSuppresionTable = "DROP TABLE IF EXISTS tableTest";
			
			String requeteCreationTable = "CREATE TABLE tableTest(";
			requeteCreationTable += table;
			requeteCreationTable += ")";
			
			String as = new String ("\\"); 
			String das = new String("\\\\");
			
			String fichierCSVdoubleBS = fichierCSV.replace(as, das);
			
			String requeteLoadData = "LOAD DATA LOCAL INFILE '" + fichierCSVdoubleBS + "' INTO TABLE tableTest FIELDS TERMINATED BY ';' IGNORE 1 LINES";
			
			
		try{
			
			PreparedStatement statementSuppr = prepareStatement(requeteSuppresionTable);
			statementSuppr.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println("La table n'a pas été supprimé.");
			e.printStackTrace();
			return;
		}
		
		try{
			PreparedStatement StatementCreate = connection.prepareStatement(requeteCreationTable);
			StatementCreate.executeUpdate();
			
		}
		catch(SQLException e) {
			System.out.println("La table n'a pas été crée.");
			e.printStackTrace();
			return;
		}
		try{
			Statement StatementLoad = connection.prepareStatement(requeteLoadData);

			
			StatementLoad.execute(requeteLoadData);

		}
		catch(SQLException e) {
			System.out.println("Le fichier n'a pas été importé.");
			e.printStackTrace();
			return;
		}
		
		System.out.println("Le fichier a été importé dans MySQL.");
		
	}
	
}

	}

}
