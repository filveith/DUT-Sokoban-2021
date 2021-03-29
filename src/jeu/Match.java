package jeu;

import java.io.PrintStream;
import java.util.Scanner;

public class Match {

    static Scanner in = new Scanner(System.in);
    static PrintStream out = System.out;
    Screen s;

    public String userInterface(){
        boolean boucler = true;
        while (boucler) {
            out.println("Où voulez vous jouer ? ");
            String commande = in.nextLine().trim();
            switch (commande) {
                case "/quit":
                    out.println("-> Bye.");
                    boucler = false;
                    System.exit(0);
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