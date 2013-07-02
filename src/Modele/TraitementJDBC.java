package Modele;

import java.sql.*;

public class TraitementJDBC {

	public static int nbLignes(String nomTable, Connection co){
		
		String req = "SELECT COUNT() FROM " + nomTable;
		
		ResultSet rs = exec1Requete(req, co, 1);
		
		
	}
}
