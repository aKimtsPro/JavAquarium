package aquarium;

public class Algue extends Vivant {

    public Algue() {
    }

    public Algue(int pv) {
        setPv(pv);
    }

    @Override
    public void estMange() {
        setPv( getPv() - 2 );
    }

    @Override
    public void declenchementTour() {
        super.declenchementTour();
        setPv( getPv() + 1 );
    }

    public Algue seReproduire(){
        if(getPv() >= 10){
            int pv = getPv() / 2;
            setPv(pv);
            return new Algue(pv);
        }

        return null;
    }
}
