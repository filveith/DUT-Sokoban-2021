import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileBoardBuilder {

    private int height = 0;
    private int width = 0;
    private int nbTarget = 0;
    private Case[][] targetList;

    // #region setter/getter

    /**
     * Getter of the boards height
     * 
     * @return int
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Setter of the boards height
     * 
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Getter of the boards width
     * 
     * @return int
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Setter of the boards width
     * 
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Getter of the numbers of targets in the board
     * 
     * @return int
     */
    public int getNbTarget() {
        return this.nbTarget;
    }

    /**
     * Setter for the numbers of targets in the board
     * 
     * @param nbTarget
     */
    public void setNbTarget(int nbTarget) {
        this.nbTarget = nbTarget;
    }

    /**
     * Getter for all the targets possition
     * 
     * @return Case[][]
     */
    public Case[][] getTargetList() {
        return this.targetList;
    }

    /**
     * Setter of the positon of a target
     * 
     * @param targetList
     */
    public void setTargetList(Case[][] targetList) {
        this.targetList = targetList;
    }
    // #endregion

    /**
     * Check if a file exist with it's filename
     * 
     * @param fileName
     * @return boolean
     */
    public boolean checkIfFileExist(String fileName) {
        try {
            File myObj = new File("sokoban/board/" + fileName);
            Scanner myReader = new Scanner(myObj); // this is needed else it won't throw the execption
            System.out.println("Le fichier \"" + fileName + "\" existe");
            return true;
        } catch (Exception e) {
            System.out.println("Le fichier \"" + fileName + "\" n'existe pas");
            return false;
        }
    }

    /**
     * Read the content of a file
     * 
     * @param file
     * @param b
     * @return Board
     */
    Board readFile(String file, Board b) {
        BoardBuilder bb = new BoardBuilder();
        try {
            File myObj = new File("sokoban/board/" + file);
            Scanner myReader = new Scanner(myObj);
            Scanner myReader2 = new Scanner(myObj);
            b = bb.build(myReader, myReader2, b, file);
            System.out.println("Le fichier existe");
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier n'existe pas, merci d'en choisir un autre");
            // System.out.println("-The file doesn't exist, taking an existing board \n");
            // b = bb.hardCodedBoard(b);
            // return b;
        }
        return b;
    }

    /**
     * Returns the content of a board as a Scanner
     * 
     * @param boardName
     * @return Scanner
     */
    public Scanner boardInfos(String boardName) {
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
            System.out.println("width = " + width + "    height = " + height);
        } catch (Exception e) {
            System.out.println("The file doesn't exist");
        }
        return myReader;
    }
}