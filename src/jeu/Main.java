package jeu;

public class Main {

    static char x;
    static int y;
    static boolean boot = true;
    static char nature = 'X';

    public static void main(String[] args) {
        UI m = new UI();    
        Screen s = new Screen(bootMain());  //max 26 because the alphabet is 26 char long :)
        Joueur j1 = new Joueur("Joueur1",0,'X');
        Joueur j2 = new Joueur("Joueur2",1,'O');
        s.clear();
        s.display();

        int playersTurn = 1;

        
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
                    j1.setCoupJouee(userInput);
                    nature = j1.natureJoueur;
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
            //m.nbJoueur();
            return (m.taillePlateau());
        }
        return 0;
    }
}