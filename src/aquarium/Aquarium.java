package aquarium;

import aquarium.poisson.Carnivore;
import aquarium.poisson.Herbivore;
import aquarium.poisson.Poisson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Aquarium {

    private final List<Algue> algues = new ArrayList<>();
    private final List<Poisson> poissons = new ArrayList<>();
    private final Random random = new Random();

    // region get/set
    public List<Algue> getAlgues() {
        return new ArrayList<>(algues);
    }

    public List<Poisson> getPoissons() {
        return new ArrayList<>(poissons);
    }

    // endregion

    public void ajouter(Vivant vivant){
        if( vivant == null )
            throw new IllegalArgumentException();

        if( vivant instanceof Algue )
            algues.add( (Algue)vivant );
        else if( vivant instanceof Carnivore )
            poissons.add(0, (Poisson)vivant );
        else if( vivant instanceof Herbivore )
            poissons.add( (Poisson) vivant );
    }

    public boolean contientVie(){
        return algues.size() > 0 || poissons.size() > 0;
    }

    public void executerTour(){

        debuterTour();

        lancerRepas();
        System.out.println("Le temps passe");
        afficherContenu();
    }

    private void debuterTour(){
        System.out.println("debut tour");
        for (int i = 0; i < poissons.size(); i++) {
            Poisson poisson = poissons.get(i);
            poisson.vieillir();
            if (poisson.estMort()) {
                poissons.remove(poisson);
                i--;
            }
        }
        for (int i = 0; i < algues.size(); i++) {
            Algue algue = algues.get(i);

            if(algue.estMort())
                algues.remove(algue);

            algue.vieillir();
            Algue nouvelleAlgue = algue.seReproduire();

            if( nouvelleAlgue != null )
                algues.add(nouvelleAlgue);
        }
    }

    private void afficherContenu(){
        System.out.println("L'aquarium contient " + algues.size() + " algues");
        System.out.println("L'aquarium contient les poissons suivants:");
        for (Poisson poisson : poissons) {
            System.out.println( poisson.getClass().getSimpleName() + " - " + poisson.getNom() + " - " + poisson.getSexe() + " - PV:" + poisson.getPv() + " - age: "+ poisson.getAge() );
        }
    }
    private void lancerRepas(){ // TODO factoriser

        int inactifHerb = 0;
        for (int i = 0; i < poissons.size() - inactifHerb ; i++) {

            Poisson poissonCourant = poissons.get(i);
            boolean isHerb = poissonCourant instanceof Herbivore;
            boolean isCarn = poissonCourant instanceof Carnivore;

            if( poissonCourant.getPv() <= 5 ){ // le poisson a faim
                if( isCarn && i < poissons.size() - 1){ // le carnivore a faim et peut manger
                    // un poisson mangé ne peut rien faire <=> on ne peut manger que les poissons qui n'ont encore rien fait
                    int mangeIndex = random.nextInt(i+1,poissons.size());
                    Poisson nourriture = poissons.get(mangeIndex);

                    ((Carnivore) poissonCourant).manger(nourriture);

                    poissons.remove(nourriture);
                    if( nourriture.getPv() > 0 ){
                        // Le poisson mangé ne peut rien faire.
                        // Il est donc mis à un endroit ou il ne sera plus parcouru
                        ajouter(nourriture);
                        if( nourriture instanceof Carnivore ) i++;
                        else if( nourriture instanceof Herbivore ) inactifHerb++;
                    }
                }
                else if( isHerb && algues.size() > 0){ // l'herbivore a faim et peut manger
                    int mangeIndex = random.nextInt(algues.size());
                    Algue nourriture = algues.get(mangeIndex);
                    ((Herbivore) poissonCourant).manger(nourriture);
                    if( nourriture.getPv() <= 0 )
                        algues.remove(nourriture);
                }
                else { // le poisson a faim mais ne peut pas manger
                    Poisson progeniture = tentativeReproduction( i );
                    if( progeniture != null ){
                        // Je pars du principe que la progéniture ne joue pas son premier tour
                        // et ne peut être mangé pendant ce tour
                        ajouter(progeniture);
                        if( progeniture instanceof Carnivore )
                            i++;
                        if( progeniture instanceof Herbivore )
                            inactifHerb++;
                    }
                    else if( isCarn ) {
                        // si le carnivore a faim mais ne trouve ni un partenaire ni à manger
                        // je le deplace vers le début de la liste afin qu'il ait plus de chance au prochain tour
                        poissons.remove(poissonCourant);
                        ajouter(poissonCourant);
                    }
                }
            }
            else { // le poisson n'a pas faim

                Poisson progeniture = tentativeReproduction( i );
                if( progeniture != null ){
                    // Je pars du principe que la progéniture ne joue pas son premier tour
                    // et ne peut être mangé pendant ce tour
                    ajouter(progeniture);
                    if( progeniture instanceof Carnivore )
                        i++;
                    if( progeniture instanceof Herbivore )
                        inactifHerb++;
                }
            }
        }
    }

    private Poisson tentativeReproduction(int poissonIndex){

        if(poissons.size() == 1)
            return null;

        int candidatIndex = random.nextInt(poissons.size() - 1);
        if( candidatIndex >= poissonIndex )
            candidatIndex++;

        Poisson progeniteur = poissons.get(poissonIndex);
        Poisson candidat = poissons.get(candidatIndex);

        return progeniteur.seReproduire(candidat);

    }
}
