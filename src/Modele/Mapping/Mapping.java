package Modele.Mapping;

import Modele.Colonne;

import com.mysql.jdbc.Connection;

public abstract class Mapping {
	
	private int id;

	private String nom;
	
	private Colonne colonne;
	
	private String nomTable;
	
	private Connection co;
	
	private Classe classe;

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Colonne getColonne() {
		return colonne;
	}

	public void setColonne(Colonne colonne) {
		this.colonne = colonne;
	}

	public String getNomTable() {
		return nomTable;
	}

	public void setNomTable(String nomTable) {
		this.nomTable = nomTable;
	}

	public Connection getCo() {
		return co;
	}

	public void setCo(Connection co) {
		this.co = co;
	}
	
	public Mapping(){
		this.setId(0);
		this.setNom("None");
		this.setClasse(new Classe(0, "None"));
	}
	
	public Mapping(int id, String nom, Classe classe, String nomTable, Connection co){
		this.setId(id);
		this.setNom(nom);
		this.setClasse(classe);
		this.setNomTable(nomTable);
		this.setCo(co);
	}
	
	public String toString() {
		return this.getNom();
	}
	
	
}


