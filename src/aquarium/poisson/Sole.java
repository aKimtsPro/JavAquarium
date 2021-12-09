package aquarium.poisson;

import aquarium.Algue;
import aquarium.Vivant;

public class Sole extends Herbivore implements HermaOpportuniste{
    public Sole(String nom, Sexe sexe) {
        super(nom, sexe);
    }

    public Sole(String nom, Sexe sexe, int age) {
        super(nom, sexe, age);
    }

    @Override
    public Poisson creerEnfant() {
        int rdmInt = (int) (Math.random() * 2);
        return new Sole(getNom()+" "+ getNbrDescendants(), rdmInt == 1 ? Sexe.MALE : Sexe.FEMELLE );
    }

    @Override
    public Poisson seReproduire(Poisson candidat) {

        if( getSexe() == candidat.getSexe() )
            setSexe( getSexe().getOppose() );

        return super.seReproduire(candidat);
    }
}
