package Modele;

public class Colonne {

	private String nomColonne;
	
	private String typeDeDonnee;
	
	private int nbCasesRemplies;
	
	private int nbCasesVides;
	
	private float pourcentagesCasesRemplies;
	
	Colonne(String nomColonne, String typeDeDonnee, int nbCasesRemplies, int nbCasesVides, int nbLignesTotales){
		this.setNomColonne(nomColonne);
		this.setTypeDeDonnee(typeDeDonnee);
		this.setNbCasesRemplies(nbCasesRemplies);
		this.setNbCasesVides(nbCasesVides);
		this.setPourcentagesCasesRemplies((float)getNbCasesRemplies()/(float)nbLignesTotales*(float)100);
	}
	
	public String toString(){
		String toString = this.getNomColonne() + " : ";
		toString += "Type de données : " + this.getTypeDeDonnee() + ", ";
		toString += "Nombre de cases remplies : " + this.getNbCasesRemplies() + ", ";
		toString += "Nombre de cases vides : " + this.getNbCasesVides() + ", ";
		toString += "Pourcentage de cases remplies : " + this.getPourcentagesCasesRemplies() + "%";
		
		return toString;
	}

	public String getNomColonne() {
		return nomColonne;
	}

	public void setNomColonne(String nomColonne) {
		this.nomColonne = nomColonne;
	}

	public String getTypeDeDonnee() {
		return typeDeDonnee;
	}

	public void setTypeDeDonnee(String typeDeDonnee) {
		this.typeDeDonnee = typeDeDonnee;
	}

	public int getNbCasesRemplies() {
		return nbCasesRemplies;
	}

	public void setNbCasesRemplies(int nbCasesRemplies) {
		this.nbCasesRemplies = nbCasesRemplies;
	}

	public int getNbCasesVides() {
		return nbCasesVides;
	}

	public void setNbCasesVides(int nbCasesVides) {
		this.nbCasesVides = nbCasesVides;
	}

	public float getPourcentagesCasesRemplies() {
		return pourcentagesCasesRemplies;
	}

	public void setPourcentagesCasesRemplies(float pourcentagesCasesRemplies) {
		this.pourcentagesCasesRemplies = pourcentagesCasesRemplies;
	}
	
	
}
