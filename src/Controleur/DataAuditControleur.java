package Controleur;

import javax.swing.JFrame;

import com.mysql.jdbc.Connection;

import Modele.DataAuditModele;
import Vue.DataAuditPanel;

public class DataAuditControleur {
	
	Connection co;
	
	JFrame fenetre;
	DataAuditPanel DAPanel;
	DataAuditModele DAModele;
	String pathFichier;
	
	public String getPathFichier() {
		return pathFichier;
	}

	public void setPathFichier(String pathFichier) {
		this.pathFichier = pathFichier;
	}

	public Connection getCo() {
		return co;
	}

	public void setCo(Connection co) {
		this.co = co;
	}

	public JFrame getFenetre() {
		return fenetre;
	}

	public void setFenetre(JFrame fenetre) {
		this.fenetre = fenetre;
	}

	public DataAuditPanel getDAPanel() {
		return DAPanel;
	}

	public void setDAPanel(DataAuditPanel dAPanel) {
		DAPanel = dAPanel;
	}

	public DataAuditModele getDAModele() {
		return DAModele;
	}

	public void setDAModele(DataAuditModele dAModele) {
		DAModele = dAModele;
	}

	DataAuditControleur(Connection co, DataAuditModele DAModele, String pathFichier){
		
		this.setCo(co);
		
		this.setDAModele(DAModele);
		
		this.setPathFichier(pathFichier);
		
		this.setFenetre(this.creerFenetre());
		
		this.setDAPanel(new DataAuditPanel(this));
		
		getFenetre().add(getDAPanel());
		
	}
	
	public JFrame creerFenetre()
	{	
		// Création de la fenetre
		
		JFrame fenetre = new JFrame ("DataAudit");
		fenetre.setSize(800, 600);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setTitle("DataAudit");
		fenetre.setLocationRelativeTo(null); // Place la fenetre au milieu de l'écran
	
		fenetre.setVisible(true);
		
		return fenetre;

	}
}
