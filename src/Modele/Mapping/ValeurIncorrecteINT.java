package Modele.Mapping;

public class ValeurIncorrecteINT {
	
	private int valeur;
	
	private int quantite;
	
	private String pourcentage;
	
	

	public String getPourcentage() {
		return pourcentage;
	}

	public void setPourcentage(String pourcentage) {
		this.pourcentage = pourcentage;
	}

	public int getValeur() {
		return valeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	public ValeurIncorrecteINT(int valeur, int quantite, int total){
		this.setValeur(valeur);
		this.setQuantite(quantite);
		this.setPourcentage(Math.round(((float) this.getQuantite()/(float) total)*100) + " %");
	}

}
