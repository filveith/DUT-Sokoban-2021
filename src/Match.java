import java.io.PrintStream;
import java.util.Scanner;

public class Match {

    static Scanner in = new Scanner(System.in);
    static PrintStream out = System.out;

    public void userInterface(){
        boolean boucler = true;
        while (boucler) {
            out.println("* Menu *");
            out.println("Où voulez vous jouer ? ");
            String commande = in.nextLine().trim();
            switch (commande) {
                case "/quit":
                    out.println("-> Bye.");
                    boucler = false;
                    break;
                default:
                    out.println("-> commande inconnue '" + commande + "'");
                    break;
            }
        }
    }
}