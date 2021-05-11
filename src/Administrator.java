import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Connection;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;

public class Administrator {
    public static void main(String[] args) {
        String sqlite_driver = "org.sqlite.JDBC";
        String path = "jdbc:sqlite:exemple.db";

        try {
            Class.forName(sqlite_driver);
        } 
        catch (ClassNotFoundException ex) {
            System.err.println("* Driver " + sqlite_driver + " absent ");
            System.exit(1);
        }

        try (Connection c = DriverManager.getConnection(path)) {
            Statement s = c.createStatement();
            s.execute("drop table if exists personnes ");
            s.execute(" create table personnes " + "( prenom text , annee integer )");
            s.executeUpdate(" insert into personnes " + " values (' alexandre ', 1994) ");
            s.executeUpdate(" insert into personnes " + " values (' simon ', 1996) ");
            System .out. println (" Toute la table :");
            ResultSet r = s. executeQuery (" select * from personnes ");

            while (r.next ()) {
                System .out. format ("| % -20s | %4d |\n",
                r. getString (" prenom "), r. getInt (" annee "));
            }
            PreparedStatement ps = c. prepareStatement (" select * from personnes where prenom = ?");

            ps. setString (1, " simon ");
            r = ps. executeQuery ();

            while (r.next ()) {
                System .out. format ("| % -20s | %4d |\n",
                r. getString (1),
                r. getInt (2));
            }
        }
        catch ( SQLException e) {
            System .err. println ("* Exception " + e. getMessage ());
        }
    }

    // public static void createNewDatabase(String fileName) {  
   
    //     String url = "jdbc:sqlite:" + fileName;  
   
    //     try {  
    //         Connection conn = DriverManager.getConnection(url);  
    //         if (conn != null) {  
    //             DatabaseMetaData meta = conn.getMetaData();  
    //             System.out.println("The driver name is " + meta.getDriverName());  
    //             System.out.println("A new database has been created.");  
    //         }  
   
    //     } catch (SQLException e) {  
    //         System.out.println(e.getMessage());  
    //     }  
    // }  
  
    // public static void main(String[] args) {  
    //     createNewDatabase("SSSIT.db");  
    // }   
=======
=======
>>>>>>> c0a3015b162a1a829a1861e4c6e063a71ab74dcb
=======
>>>>>>> c0a3015b162a1a829a1861e4c6e063a71ab74dcb
// import java.sql.PreparedStatement;
import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.DatabaseMetaData;
//import java.sql.*;

public class Administrator {

   static Connection c = null;
   static Statement stmt = null;

   public static void main(String[] args) {
      initDataBaseConnection();
      createDataBase();
      insertDataBase();
      showDataBase();
      updateDataBase();
      // showDataBase();
      closeDataBase();
   }

   private static void initDataBaseConnection() {
      try {

         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:test.db");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
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

   private static void deletDataBase() {

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

         String sql = "UPDATE COMPANY set SALARY = 25000.00 where ID=1;";
         stmt.executeUpdate(sql);
         c.commit();

      } catch (Exception e) {
         errorDataBase(e);
      }
      System.out.println("Operation done successfully");
   }

   private static void showDataBase() {
      try {
         ResultSet rs = stmt.executeQuery("SELECT * FROM COMPANY;");

         while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            String address = rs.getString("address");
            float salary = rs.getFloat("salary");

            System.out.println("ID = " + id);
            System.out.println("NAME = " + name);
            System.out.println("AGE = " + age);
            System.out.println("ADDRESS = " + address);
            System.out.println("SALARY = " + salary);
            System.out.println();
         }
         rs.close();

      } catch (Exception e) {
         errorDataBase(e);
      }
      System.out.println("Operation done successfully");
   }

   private static void insertDataBase() {
      try {

         String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
               + "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
         stmt.executeUpdate(sql);

         sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " + "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );";
         stmt.executeUpdate(sql);

         sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " + "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );";
         stmt.executeUpdate(sql);

         sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " + "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );";
         stmt.executeUpdate(sql);

         c.commit();
      } catch (Exception e) {
         errorDataBase(e);
      }
      System.out.println("Records created successfully");
   }

   private static void createDataBase() {

      try {
         String sql = "CREATE TABLE COMPANY " + "(ID INT PRIMARY KEY     NOT NULL,"
               + " NAME           TEXT    NOT NULL, " + " AGE            INT     NOT NULL, "
               + " ADDRESS        CHAR(50), " + " SALARY         REAL)";
         stmt.executeUpdate(sql);

      } catch (Exception e) {
         errorDataBase(e);
      }
      System.out.println("Table created successfully");
   }

   private static void errorDataBase(Exception e) {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
   }
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> c0a3015b162a1a829a1861e4c6e063a71ab74dcb
=======
>>>>>>> c0a3015b162a1a829a1861e4c6e063a71ab74dcb
=======
>>>>>>> c0a3015b162a1a829a1861e4c6e063a71ab74dcb
}