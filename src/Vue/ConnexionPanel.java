package Vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

import Modele.CSVtoSQL;
import Modele.ConnexionJDBC;

public class ConnexionPanel extends JPanel {
	
	
	ConnexionPanel(){
		
		setLayout(new BorderLayout());
		
		String[] labels = {"Server : ", "Port : ", "Database : ", "User : ", "Password : "};
		int numPairs = labels.length;

		//Create and populate the panel.
		JPanel champs = new JPanel(new SpringLayout());
		for (int i = 0; i < numPairs; i++) {
		    JLabel l = new JLabel(labels[i], JLabel.TRAILING);
		    champs.add(l);
		    if (i != numPairs-1){
		    	JTextField textField = new JTextField(20);
		    	l.setLabelFor(textField);
		    	champs.add(textField);
		    }
		    else{
		    	JPasswordField passField = new JPasswordField(20);
		    	l.setLabelFor(passField);
		    	champs.add(passField);
		    }
		}

		//Lay out the panel.
		SpringUtilities.makeCompactGrid(champs,
		                                numPairs, 2, //rows, cols
		                                6, 6,        //initX, initY
		                                6, 6);       //xPad, yPad
		// Ajout des champs
		this.add(champs, BorderLayout.NORTH);
		
		
		// Boutton de connexion
		JPanel panelConnexion = new JPanel();

		JButton connexion = new JButton("Connexion");
		panelConnexion.setLayout(new FlowLayout());
		panelConnexion.add(connexion);
		
		this.add(panelConnexion, BorderLayout.SOUTH);
		
	}
	
	public static void main (String[] args){
		
		// Création de la fenetre
		
		JFrame fenetre = new JFrame ("DataAudit");
		fenetre .setSize(500, 230);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setTitle("DataAudit");
		fenetre.setLocationRelativeTo(null); // Place la fenetre au milieu de l'écran
		
		
		ConnexionPanel DA = new ConnexionPanel();
		
		
		fenetre.getContentPane().add(DA);
		
		fenetre.setVisible(true);
	}
}
/*
class EcouteurConnexion implements ActionListener {
	
	ConnexionJDBC modele;
	
	EcouteurBouton(JLabel affichage, ModeleCalculatriceEntier modele){
		this.affichage = affichage;
		this.modele = modele;
	}
	
	 public void actionPerformed(ActionEvent e) {
		 // Si c'est un bouton, rajouter son nom
         if (e.getSource() instanceof JButton){
        	 JButton boutonSource = (JButton) e.getSource();
        	 modele.toucheEntree(convert(boutonSource.getText()));
        	 
        	 affichage.setText("" + modele.getValeur());
        	 
         }
     } 
*/	 
	