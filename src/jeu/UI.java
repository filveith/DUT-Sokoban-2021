package jeu;

import java.io.PrintStream;
import java.util.Scanner;

public class UI {

    static Scanner in = new Scanner(System.in);
    static PrintStream out = System.out;
    Screen s;

    //Only on game start
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

    //choix du nom du joueur 1 et 2
    public String choixNomJoueur(int nbJoueur) {
        while(true){
            out.println("Nom du joueur "+ nbJoueur +" : ");
            String nomJoueur = in.nextLine().trim();
            if (nomJoueur == "") nomJoueur = "Joueur"+nbJoueur;
            return nomJoueur;
        }
    }

    public String userInterface(String nomJoueur){
        boolean boucler = true;
        while (boucler) {
            out.println("Où voulez vous jouer " + nomJoueur + " ? ");
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