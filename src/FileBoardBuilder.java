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


    public boolean checkIfFileExist(String fileName) {
        try {
            File myObj = new File("sokoban/board/" + fileName);
            Scanner myReader = new Scanner(myObj);
            System.out.println("The file \""+ fileName +"\" exist");
            return true;
        } catch (Exception e) {
            System.out.println("The file \""+ fileName +"\" doesn't exist");
            return false;
        }
    }

    Board readFile(String file, Board b) {
        BoardBuilder bb = new BoardBuilder();
        try {
            File myObj = new File("sokoban/board/" + file);
            Scanner myReader = new Scanner(myObj);
            b = bb.build(myObj, b, file);
            //System.out.println("The file exist");
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("The file doesn't exist");
            //System.out.println("-The file doesn't exist, taking an existing board \n");
            //b = bb.hardCodedBoard(b);
            //return b;
        }
        return b;
    }

    public Scanner boardInfos(String boardName){
        Scanner myReader = null;
        try {
            File myObj = new File("sokoban/board/" + boardName);
            myReader = new Scanner(myObj);
            Scanner size = new Scanner(myObj);

            width = size.nextLine().length();
            while (size.hasNextLine()) {
                size.nextLine();
                height++;
            }
            size.close();
            System.out.println("width = "+width+"    height = "+ height);
        } catch (Exception e) {
            System.out.println("The file doesn't exist");
        }
        return myReader;
    }
}