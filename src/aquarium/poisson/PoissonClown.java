package aquarium.poisson;

import aquarium.Vivant;

public class PoissonClown extends Carnivore implements HermaOpportuniste{
    public PoissonClown(String nom, Sexe sexe) {
        super(nom, sexe);
    }

    public PoissonClown(String nom, Sexe sexe, int age) {
        super(nom, sexe, age);
    }

    @Override
    public Poisson creerEnfant() {
        int rdmInt = (int) (Math.random() * 2);
        return new PoissonClown(getNom()+" "+ getNbrDescendants(), rdmInt == 1 ? Sexe.MALE : Sexe.FEMELLE );
    }

    @Override
    public Poisson seReproduire(Poisson candidat) {

        if( getSexe() == candidat.getSexe() )
            setSexe( getSexe().getOppose() );

        return super.seReproduire(candidat);
    }
}
