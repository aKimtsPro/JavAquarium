package aquarium.poisson;

public class Merou extends Carnivore implements HermaAge {
    public Merou(String nom, Sexe sexe) {
        super(nom, sexe);
    }

    public Merou(String nom, Sexe sexe, int age) {
        super(nom, sexe, age);
    }

    @Override
    public Poisson creerEnfant() {
        int rdmInt = (int) (Math.random() * 2);
        return new Merou(getNom()+" "+ getNbrDescendants(), rdmInt == 1 ? Sexe.MALE : Sexe.FEMELLE );
    }

    @Override
    public void declenchementTour() {
        super.declenchementTour();
        if( getAge() == 10 )
            setSexe( getSexe().getOppose() );
    }
}
