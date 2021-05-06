import java.util.Scanner;
public class Main {

    static Board b = new Board("Hard-Coded Example", 6, 6) ;
    static Verification v = new Verification();
    static private Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        b.emptyBoard();
        b.drawBoard();
        
        b.addHorizontalWall(0,0,5) ;
        b.addHorizontalWall(0,5,5) ; 
        b.addVerticalWall(0,0,5) ;
        b.addVerticalWall(5,0,5) ;
        b.addBox(2,1) ;
        b.addBox(2,3) ;
        b.addTarget(3,1) ;
        b.addTarget(3,2) ;
        b.setPosition(3,4);
       
        b.drawBoard();
        
        while (true) {
            b.movePlayer(userMovementInput());
            b.checkIfTargetVisible();
            b.drawBoard();  
            if(b.checkIfWin()) break;
        }
    }

    //ON peut en faire une seule fonctione avec verification
    private static String userMovementInput(){
        System.out.println("Ou voulez vous jouer ? L R U D");
        String commande = in.nextLine().trim();
        commande = v.checkUserMovement(commande);
        //System.out.println(commande);  //DEBUG check if the user input is correctly returned
        return commande;
    }
}