import java.util.Scanner;

public class BoardBuilder {

    static private int height = 0;
    static private int width = 0;
    static private int nbTarget = 0;
    static private Case[][] targetList;
    static FileBoardBuilder f = new FileBoardBuilder();

    /**
     * Method to build a board from a file or a database
     * 
     * @return Board
     */
    public static Board build(Scanner myScanner, Scanner myScanner2, Board b, String boardName) {
        int y = 0;
        int h = 0;
        try {
            Scanner size = myScanner;
            Scanner myReader = myScanner2;

            while (size.hasNextLine()) {
                setWidth((size.nextLine()).length());
                h++;
            }

            setHeight(h);

            // System.out.println(" h = "+getHeight()+" w = "+getWidth()); //DEBUG

            b = new Board(boardName, getWidth(), getHeight());
            b.emptyBoard();

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                for (int x = 0; x < b.getWidth(); x++) {
                    // System.out.println("Data = "+data+" x = "+x+" data.charAt(x) =
                    // "+data.charAt(x)); //DEBUG
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
            System.out.println("An error occurred in the class BoardBuilder, build() : " + e);
        }
        return b;
    }

    /**
     * A hard-coded board in case there is no board to play
     * 
     * @param b
     * @return Board
     */
    public static Board hardCodedBoard(Board b) {
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

    // #region setter/getter

    /**
     * Getter of the boards height
     * 
     * @return int
     */
    static public int getHeight() {
        return height;
    }

    /**
     * Setter of the boards height
     * 
     * @param heightIn
     */
    static public void setHeight(int heightIn) {
        height = heightIn;
    }

    /**
     * Getter of the boards width
     * 
     * @return int
     */
    static public int getWidth() {
        return width;
    }

    /**
     * Setter of the boards width
     * 
     * @param widthIn
     */
    static public void setWidth(int widthIn) {
        width = widthIn;
    }

    /**
     * Getter of the numbers of traget in the board
     * 
     * @return int
     */
    static public int getNbTarget() {
        return nbTarget;
    }

    /**
     * Setter of the numbers of traget in the board
     * 
     * @param nbTargetIn
     */
    static public void setNbTarget(int nbTargetIn) {
        nbTarget = nbTargetIn;
    }

    /**
     * Getter of the positons of a target on the board
     * 
     * @return Case[][]
     */
    static public Case[][] getTargetList() {
        return targetList;
    }

    /**
     * Setter of the positons of a target on the board
     * 
     * @param targetListIn
     */
    static public void setTargetList(Case[][] targetListIn) {
        targetList = targetListIn;
    }
    // #endregion
}
