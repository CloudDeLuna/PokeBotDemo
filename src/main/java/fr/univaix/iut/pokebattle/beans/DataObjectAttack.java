package fr.univaix.iut.pokebattle.beans;
public class DataObjectAttack {
    private String niveau;
    private String nom;
    private String puissance;
    private String precision;
	private String pp;
    
	public String getNiveau() {
		return niveau;
	}

	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPuissance() {
		return puissance;
	}

	public void setPuissance(String puissance) {
		this.puissance = puissance;
	}

	public String getPrecision() {
		return precision;
	}

	public void setPrecision(String precision) {
		this.precision = precision;
	}

	public String getPp() {
		return pp;
	}

	public void setPp(String pp) {
		this.pp = pp;
	}

	@Override
    public String toString() {
        return "\nDataObjectAttack{" +
                "niveau='" + niveau + '\'' +
                ", nom='" + nom + '\'' +
                ", puissance=" + puissance +
                ", precision=" + precision +
                ", pp=" + pp +
                "}";
    }
}