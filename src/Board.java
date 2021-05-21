public class Board {

    private int height;
    private int width;
    private String boardName;
    final public char emptySpaceSign = '.';
    final public char playerSign = 'P';
    final public char boxSign = 'C';
    final public char targetSign = 'x';
    final public char wallSign = '#';
    private static Case[][] image;
    private Case[][] targetList;
    private int nbBox = 0;

    Personnage p = new Personnage();

    Board(String boardName, int width, int height) {
        this.boardName = boardName;
        this.height = height;
        this.width = width;
        image = new Case[this.width][this.height];
        targetList = new Case[this.width][this.height];
    }

    /**
     * Renvoie l'image de l'objet screen en question
     * 
     * @return l'image
     */
    public Case[][] getImage() {
        return image;
    }

    /**
     * Renvoie l'image de l'objet screen en question
     * 
     * @return l'image
     */
    public Case[][] getTargetList() {
        return targetList;
    }

    
    /** 
     * @param height
     */
    // #region Getter/Setter
    public void setHeight(int height) {
        this.height = height;
    }

    
    /** 
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    
    /** 
     * @return int
     */
    public int getHeight() {
        return height;
    }

    
    /** 
     * @return int
     */
    public int getWidth() {
        return width;
    }

    
    /** 
     * @param boardName
     */
    public void setBoardName(String boardName){
        this.boardName = boardName;
    }

    
    /** 
     * @return String
     */
    public String getBoardName(){
        return boardName;
    }
    // #endregion
    // #region board drawing

    public void drawBoard() {
        checkIfTargetVisible();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(image[x][y].getNature());
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Create an empty board
     */
    public void emptyBoard() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image[x][y] = new Case(emptySpaceSign);
                targetList[x][y] = new Case(emptySpaceSign);
            }
        }
    }

    
    /** 
     * @param x
     * @param y
     * @param length
     */
    public void addHorizontalWall(int x, int y, int length) {
        for (int i = 0; i < length; i++) {
            setPoint(x, y, wallSign);
            x++;
        }
    }

    
    /** 
     * @param x
     * @param y
     */
    public void addWall(int x, int y) {
        setPoint(x, y, wallSign);
    }

    
    /** 
     * @param x
     * @param y
     * @param length
     */
    public void addVerticalWall(int x, int y, int length) {
        for (int i = 0; i <= length; i++) {
            setPoint(x, y, wallSign);
            y++;
        }
    }

    
    /** 
     * @param x
     * @param y
     */
    public void addBox(int x, int y) {
        setPoint(x, y, boxSign);
        nbBox++;
    }

    
    /** 
     * @param x
     * @param y
     */
    public void addTarget(int x, int y) {
        setPoint(x, y, targetSign);
        targetList[x][y].setNature(targetSign);
    }

    
    /** 
     * @param x
     * @param y
     */
    public void addPlayer(int x, int y) {
        p.setPositionOfPlayer(x, y);
        setPoint(p.getX(), p.getY(), playerSign);
    }

    
    /** 
     * @param x
     * @param y
     * @param nature
     */
    public void setPoint(int x, int y, char nature) {
        try {
            image[x][y].setNature(nature);
        } catch (Exception e) {
            System.out.println("error setPoint() in class Board : exception = " + e);
        }

    }
    
    /** 
     * @param movementInput
     */
    // #endregion

    public void movePlayer(String movementInput) {
        for (char movement : movementInput.toCharArray()) {
            int x = p.getX();
            int y = p.getY();
            switch (movement) {
                case 'L':
                    x--;
                    break;
                case 'R':
                    x++;
                    break;
                case 'U':
                    y--;
                    break;
                case 'D':
                    y++;
                    break;
                default:
                    break;
            }

            // System.out.println("x = "+x+" y = "+y);
            if (checkIfMovePossible(x, y, movement, false)) {
                setPoint(x, y, playerSign);

                setPoint(p.getX(), p.getY(), emptySpaceSign);
                p.setPositionOfPlayer(x, y);
            }
        }
    }

    
    /** 
     * @param xFinal
     * @param yFinal
     * @param movement
     * @param recall
     * @return boolean
     */
    public boolean checkIfMovePossible(int xFinal, int yFinal, char movement, boolean recall) {

        char goToCase = image[xFinal][yFinal].getNature();
        if (goToCase == '#' || goToCase == 'P') {
            return false;
        } else if (goToCase == 'C' && !recall) {
            int x = xFinal;
            int y = yFinal;
            switch (movement) {
                case 'L':
                    x--;
                    break;
                case 'R':
                    x++;
                    break;
                case 'U':
                    y--;
                    break;
                case 'D':
                    y++;
                    break;
                default:
                    break;
            }
            if (checkIfMovePossible(x, y, movement, true))
                setPoint(x, y, boxSign);
            else
                return false;
        } else if (goToCase == 'C' && recall) {
            return false;
        }

        return true;
    }

    public void checkIfTargetVisible() {
        for (int y = 0; y <= height; y++) {
            for (int x = 0; x < width; x++) {
                try {
                    if (targetList[x][y].getNature() != '.') {
                        if (image[x][y].getNature() != 'P' && image[x][y].getNature() != 'C'
                                && image[x][y].getNature() != '#') {
                            setPoint(x, y, targetSign);
                        }
                    }
                } catch (Exception e) {
                    // System.out.println("Error at checkIfTargetVisible() : " + e);
                }
            }
        }
    }

    
    /** 
     * @return boolean
     */
    public boolean checkIfWin() {
        int nbWinningBoxes = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                try {
                    if (targetList[x][y].getNature() != '.') {
                        if (image[x][y].getNature() == 'C') {
                            nbWinningBoxes++;
                            if (nbWinningBoxes == nbBox) {
                                System.out.println("\n_-* Félicitation vous avez gagné *-_\n\n");
                                return true;
                            }
                        }
                    }
                } catch (Exception e) {
                    // System.out.println("Error at checkIfTargetVisible() : " + e);
                }
            }
        }
        return false;
    }

    public void drawExampleBoard() {
        System.out.println("  0 1 2 3 4 5 6 7 8\n"
                         + "0 . . # # # # . . .\n"
                         + "1 # # # . . # # # #\n" 
                         + "2 # . . . . . C . #\n"
                         + "3 # . # . . # C . #\n"
                         + "4 # . x . x # P . #\n"
                         + "5 # # # # # # # # #\n");
    }

}