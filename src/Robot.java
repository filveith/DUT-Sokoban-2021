/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;
/**
 *
 * @author fil
 */
public class Robot {

    /**
     * @param args the command line arguments
     */
    private final int nbCols, nbRows ;
    private final Screen screen ;
    private int currentCol, currentRow ;
    private char point = '*';
    // position actuelle
    
    public Robot(int nbCols, int nbRows) {
        this.nbCols = nbCols ;
        this.nbRows = nbRows ;
        this.currentCol = 0 ;
        this.currentRow = 0 ;
        screen = new Screen(nbCols, nbRows) ;
    }
    
    public void run(Command[] program) {
        screen.clear() ;
        for (Command c : program) {
            c.execute(this) ;
        }
        screen.display() ;
    }
    
    public void setChar(char newCar){
        point=newCar;
    }

    public void drawChar() {
        screen.putChar(point, currentCol, currentRow) ;
    }
    
    public void relativeMove(int c, int r) {
        currentCol += c ;
        currentRow += r ;
    }
    
    public void absoluteMove(int c, int r) {
        currentCol = c ;
        currentRow = r ;
    }
}