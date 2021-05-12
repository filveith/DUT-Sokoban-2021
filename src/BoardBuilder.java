import java.util.Scanner;
import java.io.File;
public class BoardBuilder {
    
    static private int height = 0;
    static private int width = 0;
    static private int nbTarget = 0;
    static private Case[][] targetList;
    static FileBoardBuilder f = new FileBoardBuilder();
    

    public static Board boardBuilder(File myFile, Board b, String boardName) {
        int y = 0;
        int h = 0;
        try {
            Scanner myReader = new Scanner(myFile);
            Scanner size = new Scanner(myFile);

            while (size.hasNextLine()) {
                setWidth((size.nextLine()).length());
                h++;
            }
            setHeight(h);

            System.out.println(" h = "+getHeight()+"   w = "+getWidth());

            b = new Board(boardName, getWidth(), getHeight());
            b.emptyBoard();

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                for (int x = 0; x < f.getWidth(); x++) {
                    //System.out.println("x = "+x+"   data.charAt(x) = "+data.charAt(x)); //DEBUG
                    char v = data.charAt(x);
                    switch (v) {
                        case '#':
                            b.setPoint(x, y, b.wallSign);
                            break;
                        case 'C':
                            b.addBox(x, y);
                            break;
                        case '.':
                            break;
                        case 'P':
                            b.addPlayer(x, y);
                            break;
                        case 'x':
                            b.addTarget(x, y);
                            break;
                        default:
                            break;
                    }
                }
                y++;
            }
            myReader.close();
            size.close();
        } catch (Exception e) {
            System.out.println("An error occurred in FileBoardBuilder : "+e);
        }
        return b;
    }

    public static Board hardCodedBoard(Board b){
        b = new Board("HardCodedBoard", 10, 5);
        b.emptyBoard();
        b.addHorizontalWall(0, 0, 9);
        b.addHorizontalWall(0, 4, 10);
        b.addVerticalWall(0, 0, 5);
        b.addVerticalWall(9, 0, 5);
        b.addWall(4, 1);
        b.addTarget(1, 1);
        b.addTarget(3, 1);
        b.addBox(4, 2);
        b.addBox(5, 2);
        b.addPlayer(7, 2);
        return b;
    }

    //#region setter/getter
    static public int getHeight() {
        return height;
    }

    static public void setHeight(int heightIn) {
        height = heightIn;
    }

    static public int getWidth() {
        return width;
    }

    static public void setWidth(int widthIn) {
        width = widthIn;
    }

    static public int getNbTarget() {
        return nbTarget;
    }

    static public void setNbTarget(int nbTargetIn) {
        nbTarget = nbTargetIn;
    }

    static public Case[][] getTargetList() {
        return targetList;
    }

    static public void setTargetList(Case[][] targetListIn) {
        targetList = targetListIn;
    }
    //#endregion
}
