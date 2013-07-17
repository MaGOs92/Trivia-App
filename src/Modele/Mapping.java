package Modele;

public class Mapping {
	
	private int id;

	private String nom;
	
	private String regex;
	
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}
	

	public String getRegex() {
		return regex;
	}


	public void setRegex(String regex) {
		this.regex = regex;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}

	


	@Override
	public String toString() {
		return getNom();
	}


	Mapping(int id, String nom, String regex){
		this.setId(id);
		this.setNom(nom);
		this.setRegex(regex);
	}

	
}
