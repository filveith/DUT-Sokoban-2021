package jeu;

public class Main {

    static char x;
    static int y;
    static boolean boot = true;
    

    public static void main(String[] args) {
        UI m = new UI();    
        Screen s = new Screen(bootMain());  //max 26 because the alphabet is 26 char long :)
        s.clear();
        s.display();
        
        System.out.println("* Menu *");
        while(true){
            String userInput = m.userInterface();
            if (userInput.length() == 2){
                x = userInput.charAt(0);
                y = Integer.parseInt(userInput.substring(1));
            } else if (userInput.length() == 3){
                x = userInput.charAt(0);
                y = Integer.parseInt(userInput.substring(1,3));
            }
            if(s.checkUserInput(x, y)){
                s.setPoint(x, y);
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
           return (m.taillePlateau());
        }
        return 0;
    }
}