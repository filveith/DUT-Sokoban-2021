import java.sql.DriverManager;
import java.sql.Statement;

import java.sql.Connection;
// import java.sql.PreparedStatement;
import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.DatabaseMetaData;
//import java.sql.*;

public class Administrator {

   static Connection c = null;
   static Statement stmt = null;
   static Verification v = new Verification();
   static boolean loop = true;
   
   public static void main(String[] args) {
	   openConnexion();
	   while(loop) {
		   try {
			   System.out.println();
			   String command = v.userInput(1);
			   String nomBoard = "test";
			   
			   switch (command) {
					case "create":    //Create new empty pre-configured data base
						createDataBase();
						break;
					case "list":   //List all the boards in the database
						
						break;
					case "show":   //Print a board
						showDataBase();
						break;
					case "add": //Add a board from a text file
						insertDataBase("simple", v.userInput(3), 5, 6);
						break;
					case "remove": //Removes a board or the entire DataBase
						deletDataBase(nomBoard);
						break;
					case "quit":   //Quit the data base (stops the programm)
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
   
   private static void openConnexion() {
	   try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:NewData.db");
         c.setAutoCommit(false);
         System.out.println("La base de données est ouverte");
         stmt = c.createStatement();

      } catch (Exception e) {
         errorDataBase(e);
      }
   }

   private static void createDataBaseConnection(String newDataBaseName) {
      try {

         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:"+newDataBaseName+".db");
         c.setAutoCommit(false);
         System.out.println("Created new database successfully");
         stmt = c.createStatement();

      } catch (Exception e) {
         errorDataBase(e);
      }
   }

   private static void closeDataBase() {
      try {
         stmt.close();
         c.close();
      } catch (Exception e) {
         errorDataBase(e);
      }
   }

   private static void deletDataBase(String nomBoard) {

      try {

         String sql = "DELETE from COMPANY where ID=2;";
         stmt.executeUpdate(sql);
         c.commit();

      } catch (Exception e) {
         errorDataBase(e);
      }
      System.out.println("Operation done successfully");
   }

   private static void updateDataBase() {
      try {

         String sql = "UPDATE BOARDS set NAME = newName where name = oldname;";
         stmt.executeUpdate(sql);
         c.commit();

      } catch (Exception e) {
         errorDataBase(e);
      }
      System.out.println("Operation done successfully");
   }

   private static void showDataBase() {
      try {
         ResultSet rs = stmt.executeQuery("SELECT * FROM BOARDS;");

         System.out.println("| board_id | name             | nb_rows | nb_cols |\n"
                           +"|----------|------------------|---------|---------|");
         while (rs.next()) {
            String board_id= rs.getString("board_id");
            String name = rs.getString("name");
            int nb_rows = rs.getInt("nb_rows");
            int nb_cols = rs.getInt("nb_cols");

            //System.out.println("board_id = " + board_id);
            //System.out.println("name = " + name);
            //System.out.println("nb_rows = " + nb_rows);
            //System.out.println("nb_cols = " + nb_cols);
            //System.out.println();

            
            System.out.println("| \""+board_id+"\" | "+name+"           | "+nb_rows+" | "+nb_cols+" |");

         }
         rs.close();

      } catch (Exception e) {
         errorDataBase(e);
      }
      System.out.println("Operation done successfully");
   }

   private static void insertDataBase(String board_id, String board_name, int nb_rows, int nb_cols) {
      try {

         String sql = "INSERT INTO BOARDS(board_id,NAME,nb_rows,nb_cols) "
                     + "VALUES (\'"+board_id+"\', \'"+board_name+"\', "+nb_rows+", "+nb_cols+");";
         stmt.executeUpdate(sql);

         /*sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " + "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );";
         stmt.executeUpdate(sql);

         sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " + "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );";
         stmt.executeUpdate(sql);

         sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " + "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );";
         stmt.executeUpdate(sql);
         */
         c.commit();
      } catch (Exception e) {
         errorDataBase(e);
      }
      System.out.println("Records created successfully");
   }

   private static void createDataBase() {

      try {
         String sql = "CREATE TABLE BOARDS " + "(board_id TEXT NOT NULL,"
               + " NAME TEXT PRIMARY KEY NOT NULL, " + " nb_rows INT NOT NULL, "
               + " nb_cols INT NOT NULL)";
         stmt.executeUpdate(sql);

         /*sql = "CREATE TABLE ROWS " + "(board_id TEXT NOT NULL,"
               + " row_num INT NOT NULL, "
               + " description INT NOT NULL)";
         stmt.executeUpdate(sql);
         */
      } catch (Exception e) {
         errorDataBase(e);
      }
      System.out.println("Table created successfully");
   }

   private static void errorDataBase(Exception e) {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
   }
}