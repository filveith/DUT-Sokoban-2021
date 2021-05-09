import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileBoardBuilder {

    private int height = 0;
    private int width = 0;
    private int nbTarget = 0;
    private Case[][] targetList;

    // #region setter/getter
    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getNbTarget() {
        return this.nbTarget;
    }

    public void setNbTarget(int nbTarget) {
        this.nbTarget = nbTarget;
    }

    public Case[][] getTargetList() {
        return this.targetList;
    }

    public void setTargetList(Case[][] targetList) {
        this.targetList = targetList;
    }
    // #endregion

    Board readFile(String file, Board b) {
        int h = 0;
        int y = 0;
        try {
            File myObj = new File("sokoban/board/" + file);
            Scanner myReader = new Scanner(myObj);
            Scanner size = new Scanner(myObj);

            while (size.hasNextLine()) {
                setWidth((size.nextLine()).length());
                h++;
            }
            setHeight(h);

            b = new Board(file, getWidth(), getHeight());
            b.emptyBoard();

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                for (int x = 0; x < getWidth(); x++) {
                    char v = data.charAt(x);
                    switch (v) {
                        case '#':
                            b.setPoint(x, y, b.wallSign);
                            ;
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
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred in FileBoardBuilder");
            e.printStackTrace();
        }
        return b;
    }
}