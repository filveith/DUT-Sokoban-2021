import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Administrator {

   static private Scanner in = new Scanner(System.in);
   static Connection c = null;
   static Statement stmt = null;
   static Verification v = new Verification();
   static boolean loop = true;

   
   /**
    * Main method of Administrator  
    * 
    * @param args
    */
   public static void main(String[] args) {
      System.out.println("ADMINISTRATION INTERFACE - USE WITH CAUTION");
      openConnexion();

      while (loop) {
         try {
            System.out.println();
            String command = v.userInput(1);

            switch (command) {
               case "create": // Create a new empty pre-configured data base
                  createDataBase();
                  break;
               case "list": // List all the boards in the database
                  listBoards();
                  break;
               case "show":
                  showBoard(v.userInput(5));
                  break;
               case "add": // Add a board from a text file
                  insertBoards(v.userInput(4), v.userInput(3)); // ask for board id + name of the text file
                  break;
               case "remove": // Removes a board or the entire DataBase
                  deleteBoardID(v.userInput(6));
                  break;
               case "quit": // Quit the data base (stops the programm)
                  closeDataBase();
                  loop = false;
                  break;
               default:
                  break;
            }
         } catch (Exception e) {
            errorDataBase(e);
         }
      }
   }

   
   /** 
    * Same as the main but with less commands, just for the user to choose a board to play 
    *
    * @param b
    * @return Board
    */
   public static Board playerAdministrator(Board b) {
      openConnexion();
      while (loop) {
         try {
            System.out.println();
            String command = v.userInput(7);

            switch (command) {
               case "list": // List all the boards in the database
                  listBoards();
                  break;
               case "show":
                  showBoard(v.userInput(5));
                  break;
               case "play":
                  b = loadBoard(v.userInput(5), b);
                  // TODO: Player chooses a board (with board_id) and create the borad ect...
                  break;
               case "quit": // Quit the data base (stops the programm)
                  closeDataBase();
                  return b;
               default:
                  break;
            }
         } catch (Exception e) {
            errorDataBase(e);
         }
      }
      return b;
   }

   /**
    * opens the connexion with the database
    */
   private static void openConnexion() {
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:BoardDB.db");
         c.setAutoCommit(false);
         System.out.println("La base de données est ouverte");
         stmt = c.createStatement();

      } catch (Exception e) {
         errorDataBase(e);
      }
   }

   
   /**
    *  
    * 
    * @param newDataBaseName
    */
   private static void createDataBaseConnection(String newDataBaseName) {
      try {

         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:" + newDataBaseName + ".db");
         c.setAutoCommit(false);
         System.out.println("Created new database successfully");
         stmt = c.createStatement();

      } catch (Exception e) {
         errorDataBase(e);
      }
   }

   /**
    * Close the connexion with the database
    */
   private static void closeDataBase() {
      try {
         stmt.close();
         c.close();
      } catch (Exception e) {
         errorDataBase(e);
      }
   }

   
   /** 
    * Delete a board in the database 
    *
    * @param boardID
    */
   private static void deleteBoardID(String boardID) {
      System.out.println("Are you sure you want to delete '" + boardID + "'   (Yes/No)");
      String userInput = (in.nextLine().trim()).toLowerCase();
      if (userInput.equals("yes") || userInput.equals("y")) {
         try {

            String sql = "DELETE FROM ROWS WHERE board_id='" + boardID + "';";
            stmt.executeUpdate(sql);

            sql = "DELETE FROM BOARDS WHERE board_id='" + boardID + "';";
            stmt.executeUpdate(sql);

            c.commit();
            System.out.println("Operation done successfully");

         } catch (Exception e) {
            errorDataBase(e);
         }
      } else {
         System.out.println("Okay leaving deletion mode");
      }
   }

   /**
    * Prints all the boards id's, name and size
    */
   private static void listBoards() {
      try {
         ResultSet rs = stmt.executeQuery("SELECT * FROM BOARDS;");

         //System.out.format(); //POUR FORMAT LE TEXTE

         System.out.println("| board_id | name             | nb_rows | nb_cols |\n"
               + "|----------|------------------|---------|---------|");
         while (rs.next()) {
            String board_id = rs.getString("board_id");
            String name = rs.getString("name");
            int nb_rows = rs.getInt("nb_rows");
            int nb_cols = rs.getInt("nb_cols");

            System.out.println("| \"" + board_id + "\" | " + name + "           | " + nb_rows + " | " + nb_cols + " |");

         }
         rs.close();

      } catch (Exception e) {
         errorDataBase(e);
      }
      System.out.println("Operation done successfully");
   }

   
   /**
    * Prints a board 
    * 
    * @param boardID
    */
   private static void showBoard(String boardID) {
      try {
         ResultSet rs = stmt.executeQuery("SELECT * FROM ROWS WHERE board_id='" + boardID + "';");

         System.out.println(
               "| board_id | row_num          | description  |\n" + "|----------|------------------|--------------|");

         while (rs.next()) {
            String board_id = rs.getString("board_id");
            int nb_rows = rs.getInt("row_num");
            String description = rs.getString("description");

            System.out.println("| \"" + board_id + "\" | " + nb_rows + " | " + description + " |");
         }
         rs.close();

      } catch (Exception e) {
         System.out.println("error showBoard()");
         errorDataBase(e);
      }
   }

   /** 
    * add a new board to the table Board
    *
    * @param board_id
    * @param board_name
    */
   private static void insertBoards(String board_id, String board_name) {
      try {
         FileBoardBuilder f = new FileBoardBuilder();
         String textFileName = board_name + ".txt";
         int nb_rows = 0;
         int nb_cols = 0;
         if (f.checkIfFileExist(textFileName)) {
            Scanner newBoard = f.boardInfos(textFileName);
            nb_rows = f.getHeight();
            nb_cols = f.getWidth();
            int row_num = 0;
            while (newBoard.hasNextLine()) {
               insertRows(board_id, row_num, ("\"" + newBoard.nextLine() + "\""));
               row_num++;
            }
            String sql = "INSERT INTO BOARDS(board_id,NAME,nb_rows,nb_cols) " + "VALUES (\'" + board_id + "\', \'"
                  + board_name + "\', " + nb_rows + ", " + nb_cols + ");";

            stmt.executeUpdate(sql);
            c.commit();

            System.out.println("Records created successfully");
         }
      } catch (Exception e) {
         errorDataBase(e);
      }
   }

   
   /** 
    * add a new board to the table Rows
    *
    * @param board_id
    * @param row_num
    * @param description
    */
   private static void insertRows(String board_id, int row_num, String description) {
      try {

         String sql = "INSERT INTO ROWS(board_id,row_num,description) " + "VALUES (\'" + board_id + "\', \'" + row_num
               + "\', " + description + ")";

         stmt.executeUpdate(sql);
         c.commit();
         System.out.println("Records created successfully");
      } catch (Exception e) {
         errorDataBase(e);
      }

   }

   /**
    * Create the tables in the database
    */
   private static void createDataBase() {

      try {
         String sql = "CREATE TABLE BOARDS " + "(board_id NOT NULL," + " NAME TEXT NOT NULL, "
               + " nb_rows INT NOT NULL, " + " nb_cols INT NOT NULL)";
         stmt.executeUpdate(sql);

         sql = "CREATE TABLE ROWS " + "(board_id NOT NULL," + " row_num INT NOT NULL, "
               + " description String NOT NULL)";
         stmt.executeUpdate(sql);

         System.out.println("Table created successfully");
      } catch (Exception e) {
         errorDataBase(e);
      }

   }

   
   /** 
    * Load a board from the database to be played 
    * 
    * @param boardID
    * @param b
    * @return Board
    */
   public static Board loadBoard(String boardID, Board b) {
      int boucle = 0;
      int heightBoard = 0;
      boolean loop = true;
      String board = "";
      try {
         ResultSet height = stmt.executeQuery("SELECT * FROM BOARDS WHERE board_id='" + boardID + "';");
         heightBoard = height.getInt("nb_rows");
      } catch (SQLException e) {
         errorDataBase(e);
      }
      while (loop) {
         try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM ROWS WHERE board_id='" + boardID + "' AND row_num=" + boucle + ";");
            while (rs.next()) {
               String board_id = rs.getString("board_id");
               int nb_rows = rs.getInt("row_num");
               String description = rs.getString("description");

               //System.out.println("Board id = \"" + board_id + "\" | row num =  " + nb_rows + " | " + description + " |");
               System.out.println(description);
               board += description+"\n";
               
            }
            rs.close();
            boucle++;
            if(boucle == heightBoard+1){
               loop = false;
               System.out.println("Voulez vous jouer avec ce plateau ? (O/N)");
               String userInput = in.nextLine().trim();
               if (userInput.equals("N")) {
                  loop = true;
               }
            } 

         } catch (Exception e) {
            System.out.println("error showBoard()");
            errorDataBase(e);
         }
      }
      Scanner s1 = new Scanner(board), s2 = new Scanner(board);
      return BoardBuilder.build(s1, s2, b, boardID);
   }

   
   /**
    * Check if the id of the table exist, use when adding a new table 
    * 
    * @param id
    * @return boolean
    */
   public static boolean checkIfIDExist(String id) {
      try {
         String sql = ("SELECT * FROM ROWS WHERE board_id='" + id + "';");
         System.out.println(sql);
         ResultSet rs = stmt.executeQuery(sql);
         if (rs.getString("board_id").equals(id)) {
            // System.out.println("Le id_plateau existe déjà");
            return true;
         } else {
            return false;
         }
      } catch (Exception e) {
         return false;
      }
   }

   
   /**
    * Print the erros of the database 
    * 
    * @param e
    */
   private static void errorDataBase(Exception e) {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
   }
}