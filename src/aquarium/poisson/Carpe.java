package aquarium.poisson;

import aquarium.Algue;

public class Carpe extends Herbivore implements Monosex{
    public Carpe(String nom, Sexe sexe) {
        super(nom, sexe);
    }

    public Carpe(String nom, Sexe sexe, int age) {
        super(nom, sexe, age);
    }

    @Override
    public Poisson creerEnfant() {
        int rdmInt = (int) (Math.random() * 2);
        return new Carpe(getNom()+" "+ getNbrDescendants(), rdmInt == 1 ? Sexe.MALE : Sexe.FEMELLE );
    }
}
