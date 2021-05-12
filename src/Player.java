public class Player {

    static Board b; //= new Board("Hard-Coded Example", 6, 6);
    static Verification v = new Verification();
    static FileBoardBuilder f = new FileBoardBuilder();

    public static void main(String[] args) {
        
        b = f.readFile(" Board.txt", b);
        //f.checkIfFileExist("A ");
        b.drawBoard();
    
        while (true) {
            b.movePlayer(Verification.userInput(0));
            b.checkIfTargetVisible();
            b.drawBoard();  
            if(b.checkIfWin()) break;
        }
    }
}