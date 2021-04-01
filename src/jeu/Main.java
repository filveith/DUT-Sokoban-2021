package jeu;
import java.io.PrintStream;
import java.util.Scanner;
public class Main {

    static Scanner in = new Scanner(System.in);
    static char x;
    static int y;
    static boolean boot = true;
    static char nature = 'X';
    static Joueur jH = new Joueur("Joueur1",0,'X');
    static Joueur j2 = new Joueur("Joueur2",1,'O');
    static int playersTurn = 1;

    public static void main(String[] args) {
        UI m = new UI();    
        Screen s = new Screen(bootMain());  //max 26 because the alphabet is 26 char long :)
       
        s.clear();
        s.display();

        

        
        System.out.println("* Menu *");
        while(true){
            Game.getAllPlayableMoves(s);
            String userInput = m.userInterface();      
            
            if (userInput.length() == 2){
                x = userInput.charAt(0);
                y = Integer.parseInt(userInput.substring(1));
            } else if (userInput.length() == 3){
                x = userInput.charAt(0);
                y = Integer.parseInt(userInput.substring(1,3));
            }

            if(s.checkUserInput(x, y)){
                s.display();
                if (playersTurn == 1) {
                    jH.setCoupJouee(userInput);
                    nature = jH.natureJoueur;
                    playersTurn++;
                } else if (playersTurn == 2) {
                    j2.setCoupJouee(userInput);
                    nature = j2.natureJoueur;
                    playersTurn--;
                }
                System.out.println("x = " + x + " y = "+ y);
                s.setPoint(x, y, nature);
                s.display();
            } else {
                System.out.println("Erreur: Le point sélectionné n'est pas disponible");
            }
        }
    }

    public static int bootMain() {
        UI m = new UI();
        if (boot) {
            boot = false;
            nbJoueur();
            return (m.taillePlateau());
        }
        return 0;
    }

    public static void nbJoueur() {
        UI m = new UI();
        System.out.println("Voulez-vous jouer contre l'ordinateur(O) ou contre un humain(H) ? ");
        String adversaire = in.nextLine().trim();

        jH.nomJoueur = m.nbJoueur(1);

        if (adversaire.charAt(0) == 'H') {
            j2.nomJoueur = m.nbJoueur(2);
        } else if (adversaire.charAt(0) == 'O'){
            j2.nomJoueur = "IA";
        }
    }
}