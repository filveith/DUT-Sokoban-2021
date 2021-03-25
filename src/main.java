
public class main {

    public static void main(String[] args) {
        Screen s = new Screen(25);
        s.clear();
        match m = new match();
        s.display();
        m.userInterface();
    }

}