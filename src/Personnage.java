public class Personnage {

    int x;
    int y;

    
    /** 
     * Getter of the x position of the player
     * 
     * @return int
     */
    public int getX() {
        return this.x;
    }

    
    /**
     * Setter of the x position of the player 
     * 
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    
    /** 
     * Getter of the y position of the player
     * 
     * @return int
     */
    public int getY() {
        return this.y;
    }

    
    /** 
     * Setter of the y position of the player
     * 
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }    

    
    /**
     * Set the postion of the player 
     * 
     * @param x
     * @param y
     */
    public void setPositionOfPlayer(int x, int y){
        setX(x);
        setY(y);
    }
}
