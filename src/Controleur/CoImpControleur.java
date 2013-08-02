package Controleur;


import java.awt.Toolkit;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.UIManager;

import Modele.DataAuditModele;
import Modele.ImportationModele;
import Vue.ConnexionPanel;
import Vue.ImportationPanel;
import Vue.LoadingPanel;

public class CoImpControleur {

	   ConnexionPanel coPanel;  
	   ImportationPanel impPanel;
	   LoadingPanel loaPanel;
	   DataAuditModele traitement;
	   JFrame fenetre;
	   
	   
	   
	public LoadingPanel getLoaPanel() {
		return loaPanel;
	}
	public void setLoaPanel(LoadingPanel loaPanel) {
		this.loaPanel = loaPanel;
	}
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
	
	 public static void setBestLookAndFeelAvailable(){
		   String system_lf = UIManager.getSystemLookAndFeelClassName().toLowerCase();
		   if(system_lf.contains("metal")){
		       try {
		           UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		       }catch (Exception e) {}
		   }else{
		       try {
		           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		       }catch (Exception e) {}
		   }
		 }
	
	public JFrame creerFenetre()
	{	
		// Cr�ation de la fenetre
		
		setBestLookAndFeelAvailable();
	     
		JFrame fenetre = new JFrame ("Trivia DataDiscovery");
		fenetre.setSize(500, 230);
		fenetre.setResizable(false);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setTitle("Trivia DataDiscovery");
		fenetre.setIconImage(Toolkit.getDefaultToolkit().getImage("Img\\icone.png")); 
		fenetre.setLocationRelativeTo(null); // Place la fenetre au milieu de l'�cran
	
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
	      setLoaPanel(new LoadingPanel());
	      
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
		   
		    getFenetre().getContentPane().removeAll();
		   
		    getFenetre().getContentPane().add(getLoaPanel());
			 
			getFenetre().setVisible(true);
		   	
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
			
			DataAuditControleur controller = new DataAuditControleur(this.getCoPanel().getCo(), this.getTraitement(), this.getImpPanel().getPathFichier());
					
		}
	}  