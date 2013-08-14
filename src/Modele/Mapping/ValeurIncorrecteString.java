package Modele.Mapping;

public class ValeurIncorrecteString {
	
	private String valeur;
	
	private int quantite;
	
	private String pourcentage;
	
	public String getPourcentage() {
		return pourcentage;
	}

	public void setPourcentage(String pourcentage) {
		this.pourcentage = pourcentage;
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	public ValeurIncorrecteString(String valeur, int quantite, int total){
		this.setValeur(valeur);
		this.setQuantite(quantite);
		this.setPourcentage(((float) this.getQuantite()/(float) total)*100 + " %");
	}

}
