package aquarium.poisson;

import aquarium.Algue;

public abstract class Herbivore extends Poisson {
    public Herbivore(String nom, Sexe sexe) {
        super(nom, sexe);
    }

    public Herbivore(String nom, Sexe sexe, int age) {
        super(nom, sexe, age);
    }

    public void manger(Algue algue){
        System.out.println(getNom() + " mange une algue");
        algue.estMange();
        this.setPv( getPv() + 3 );
    }
}
