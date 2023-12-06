package tn.esprit.com.foyer.entities;

public class TypeBlocPourcentage {
    private Etat etat;
    private float pourcentage;



    public TypeBlocPourcentage(Etat etat, float pourcentage) {
        this.etat = etat;
        this.pourcentage = pourcentage;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public float getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(float pourcentage) {
        this.pourcentage = pourcentage;
    }

}
