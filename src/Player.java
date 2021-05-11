import java.util.Scanner;
public class Player {

    static Board b; //= new Board("Hard-Coded Example", 6, 6);
    static Verification v = new Verification();
    static FileBoardBuilder f = new FileBoardBuilder();
    static private Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        
        b = f.readFile("A Simple Board.txt", b);
        
        b.drawBoard();

        while (true) {
            b.movePlayer(Verification.userInput(0));
            b.checkIfTargetVisible();
            b.drawBoard();  
            if(b.checkIfWin()) break;
        }
    }
}