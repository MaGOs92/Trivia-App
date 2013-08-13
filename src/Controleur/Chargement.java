package Controleur;

import java.sql.SQLException;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import Modele.DataAuditModele;
import Modele.ImportationModele;

public class Chargement extends SwingWorker<Integer, String> {
	

	CoImpControleur controlleur;
	
	public CoImpControleur getControlleur() {
		return controlleur;
	}

	public void setControlleur(CoImpControleur controlleur) {
		this.controlleur = controlleur;
	}

	public Chargement(CoImpControleur controlleur){
		this.controlleur = controlleur;
	}

	protected Integer doInBackground() throws Exception {

		   String chemin = getControlleur().getImpPanel().getPathFichier();

		   String nomTable = ImportationModele.nomDeLaTable(chemin);

		   ImportationModele.deleteTable(getControlleur().getCoPanel().getCo() , nomTable);

		   setProgress(0);				   

		   publish("Creating a new table ...");

		   ImportationModele.createTable(getControlleur().getCoPanel().getCo() ,nomTable, ImportationModele.champsDeLaTable(ImportationModele.typeDeDonnees(ImportationModele.lecteurDeFichier(chemin)), ImportationModele.nomDesColonnes(ImportationModele.lecteurDeFichier(chemin))));

		   setProgress(1);

		   publish("Loading the file in the database ...");

		   ImportationModele.loadFichierCSV(getControlleur().getCoPanel().getCo() ,nomTable, chemin);

		   setProgress(2);

		   publish("Adding a unique identifier ...");

		   ImportationModele.ajoutID(nomTable, getControlleur().getCoPanel().getCo());

		   setProgress(3);

		   publish("Filling the arrays ...");

		   try {
			   getControlleur().setTraitement(new DataAuditModele(getControlleur().getCoPanel().getCo(), nomTable));
		   	} 
		   catch (SQLException e) {
		   		e.printStackTrace();
		   	}

		   return 0;

	      }

	      public void done(){

	          if(SwingUtilities.isEventDispatchThread())
	            System.out.println("Traitement terminé");			          
	            //On utilise la méthode get() pour récupérer le résultat
	            //de la méthode doInBackground()
	          getControlleur().lancerGUI();
	        }

	      public void process(List<String> list){
	          for(String str : list){
	        	  getControlleur().getLoaPanel().getHeaderLabel().setText(str);
	        	  System.out.println(str);
	          }
	  }
}

