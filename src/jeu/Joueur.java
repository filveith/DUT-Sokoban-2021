package jeu;
import java.util.ArrayList;
import java.util.List;

public class Joueur {
    
    String nomJoueur;
    int numJoueur;
    char natureJoueur;
    List<String> coupJouee = new ArrayList<>();

    Joueur(String nomJoueur, int nbJoueur, char natureJoueur) {
        this.nomJoueur = nomJoueur;
        this.numJoueur = nbJoueur;
        this.natureJoueur = natureJoueur;
    }

    public void setCoupJouee(String coupJouee){
        this.coupJouee.add(coupJouee);
    }

    public void setnNomJoueur(String nomJoueur){
        this.nomJoueur = nomJoueur;
    }

    public String getNomJoueur(){
        return this.nomJoueur;
    }

    public void setNumJoueur(int numJoueur){
        this.numJoueur = numJoueur;
    }

    public int getNumJoueur(){
        return this.numJoueur;
    }
}
