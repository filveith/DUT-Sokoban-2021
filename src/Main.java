public class Main {

    public static void main(String[] args) {
        Screen s = new Screen(5);  //max 26 because the alphabet is 26 char long :)
        Match m = new Match();
        s.clear();
        s.display();
        m.userInterface();
    }

}