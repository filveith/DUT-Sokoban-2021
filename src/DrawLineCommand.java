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

public class DrawLineCommand implements Command{
    private final int nbPas;
    private final int rowDirection; 
    private final int colDirection;
    
    public DrawLineCommand(int nbPas, int rowDirection, int colDirection) {
        this.nbPas = nbPas;
        this.rowDirection = rowDirection;
        this.colDirection = colDirection;
    }
    
    @Override
    public void execute(Robot robot) {
        robot.drawChar() ;
        for (int i = 0 ; i < nbPas ; i++) {
            robot.relativeMove(rowDirection, colDirection);
            robot.drawChar();
        }
    }
}