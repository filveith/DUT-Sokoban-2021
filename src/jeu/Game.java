package jeu;

import java.util.ArrayList;

public class Game {
    
    private ArrayList<String> allPlays = new ArrayList<>();

    public static ArrayList<String> getAllPlayableMoves(Screen screen) {


        ArrayList<String> moves = new ArrayList<>();
        Case[][] imageScreen = Screen.getImage();


        for (int i = 0; i < imageScreen.length; i ++) {
            for (int j = 0; j < imageScreen[i].length; j++) {
                if (imageScreen[i][j].nature != ' ') {
                    screen.getPosFromInt(i, j);
                }
            }
        }
        


        return moves;
    }

}
