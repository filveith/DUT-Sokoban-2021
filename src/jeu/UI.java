package jeu;

import java.io.PrintStream;
import java.util.Scanner;

public class UI {

    static Scanner in = new Scanner(System.in);
    static PrintStream out = System.out;
    Screen s;

    public int taillePlateau(){
        int taillePlateauInt=10;
        out.println("Quel taille de plateau voulez-vous ? (entre 5 et 26)");
        while (true){
            String taillePlateau = in.nextLine().trim();
            try {
                taillePlateauInt = Integer.parseInt(taillePlateau);    
            } catch (Exception e) {
                out.println("erreur: la valeur entrée n'est pas valable" + e);
            }

            if (taillePlateauInt <= 26 && taillePlateauInt >= 5) {
                return taillePlateauInt;
            }
            out.println("erreur: la valeur entrée n'est pas valable");
        }
    }

    public void nbJoueur() {
        while(true){
            out.println("Voulez-vous contre l'ordinateur(O) ou contre un humain(H) ? ");
            String adversaire = in.nextLine().trim();
            if (adversaire.contains("H")) {
                out.println("Nom du joueur 1: ");
                Joueur j1 = new Joueur(in.nextLine().trim());
                out.println("Nom du joueur 2: ");
                Joueur j2 = new Joueur(in.nextLine().trim());
            } else if (adversaire.contains("O")) {
                Joueur jIA = new Joueur("IA");
            } else {
                out.println("erreur : la valeur entréé n'est pas valable");
            }
        }

    }

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