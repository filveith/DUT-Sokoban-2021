public class Target {
    int x;
    int y;
 
    /**
     * Constructor for Target
     * 
     * @param x
     * @param y
     */
    Target(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    /** 
     * Getter of the x position of a target
     * 
     * @return int
     */
    public int getX() {
        return this.x;
    }

    
    /** 
     * Setter of the x position of a target
     * 
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    
    /** 
     * Getter of the y position of a target
     * 
     * @return int
     */
    public int getY() {
        return this.y;
    }

    
    /** 
     * Setter of the y position of a target
     * 
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

}
