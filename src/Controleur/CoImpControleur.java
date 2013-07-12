package Controleur;


import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JFrame;

import Modele.DataAuditModele;
import Modele.ImportationModele;
import Vue.ConnexionPanel;
import Vue.ImportationPanel;

public class CoImpControleur {

	   ConnexionPanel coPanel;  
	   ImportationPanel impPanel;
	   DataAuditModele traitement;
	   JFrame fenetre;
	   
	public ConnexionPanel getCoPanel() {
		return coPanel;
	}
	public void setCoPanel(ConnexionPanel coPanel) {
		this.coPanel = coPanel;
	}
	public ImportationPanel getImpPanel() {
		return impPanel;
	}
	public void setImpPanel(ImportationPanel impPanel) {
		this.impPanel = impPanel;
	}
	
	public JFrame getFenetre() {
		return fenetre;
	}
	
	public DataAuditModele getTraitement() {
		return traitement;
	}
	public void setTraitement(DataAuditModele traitement) {
		this.traitement = traitement;
	}
	public JFrame creerFenetre()
	{	
		// Création de la fenetre
		
		JFrame fenetre = new JFrame ("DataAudit");
		fenetre.setSize(500, 230);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setTitle("DataAudit");
		fenetre.setLocationRelativeTo(null); // Place la fenetre au milieu de l'écran
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
	
		fenetre.setVisible(true);
		
		return fenetre;

	}


	public void setFenetre(JFrame fenetre) {
		this.fenetre = fenetre;
	}
	
	// Constructor  
	   public CoImpControleur()  
	   {  
		  setFenetre(this.creerFenetre());
	      setCoPanel(new ConnexionPanel(this));  
	      setImpPanel(new ImportationPanel(this));
	      
	      getFenetre().getContentPane().add(getCoPanel());

	      getFenetre().setVisible(true);  
	   }  
	   public void showImp()  
	   {  
		   getFenetre().getContentPane().removeAll();
		   getFenetre().getContentPane().add(getImpPanel());
		   getFenetre().setVisible(true);
	   }
	   
	   public void lancerDataAudit() throws IOException{
		   	
			String chemin = getImpPanel().getPathFichier();
			
			String nomTable = ImportationModele.nomDeLaTable(chemin);
					
			ImportationModele.deleteTable(getCoPanel().getCo() , nomTable);
				
			ImportationModele.createTable(getCoPanel().getCo() ,nomTable, ImportationModele.champsDeLaTable(ImportationModele.typeDeDonnees(ImportationModele.lecteurDeFichier(chemin)), ImportationModele.nomDesColonnes(ImportationModele.lecteurDeFichier(chemin))));
			
			ImportationModele.loadFichierCSV(getCoPanel().getCo() ,nomTable, chemin);
			
			ImportationModele.ajoutID(nomTable, getCoPanel().getCo());
			
			try {
				setTraitement(new DataAuditModele(getCoPanel().getCo(), nomTable));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			getFenetre().setVisible(false);
			
			DataAuditControleur controller = new DataAuditControleur(this.getCoPanel().getCo(), this.getTraitement());
					
		}
	}  