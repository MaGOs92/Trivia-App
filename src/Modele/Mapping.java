package Modele;

public class Mapping {
	
	private int id;

	private String nom;
	
	public Mapping(){
		
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

	


	@Override
	public String toString() {
		return getNom();
	}


	Mapping(int id, String nom){
		this.setId(id);
		this.setNom(nom);
	}

	
}
