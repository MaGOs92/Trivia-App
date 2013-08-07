package Vue;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Modele.Colonne;


public class SaisieClasse extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Colonne[] colonnes;
	JPanel[] panneau;
	Box boite;
	JButton valider;
	JButton skip;
	JRadioButton indicator;
	JRadioButton date;
	JRadioButton text;
	JRadioButton identifier;
	JRadioButton code;
	JRadioButton quantity;
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


	public JRadioButton getIndicator() {
		return indicator;
	}


	public void setIndicator(JRadioButton indicator) {
		this.indicator = indicator;
	}


	public JRadioButton getDate() {
		return date;
	}


	public void setDate(JRadioButton date) {
		this.date = date;
	}


	public JRadioButton getText() {
		return text;
	}


	public void setText(JRadioButton text) {
		this.text = text;
	}


	public JRadioButton getIdentifier() {
		return identifier;
	}


	public void setIdentifier(JRadioButton identifier) {
		this.identifier = identifier;
	}


	public JRadioButton getCode() {
		return code;
	}


	public void setCode(JRadioButton code) {
		this.code = code;
	}


	public JRadioButton getQuantity() {
		return quantity;
	}


	public void setQuantity(JRadioButton quantity) {
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


	public JButton getValider() {
		return valider;
	}


	public void setValider(JButton valider) {
		this.valider = valider;
	}
	
	
	public SaisieClasse(){		
 		
 		boite = Box.createVerticalBox();
 		setModal(true);
 		setTitle("Set classes");
 		
			JPanel panneau = new JPanel(new BorderLayout());
			panneau.add(new JLabel("SISI"), BorderLayout.NORTH);
			
			indicator = new JRadioButton("Indicator");
			identifier = new JRadioButton("Identifier");
			date = new JRadioButton("Date");
			quantity = new JRadioButton("Quantity");
			text = new JRadioButton("Text");
			code = new JRadioButton("Code");
			
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
			
			panneau.add(panneauRadio, BorderLayout.CENTER);
			
			JPanel panneauBoutons = new JPanel(new FlowLayout());
 			valider = new JButton("Validate");
 			skip = new JButton("Skip");
			panneauBoutons.add(valider);
			panneauBoutons.add(skip);
			
			panneau.add(panneauBoutons, BorderLayout.SOUTH);
			
			valider.addActionListener(this);
			skip.addActionListener(this);
			
			boite.add(panneau);
			add(boite) ;
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
			
	}



	public SaisieClasse(Colonne[] colonnes) {

 		panneau = new JPanel[colonnes.length];
	
 		boite = Box.createVerticalBox();
 		setModal(true);
 		setTitle("Set classes");
 		
 		for (int i = 0; i < colonnes.length; i ++){
 			
 			panneau[i] = new JPanel(new BorderLayout());
 			
 			JPanel titre = new JPanel(new FlowLayout());
 			titre.add(new JLabel(colonnes[i].getNomColonne()));
 			
 			panneau[i].add(titre, BorderLayout.NORTH);
 			
 			indicator = new JRadioButton("Indicator");
 			identifier = new JRadioButton("Identifier");
 			date = new JRadioButton("Date");
 			quantity = new JRadioButton("Quantity");
 			text = new JRadioButton("Text");
 			code = new JRadioButton("Code");
 			
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
 			
 			panneau[i].add(panneauRadio, BorderLayout.CENTER);
 			
 			JPanel panneauBoutons = new JPanel(new FlowLayout());
 			valider = new JButton("Validate");
 			skip = new JButton("Skip");
 			
 			panneauBoutons.add(skip);
 			panneauBoutons.add(valider);
 			
 			panneau[i].add(panneauBoutons, BorderLayout.SOUTH);
 			
 			valider.addActionListener(this);
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
 		if (source == valider) {
 			System.out.println("");
 			this.setCompteur(this.getCompteur() + 1);
 			if (this.getCompteur() == getColonnes().length){
 				this.getColonnes()[this.getCompteur()].setClasse(this.getChoixRadio().getSelection().toString()); 				
 				dispose();
 			}
 			else{ 			
 				this.getColonnes()[this.getCompteur()].setClasse(this.getChoixRadio().getSelection().toString());
 				this.getBoite().removeAll();
 				this.getBoite().add(this.getPanneau()[this.getCompteur()]);
 				pack();
 				setLocationRelativeTo(null);
 				setVisible(true);
 			}

 		}
 		else if (source == skip) { 
 			dispose();
 		}
 	}
 	
 	public static void main(String[] args){
 		
 		SaisieClasse test = new SaisieClasse();
 		
 	}
 	
}