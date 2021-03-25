/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author fil
 */

public class Screen {
    private final int nbCols, nbRows ;
    private final char[][] image ;
    char[] barLetter = {'A','B','C','D','E','F','G','H'};
    String barLetterComplet = "A B C D E F G H";
    public Screen(int nbCols, int nbRows) {
        this.nbCols = nbCols ;
        this.nbRows = nbRows ;
        this.image = new char[nbCols][nbRows];
    }
    
    public void clear() {
        for (int r = 0 ; r < nbRows ; r++) {
            for (int c = 0 ; c < nbCols ; c++) {
                image[c][r] = ' ' ;
            }
        }
    }

    public void putChar(char c, int col, int row){
        if (0 <= row && row < nbRows && 0 <= col && col < nbCols) {
            image[col][row] = c ;
        }
    }
    
    public void display() {
        displayLetter();
        displayBar() ;
        for (int r = 0 ; r < nbRows ; r++) {
            System.out.print((r+1)+"|") ;
            for (int c = 0 ; c < nbCols ; c++) {
                System.out.print(image[c][r]) ;
            }
            System.out.println("|") ;
        }
        displayBar() ;
    }
    
    private void displayBar() {
        System.out.print(" +") ;
        for (int c = 0 ; c < nbCols ; c++) {
            System.out.print("-");
        }
        System.out.println("+") ;
    }

    private void displayLetter(){
        System.out.println("   "+barLetterComplet);
    }
}
