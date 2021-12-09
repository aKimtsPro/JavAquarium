package aquarium.poisson;

public class Thon extends Carnivore implements Monosex{
    public Thon(String nom, Sexe sexe) {
        super(nom, sexe);
    }

    public Thon(String nom, Sexe sexe, int age) {
        super(nom, sexe, age);
    }

    @Override
    public Poisson creerEnfant() {
        int rdmInt = (int) (Math.random() * 2);
        return new Thon(getNom()+" "+ getNbrDescendants(), rdmInt == 1 ? Sexe.MALE : Sexe.FEMELLE );
    }
}
