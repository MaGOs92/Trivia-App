package Modele;

public class ValeurRetiree {
	
	String valeur;
	String pourcentage;
	int count;
	
	public String getValeur() {
		return valeur;
	}
	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
	public String getPourcentage() {
		return pourcentage;
	}
	public void setPourcentage(String pourcentage) {
		this.pourcentage = pourcentage;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public ValeurRetiree(String valeur, int count, int total){
		this.setValeur(valeur);
		this.setCount(count);
		this.setPourcentage(Math.round(((float) this.getCount()/(float) total)*100) + " %");
	}
	
	public ValeurRetiree(String valeur){
		this.setValeur(valeur);
	}

}
