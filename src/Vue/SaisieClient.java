package Vue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Modele.DataAuditModele;


public class SaisieClient extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DataAuditModele modele;
	JButton valider = new JButton("Validate");
	JButton annuler = new JButton("Cancel");
	JTextField clientNom = new JTextField(50);
	
 	public DataAuditModele getModele() {
		return modele;
	}


	public void setModele(DataAuditModele modele) {
		this.modele = modele;
	}


	public JButton getValider() {
		return valider;
	}


	public void setValider(JButton valider) {
		this.valider = valider;
	}


	public JButton getAnnuler() {
		return annuler;
	}


	public void setAnnuler(JButton annuler) {
		this.annuler = annuler;
	}


	public JTextField getClientNom() {
		return clientNom;
	}


	public void setClientNom(JTextField clientNom) {
		this.clientNom = clientNom;
	}


	public SaisieClient(DataAuditModele modele) {
 		this.modele = modele;
 		JPanel panneau ;
	
 		Box boite = Box.createVerticalBox();
 		setModal(true);
 		setTitle("Customer's name");
 		panneau = new JPanel();
 		panneau.add(new JLabel("Please insert the customer's name below : "));
 		panneau.add(clientNom);
 		boite.add(panneau);
	
 		panneau = new JPanel();
		boite.add(panneau);
	
		panneau = new JPanel();
		panneau.add(valider);
		panneau.add(annuler);
		boite.add(panneau);
	
		add(boite) ;
	
		valider.addActionListener(this);
		annuler.addActionListener(this);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
 	}
 
 	
 	public void actionPerformed(ActionEvent evt) {
 		Object source = evt.getSource();
 		if (source == valider) {
 			this.getModele().setNomClient(this.getClientNom().getText());
 			dispose();
 		}
 		else if (source == annuler) { 
 			dispose();
 		}
 	}
}