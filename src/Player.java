public class Player {

    static Board b, backup; //= new Board("Hard-Coded Example", 6, 6);
    static Verification v = new Verification();
    static FileBoardBuilder f = new FileBoardBuilder();

    public static void main(String[] args) {

        b = v.playerMenu(b);

        b = f.readFile("Simple-One.txt", b);
        b.drawBoard();
    
        while (true) {
            b.movePlayer(Verification.userInput(0));
            b.drawBoard();  
            if(b.checkIfWin()) break;
        }
    }

    public void restartGame(){
        System.out.println("Redémarrage de la partie...");
        b = f.readFile(b.getBoardName(), b);
    }

    public void changeBoard(String newBoard){
        System.out.println("Redémarrage de la partie...");
        b = f.readFile(b.getBoardName(), b);
    }
}