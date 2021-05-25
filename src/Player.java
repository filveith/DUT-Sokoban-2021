import java.util.Scanner;

public class Player {

    static private Board b; //= new Board("Hard-Coded Example", 6, 6);
    static private Verification v = new Verification();
    static private FileBoardBuilder f = new FileBoardBuilder();
    static private Administrator a = new Administrator();
    static private Scanner in = new Scanner(System.in);
    static private boolean dataBaseUsed = false;
    
    /**
     * Main method of Player 
     * 
     * @param args
     */
    public static void main(String[] args) {
        b = chooseBoard(b);
        b.drawBoard();
    
        while (true) {
            b.movePlayer(Verification.userInput(0));
            b.drawBoard();  
            if(b.checkIfWin()) break;
        }
    }

    /**
     * Restarts the game when the player asks it
     */
    public void restartGame(){
        System.out.println("Redémarrage de la partie... \n");
        if (dataBaseUsed) {
            b = a.loadBoard(b.getBoardName(), b);
        } else {
            b = f.readFile(b.getBoardName(), b);
        }
    }

    
    /** 
     * Start a new game with a new Board
     * 
     * @param newBoard
     */
    public void changeBoard(){
        System.out.println("Changement de plateau..");
        b = null;
        b = chooseBoard(b);
    }
    
    /**
     * Menu for the player to choose a board to play on 
     * 
     * @param b
     * @return Board
     */
    private static Board chooseBoard(Board b) {
        boolean loop = true;
        while (loop) {
            System.out.println("Voulez vous choir un fichier de la base de donnée (bd) ou dans le dossier board (board)");
            String input = in.nextLine().trim().toLowerCase();
            
            if (input.equals("bd")) {
                b = a.playerAdministrator(b);
                if (b != null) {
                    dataBaseUsed = true;
                    loop = false;
                }
            } else if (input.equals("board")) {
                input = in.nextLine().trim();
                if(f.checkIfFileExist(input+".txt")){
                    b = f.readFile(input+".txt", b);
                    loop = false;
                }
            } else {
                System.out.println("Commande entrée non-disponible, 'quit' pour quitter le jeu");
            }
        }
        return b;
    }
}