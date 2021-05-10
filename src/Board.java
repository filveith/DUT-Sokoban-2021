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

    // #region Getter/Setter
    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    // #endregion
    // #region board drawing

    public void drawBoard() {
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

    public void addHorizontalWall(int x, int y, int length) {
        for (int i = 0; i < length; i++) {
            setPoint(x, y, wallSign);
            x++;
        }
    }

    public void addWall(int x, int y) {
        setPoint(x, y, wallSign);
    }

    public void addVerticalWall(int x, int y, int length) {
        for (int i = 0; i <= length; i++) {
            setPoint(x, y, wallSign);
            y++;
        }
    }

    public void addBox(int x, int y) {
        setPoint(x, y, boxSign);
        nbBox++;
    }

    public void addTarget(int x, int y) {
        setPoint(x, y, targetSign);
        targetList[x][y].setNature(targetSign);
    }

    public void addPlayer(int x, int y) {
        p.setPositionOfPlayer(x, y);
        setPoint(p.getX(), p.getY(), playerSign);
    }

    public void setPoint(int x, int y, char nature) {
        try {
            image[x][y].setNature(nature);
        } catch (Exception e) {
            System.out.println("error setPoint() in class Board : exception = " + e);
        }

    }
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
}