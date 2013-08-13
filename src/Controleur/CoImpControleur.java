package Controleur;


import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import Modele.DataAuditModele;
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
		// Création de la fenetre

		setBestLookAndFeelAvailable();

		JFrame fenetre = new JFrame ("Trivia DataDiscovery");
		fenetre.setSize(500, 230);
		fenetre.setResizable(false);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setTitle("Trivia DataDiscovery");
		fenetre.setIconImage(Toolkit.getDefaultToolkit().getImage("Img\\icone.png")); 
		fenetre.setLocationRelativeTo(null); // Place la fenetre au milieu de l'écran

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


	   public void lancerGUI(){

		   getLoaPanel().setVisible(false);

		   DataAuditControleur controller = new DataAuditControleur(this, this.getCoPanel().getCo(), this.getTraitement(), this.getImpPanel().getPathFichier());

	   }

	   public void lancerDataAudit() throws IOException{

		   getFenetre().setVisible(false);

		   getLoaPanel().setVisible(true);

		   Chargement sw = new Chargement(this);

			      //On écoute le changement de valeur pour la propriété
		   sw.addPropertyChangeListener(new PropertyChangeListener(){
			        //Méthode de l'interface
			public void propertyChange(PropertyChangeEvent event) {
			          //On vérifie tout de même le nom de la propriété
			      if("progress".equals(event.getPropertyName())){
			    	  
			            if(SwingUtilities.isEventDispatchThread())
			              //System.out.println("Dans le listener donc dans l'EDT ! ");
			            //On récupère sa nouvelle valeur
			            	System.out.println("Traitement en progrès");
			          }            
			        }         
			});
			      //On lance le SwingWorker
		   sw.execute();
		   
		}
	   
}
