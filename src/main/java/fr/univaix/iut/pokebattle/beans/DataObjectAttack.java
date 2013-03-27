package fr.univaix.iut.pokebattle.beans;
public class DataObjectAttack {
    String niveau;
    String nom;
    String puissance;
    String precision;
    String pp;

    public DataObjectAttack(String niveau, String nom, String puissance, String precision, 
                            String pp) {
        this.niveau = niveau;
        this.nom = nom;
        this.puissance = puissance;
        this.precision = precision;
        this.pp = pp;
    }

    public String getNiveau() {
		return niveau;
	}

	public String getNom() {
		return nom;
	}

	public String getPuissance() {
		return puissance;
	}

	public String getPrecision() {
		return precision;
	}

	public String getPp() {
		return pp;
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