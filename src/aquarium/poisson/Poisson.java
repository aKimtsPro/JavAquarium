package aquarium.poisson;

import aquarium.Vivant;

public abstract class Poisson extends Vivant {

    public enum Sexe {
        MALE,
        FEMELLE;

        public Sexe getOppose() {
            return this == MALE ? FEMELLE : MALE;
        }
    }

    private String nom;
    private Sexe sexe;
    private int nbrDescendants = 0;

    public Poisson(String nom, Sexe sexe) {
        this.nom = nom;
        this.sexe = sexe;
    }

    public Poisson(String nom, Sexe sexe, int age) {
        super(age);
        this.nom = nom;
        this.sexe = sexe;
    }

    public int getNbrDescendants() {
        return nbrDescendants;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    @Override
    public void estMange() {
        setPv( getPv() - 4 );
    }

    @Override
    public void vieillir() {
        super.vieillir();
        setPv( getPv() - 1 );
    }

    public Poisson seReproduire(Poisson candidat){
        if( this.getClass() == candidat.getClass() && this.getSexe() != candidat.getSexe() ){
            nbrDescendants++;
            return creerEnfant();
        }

        return null;
    }
    protected abstract Poisson creerEnfant();

}
