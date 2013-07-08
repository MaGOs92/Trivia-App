package Modele;

public class Colonne {

	private String nomColonne;
	
	private String typeDeDonnee;
	
	private int nbCasesRemplies;
	
	private int nbCasesVides;
	
	private float pourcentagesCasesRemplies;
	
	private String[] valeursFrequentes;
	
	Colonne(String nomColonne, String typeDeDonnee, int nbCasesRemplies, int nbCasesVides, int nbLignesTotales, String[] valeursFrequentes){
		this.setNomColonne(nomColonne);
		this.setTypeDeDonnee(typeDeDonnee);
		this.setNbCasesRemplies(nbCasesRemplies);
		this.setNbCasesVides(nbCasesVides);
		this.setPourcentagesCasesRemplies((float)getNbCasesRemplies()/(float)nbLignesTotales*(float)100);
		this.setValeursFrequentes(valeursFrequentes);
	}
	
	public String toString(){
		String toString = this.getNomColonne() + " : ";
		toString += "Type de données : " + this.getTypeDeDonnee() + ", ";
		toString += "Nombre de cases remplies : " + this.getNbCasesRemplies() + ", ";
		toString += "Nombre de cases vides : " + this.getNbCasesVides() + ", ";
		toString += "Pourcentage de cases remplies : " + this.getPourcentagesCasesRemplies() + "%";
		
		return toString;
	}
	
	public String afficherValeursFrequentes(){
		
		String valeursFrequentes = "";
		
		for (int i = 0; i < 3; i ++){
			valeursFrequentes += this.getValeursFrequentes()[i] + ";";
		}
		
		
		return valeursFrequentes;
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

	public String[] getValeursFrequentes() {
		return valeursFrequentes;
	}

	public void setValeursFrequentes(String[] valeursFrequentes) {
		this.valeursFrequentes = valeursFrequentes;
	}
	
}
