package jeu;

import java.io.PrintStream;
import java.util.Scanner;

public class UI {

    static Scanner in = new Scanner(System.in);
    static PrintStream out = System.out;

    public String userInterface(Screen screen_){
        boolean boucler = true;
        while (boucler) {
            out.println("Où voulez vous jouer ? ");
            String commande = in.nextLine().trim();
            switch (commande) {
                case "/quit":
                    out.println("-> Bye.");
                    boucler = false;
                    System.exit(0);
                case "/where":
                    screen_.getAllPossiblePlays();
                    break;
                default:
                    if (commande.length() == 2 || commande.length() == 3){
                        return commande;
                    }
                    out.println("-> commande inconnue '" + commande + "'");
                    break;
            }
        }
        return "";
    }

    
}