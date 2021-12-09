package aquarium.poisson;

public abstract class Carnivore extends Poisson {
    public Carnivore(String nom, Sexe sexe) {
        super(nom, sexe);
    }

    public Carnivore(String nom, Sexe sexe, int age) {
        super(nom, sexe, age);
    }

    public void manger(Poisson poisson){
        System.out.println(getNom() + " mange un poisson");
        poisson.estMange();
        this.setPv( getPv() + 5 );
    }
}
