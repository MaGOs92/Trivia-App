package Vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import Modele.Colonne;

public class PromotabilityPanel extends JDialog implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Colonne[] colonnes;
	JPanel panneau;
	Box boite;
	JButton skip;
	JButton validate;
	JCheckBox[] cbTab;
	JLabel[] labelTab;
	ArrayList<Colonne> colonnesSelected;
	
	public ArrayList<Colonne> getColonnesSelected() {
		return colonnesSelected;
	}
	public void setColonnesSelected(ArrayList<Colonne> colonnesSelected) {
		this.colonnesSelected = colonnesSelected;
	}
	
	public Colonne[] getColonnes() {
		return colonnes;
	}
	public void setColonnes(Colonne[] colonnes) {
		this.colonnes = colonnes;
	}
	public JPanel getPanneau() {
		return panneau;
	}
	public void setPanneau(JPanel panneau) {
		this.panneau = panneau;
	}
	public Box getBoite() {
		return boite;
	}
	public void setBoite(Box boite) {
		this.boite = boite;
	}
	public JButton getSkip() {
		return skip;
	}
	public void setSkip(JButton skip) {
		this.skip = skip;
	}
	public JButton getValidate() {
		return validate;
	}
	public void setValidate(JButton validate) {
		this.validate = validate;
	}
	public JCheckBox[] getCbTab() {
		return cbTab;
	}
	public void setCbTab(JCheckBox[] cbTab) {
		this.cbTab = cbTab;
	}
	public JLabel[] getLabelTab() {
		return labelTab;
	}
	public void setLabelTab(JLabel[] labelTab) {
		this.labelTab = labelTab;
	}

	
	public PromotabilityPanel(Colonne[] colonnes){
		
		boite = Box.createVerticalBox();
 		setModal(true);
 		setTitle("Promotability graph.");
 		this.setColonnes(colonnes);
 		this.setColonnesSelected(new ArrayList<Colonne>());
 				
 		panneau = new JPanel(new BorderLayout());			
 		JPanel titre = new JPanel(new FlowLayout());
 		titre.add(new JLabel("Select the field which will be included in the Promotability graph"));			
 		panneau.add(titre, BorderLayout.NORTH);
 		
 		JPanel contenu = new JPanel(new SpringLayout());
 		
 		this.setCbTab(new JCheckBox[colonnes.length]);
 		this.setLabelTab(new JLabel[colonnes.length]);
 		
 		for (int i = 0; i < colonnes.length; i++){
 			if (colonnes[i].isStringValues())
 				labelTab[i] = new JLabel(colonnes[i].getNomColonne() + "(" + colonnes[i].getMappingString().getNom() + ")");
 			else
 				labelTab[i] = new JLabel(colonnes[i].getNomColonne() + "(" + colonnes[i].getMappingINT().getNom() + ")");
 			contenu.add(labelTab[i]);
 			cbTab[i] = new JCheckBox();
 			labelTab[i].setLabelFor(cbTab[i]);
 			//cbTab[i].addActionListener(this);
 			contenu.add(cbTab[i]);
 			
 		}
 		
		SpringUtilities.makeCompactGrid(contenu,
                colonnes.length, 2, //rows, cols
                10, 10,        //initX, initY
                10, 10);       //xPad, yPad
 		panneau.add(contenu, BorderLayout.CENTER);
 		
 		
 		
 		JPanel bouton = new JPanel(new FlowLayout());
		skip = new JButton("Skip");
 		validate = new JButton("Validate");
 		bouton.add(validate);
		bouton.add(skip);
		validate.addActionListener(this);
		skip.addActionListener(this);
 		panneau.add(bouton, BorderLayout.SOUTH);
 		
		boite.add(panneau);
		add(boite);
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
 	public void actionPerformed(ActionEvent evt) {
 		
 		Object source = evt.getSource();

 		if (source == this.getSkip()) { 
 			dispose();
 		}
 		else if(source == this.getValidate()){
 			
 			for (int i = 0; i<colonnes.length; i++){
 				if (cbTab[i].isSelected()){
 					this.getColonnesSelected().add(this.getColonnes()[i]);
 				}
 			}
 			
 			dispose();
 		}

 	}

}