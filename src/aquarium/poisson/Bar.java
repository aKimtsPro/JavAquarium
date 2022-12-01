package aquarium.poisson;

public class Bar extends Herbivore implements HermaAge{
    public Bar(String nom, Sexe sexe) {
        super(nom, sexe);
    }

    public Bar(String nom, Sexe sexe, int age) {
        super(nom, sexe, age);
    }

    @Override
    public Poisson creerEnfant() {
        int rdmInt = (int) (Math.random() * 2);
        return new Bar(getNom()+" "+ getNbrDescendants(), rdmInt == 1 ? Sexe.MALE : Sexe.FEMELLE );
    }

    @Override
    public void vieillir() {
        super.vieillir();
        if( getAge() == 10 )
            setSexe( getSexe().getOppose() );
    }
}
