package aquarium;

import aquarium.poisson.*;

import java.util.Scanner;

public class Menu {

    private final Aquarium aquarium = new Aquarium();
    private final Scanner sc = new Scanner(System.in);

    public void start(){
        aquarium.ajouter(new Algue());

        aquarium.ajouter(new Bar("barette", Poisson.Sexe.FEMELLE));
        aquarium.ajouter(new Sole("S'Olé", Poisson.Sexe.MALE));
        aquarium.ajouter(new Carpe("Carpette", Poisson.Sexe.FEMELLE));
//        aquarium.ajouter(new Bar("bar", Poisson.Sexe.MALE, 18));
//        aquarium.ajouter(new Sole("Soleil", Poisson.Sexe.MALE));
//        aquarium.ajouter(new Carpe("Carpool", Poisson.Sexe.MALE));

        aquarium.ajouter(new Merou("Merlou", Poisson.Sexe.MALE, 9));
        aquarium.ajouter(new Thon("Tonton", Poisson.Sexe.MALE, 2));
//        aquarium.ajouter(new Thon("Thon Alite", Poisson.Sexe.FEMELLE, 10));
//        aquarium.ajouter(new Merou("merou", Poisson.Sexe.FEMELLE));
        aquarium.ajouter(new PoissonClown("Clownie", Poisson.Sexe.MALE, 1));
//        aquarium.ajouter(new PoissonClown("Clywne", Poisson.Sexe.MALE, 1));

        while (aquarium.contientVie()) {
            aquarium.executerTour();
            System.out.println("\n-- continuer ? --");
            sc.nextLine();
        }
    }
}
