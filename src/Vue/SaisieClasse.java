package Vue;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Modele.Colonne;
import Modele.Mapping.Classe;


public class SaisieClasse extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	Colonne[] colonnes;
	JPanel[] panneau;
	Box boite;
	JButton skip;
	JRadioButtonClasse indicator;
	JRadioButtonClasse date;
	JRadioButtonClasse text;
	JRadioButtonClasse identifier;
	JRadioButtonClasse code;
	JRadioButtonClasse quantity;
	ButtonGroup choixRadio;
	int compteur;

	
	

	public Box getBoite() {
		return boite;
	}


	public void setBoite(Box boite) {
		this.boite = boite;
	}


	public JPanel[] getPanneau() {
		return panneau;
	}


	public void setPanneau(JPanel[] panneau) {
		this.panneau = panneau;
	}


	public int getCompteur() {
		return compteur;
	}


	public void setCompteur(int compteur) {
		this.compteur = compteur;
	}


	public JButton getSkip() {
		return skip;
	}


	public void setSkip(JButton skip) {
		this.skip = skip;
	}



	public JRadioButtonClasse getIndicator() {
		return indicator;
	}


	public void setIndicator(JRadioButtonClasse indicator) {
		this.indicator = indicator;
	}


	public JRadioButtonClasse getDate() {
		return date;
	}


	public void setDate(JRadioButtonClasse date) {
		this.date = date;
	}


	public JRadioButtonClasse getText() {
		return text;
	}


	public void setText(JRadioButtonClasse text) {
		this.text = text;
	}


	public JRadioButtonClasse getIdentifier() {
		return identifier;
	}


	public void setIdentifier(JRadioButtonClasse identifier) {
		this.identifier = identifier;
	}


	public JRadioButtonClasse getCode() {
		return code;
	}


	public void setCode(JRadioButtonClasse code) {
		this.code = code;
	}


	public JRadioButtonClasse getQuantity() {
		return quantity;
	}


	public void setQuantity(JRadioButtonClasse quantity) {
		this.quantity = quantity;
	}


	public ButtonGroup getChoixRadio() {
		return choixRadio;
	}


	public void setChoixRadio(ButtonGroup choixRadio) {
		this.choixRadio = choixRadio;
	}


	public Colonne[] getColonnes() {
		return colonnes;
	}


	public void setColonnes(Colonne[] colonnes) {
		this.colonnes = colonnes;
	}
	

	public SaisieClasse(Colonne[] colonnes) {

 		panneau = new JPanel[colonnes.length];
	
 		boite = Box.createVerticalBox();
 		setModal(true);
 		setTitle("Set classes");
 		this.setCompteur(0);
 		this.setColonnes(colonnes);
 		
 		for (int i = 0; i < colonnes.length; i ++){
 			
 			panneau[i] = new JPanel(new BorderLayout());
 			
 			JPanel titre = new JPanel(new FlowLayout());
 			titre.add(new JLabel(colonnes[i].getNomColonne()));
 			
 			panneau[i].add(titre, BorderLayout.NORTH);
 			
 			indicator = new JRadioButtonClasse("Indicator", new Classe(2, "Indicator"));
 			identifier = new JRadioButtonClasse("Identifier", new Classe(1, "Identifier"));
 			date = new JRadioButtonClasse("Date", new Classe(4, "Date"));
 			quantity = new JRadioButtonClasse("Quantity", new Classe(3, "Quantity"));
 			text = new JRadioButtonClasse("Text", new Classe(5, "Text"));
 			code = new JRadioButtonClasse("Code", new Classe(6, "Code"));
 			
 			JPanel panneauCentre = new JPanel(new BorderLayout());
 			
 			JPanel panneauRadio = new JPanel(new FlowLayout());
 			panneauRadio.add(indicator);
 			panneauRadio.add(identifier);
 			panneauRadio.add(date);
 			panneauRadio.add(quantity);
 			panneauRadio.add(text);
 			panneauRadio.add(code);
 			 			
 			choixRadio = new ButtonGroup();
 			choixRadio.add(indicator);
 			choixRadio.add(identifier);
 			choixRadio.add(code);
 			choixRadio.add(date);
 			choixRadio.add(text);
 			choixRadio.add(quantity);
 			
 			indicator.addActionListener(this);
 			identifier.addActionListener(this);
 			date.addActionListener(this);
 			quantity.addActionListener(this);
 			text.addActionListener(this);
 			code.addActionListener(this);
 			
 			panneauCentre.add(panneauRadio, BorderLayout.NORTH);
 			
 			
 	        String[] entetes = {"Value", "Count", "Pourcentage"};
 	        
 	        String[][] donnees = new String[3][3];
 	       	        
 	        int l = 0; 	        
 	        for (int j = 0; j < 3; j++){
 	        	for (int k = 0; k < 3; k ++){
 	        		donnees[j][k] = colonnes[i].getValeursFrequentes()[l];
 	        		l ++;
 	        	}
 	        }
 	        
 	        JTable tableau = new JTable(donnees, entetes);
 	        
 	       panneauCentre.add(tableau.getTableHeader(), BorderLayout.CENTER);
 	       panneauCentre.add(tableau, BorderLayout.SOUTH);
 			
 			panneau[i].add(panneauCentre, BorderLayout.CENTER);
 			
 			JPanel panneauBoutons = new JPanel(new FlowLayout());
 			skip = new JButton("Skip");
 			
 			panneauBoutons.add(skip);
 			
 			panneau[i].add(panneauBoutons, BorderLayout.SOUTH);
 			
 			skip.addActionListener(this);

 			
 		}
 		
		boite.add(panneau[0]);
		add(boite);
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
 	}
 
 	
 	public void actionPerformed(ActionEvent evt) {
 		Object source = evt.getSource();
 		
 		if (source == skip) { 
 			dispose();
 		}
 				
 		else{ 		
 			this.setCompteur(this.getCompteur() + 1);
 			if (this.getCompteur() == this.getColonnes().length){
 				
 				if (source == this.getIdentifier()) {
 					this.getColonnes()[this.getCompteur()-1].setClasse(this.getIdentifier().getClasse());
 					System.out.println(this.getColonnes()[this.getCompteur()-1].getNomColonne());
 				}
 				else if (source == this.getCode()) {
 					this.getColonnes()[this.getCompteur()-1].setClasse(this.getCode().getClasse());
 				}
 				else if (source == this.getDate()) {
 					this.getColonnes()[this.getCompteur()-1].setClasse(this.getDate().getClasse());
 				}
 				else if (source == this.getIndicator()) {
 					this.getColonnes()[this.getCompteur()-1].setClasse(this.getIndicator().getClasse());
 				}
 				else if (source == this.getQuantity()) {
 					this.getColonnes()[this.getCompteur()-1].setClasse(this.getQuantity().getClasse());
 				}
 				else if (source == this.getText()) {
 					this.getColonnes()[this.getCompteur()-1].setClasse(this.getText().getClasse());
 				}
 				dispose();
 			}
 			else{	
 				System.out.println(this.getColonnes()[this.getCompteur()-1].getNomColonne());
 	 				if (source == this.getIdentifier()) {
 	 					this.getColonnes()[this.getCompteur()-1].setClasse(this.getIdentifier().getClasse());
 	 				}
 	 				else if (source == this.getCode()) {
 	 					this.getColonnes()[this.getCompteur()-1].setClasse(this.getCode().getClasse());
 	 				}
 	 				else if (source == this.getDate()) {
 	 					this.getColonnes()[this.getCompteur()-1].setClasse(this.getDate().getClasse());
 	 				}
 	 				else if (source == this.getIndicator()) {
 	 					this.getColonnes()[this.getCompteur()-1].setClasse(this.getIndicator().getClasse());
 	 				}
 	 				else if (source == this.getQuantity()) {
 	 					this.getColonnes()[this.getCompteur()-1].setClasse(this.getQuantity().getClasse());
 	 				}
 	 				else if (source == this.getText()) {
 	 					this.getColonnes()[this.getCompteur()-1].setClasse(this.getText().getClasse());
 	 				}
 				this.getBoite().removeAll();
 				this.getBoite().add(this.getPanneau()[this.getCompteur()]);
 				pack();
 				setLocationRelativeTo(null);
 				setVisible(true);
 			}

 		}
 	}
 	
}