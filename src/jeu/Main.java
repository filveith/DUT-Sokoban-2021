package jeu;

public class Main {

    static char x;
    static int y;
    public static void main(String[] args) {
        Screen s = new Screen(10);  //max 26 because the alphabet is 26 char long :)
        UI m = new UI();
        s.clear();
        s.display();
        System.out.println("* Menu *");
        while(true){
            //s.getAllPossiblePlays();
            String userInput = m.userInterface(s);      
            if (userInput.length() == 2){
                x = userInput.charAt(0);
                y = Integer.parseInt(userInput.substring(1));
            } else if (userInput.length() == 3){
                x = userInput.charAt(0);
                y = Integer.parseInt(userInput.substring(1,3));
            }
            if(s.checkUserInput(x, y)){
                s.setPoint(x, y, 'X');
                s.display();
            } 
        }
    }
}