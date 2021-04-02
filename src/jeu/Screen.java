package jeu;

import java.util.ArrayList;

public class Screen {

    private int size;
    private static Case[][] image;
    

    private static char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
                       'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                       'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
                    };


    Screen(int size_) {
        size = size_;
        image = new Case[size*2][size];
    }

    public Case[][] getImage() {
        return image;
    }

    public boolean checkUserInput(char letter, int chiffre){
        String play = Character.toString(letter) + String.valueOf(chiffre);
        int value = new String(alphabet).indexOf(letter);
        if(value != -1 && 0 < chiffre && chiffre <= size){
            if (getAllPossiblePlays().contains(getCaseFromString(play))) {
                return true;
            } else {
                System.out.println("Le coup n'est pas jouable");
                return false;
            }    
        }
        return false;
    }

    public int setPoint(char letter, int chiffre, char nature){
        int x = getPostionOfLetter(letter);
        if (x == 0){
            x++;
        } else if(x == 1){
            x= x + 2;
        } else {
            x=x*2+1;
        }
        image[x][chiffre-1].setNature(nature);
        return 0;
    }

    private int getPostionOfLetter(char letter){
        return(new String(alphabet).indexOf(letter));
    }

    public void clear() {
        for(int r = 0; r < size; r++) {
            for(int c = 0; c < size*2; c++) {
                image[c][r] = new Case(' ');
            }
        }
    }

    public void display() {
        displayLetters();
        displayBar();
        for(int r = 0; r < size ;r++) {
            String lineNum = Integer.toString(r+1);
            if(r<9) {
                lineNum = (" "+Integer.toString(r+1));
            }
            System.out.print(lineNum+"|");
            for(int c = 0; c < size*2 ; c++) {
                System.out.print(image[c][r].nature);
            }
            System.out.println(" |");
        }
        displayBar();
    }
    
    private void displayBar() {
        System.out.print("  +");
        for(int c = 0; c < size*2 ; c++) {
            System.out.print("-");
        }
        System.out.println("-+");
    }

    private void displayLetters(){
        System.out.print("   ");
        for(int i = 0; i<size; i++){
            System.out.print(" "+alphabet[i]);
        }
        System.out.println("");
    }

    public String getPosFromInt(int a, int b) {
       return alphabet[a/2] + String.valueOf(b+1);
    }

    public static Case getCaseFromString(String string_) {
        char letter = string_.charAt(0);
        int indexOfLetter = new String(alphabet).indexOf(letter);
        int number = Integer.parseInt(String.valueOf(string_.charAt(1)));
        return image[indexOfLetter][number - 1];
    }

    public static String getStringFromCase(Case case_) {
        String letter = "";
        String nb = "";
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                if (image[i][j] == case_) {
                    letter = Character.toString(alphabet[i/2]);
                    nb = String.valueOf(j+1);
                }
            }
        }
        return letter.concat(nb);
    }

    public int getNbCoupsJoues() {
        int nb = 0;
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                if (image[i][j].getNature() != ' ') {
                    nb++;
                }
            }
        }
        return nb;
    }

    public ArrayList<Case> getAllPossiblePlays() {        
        ArrayList<Case> possibleMoves = new ArrayList<>();
        ArrayList<Case> adjacentCases = new ArrayList<>();
        int nbCoupsJoues = getNbCoupsJoues();

        //If there's at least one move already played
        if (nbCoupsJoues >= 1) {

            //Check every cases
            for (int i = 0; i < image.length; i++) {
                for (int j = 0; j < image[i].length; j = j + 2) {

                    //If it finds a non empty case
                    if (image[i][j].getNature() != ' ') {
                        nbCoupsJoues++;
                        adjacentCases.clear();

                        //Check all around the case to see if there's another case
                        for (int a = -2; a <= 2; a = a + 2) {
                            for (int b = -1; b <= 1; b++) {
                                //If a case exists, add it
                                try {
                                    if (image[i + a][j + b] != null && image[i + a][j + b] != image[i][j]) {
                                        adjacentCases.add(image[i + a][j + b]);
                                    }
                                } catch (Exception e) {
                                    System.out.println("Probleme mec " + e);
                                }
                                
                            }
                        }
                        //Then, for each non empty case, add the adjacent cases to the list of possible moves
                        possibleMoves.addAll(adjacentCases);
                    }
                }
                
            }
            
            System.out.println("Size " + possibleMoves.size());

        //Else (if there are no move already played)
        } else {

            //Add all cases
            for (int i = 0; i < image.length; i++) {
                for (int j = 0; j < image[i].length; j++) {
                    possibleMoves.add(image[i][j]);
                }
            }
        }
        for (Case c : possibleMoves) {
            System.out.println(getStringFromCase(c));
        }
        
        return possibleMoves;
    }
}
