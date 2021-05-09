public class Personnage {

    int x;
    int y;

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }    

    public void setPositionOfPlayer(int x, int y){
        setX(x);
        setY(y);
    }
}
