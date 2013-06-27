package Modele;
import java.io.*;
import java.sql.*;
import java.util.*;

public class ConnectionSQL {

	public static void main (String[] args) throws IOException{
		
		System.out.println("-------- Test de la connection avec la base de donnée ------------");
		 
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver JDBC introuvable");
			e.printStackTrace();
			return;
		}
	 	
		Connection connection = null;
	 
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbtriviacsv","root", "root");
	 
		} catch (SQLException e) {
			System.out.println("Connection échouée, vérifier les paramètres de connection à la base de donnée.");
			e.printStackTrace();
			return;
		}
	 
		if (connection != null) {
			System.out.println("Connection réussie");
		} else {
			System.out.println("Connection échouée");
		}
		
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
		
		
			String requeteSuppresionTable = "DROP TABLE IF EXISTS tableTest";
			
			String requeteCreationTable = "CREATE TABLE tableTest(";
			requeteCreationTable += table;
			requeteCreationTable += ")";
			
			String as = new String ("\\"); 
			String das = new String("\\\\");
			
			String fichierCSVdoubleBS = fichierCSV.replace(as, das);
			
			String requeteLoadData = "LOAD DATA LOCAL INFILE '" + fichierCSVdoubleBS + "' INTO TABLE tableTest FIELDS TERMINATED BY ';' IGNORE 1 LINES";
			
			
		try{
			
			PreparedStatement statementSuppr = connection.prepareStatement(requeteSuppresionTable);
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
