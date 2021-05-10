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

   public static void main(String[] args) {
      initDataBaseConnection();
      createDataBase();
      insertDataBase();
      showDataBase();
      updateDataBase();
      // showDataBase();
   }

   public static void initDataBaseConnection() {
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

   public static void closeDataBase() {
      try {
         stmt.close();
         c.close();
      } catch (Exception e) {
         errorDataBase(e);
      }
   }

   public static void deletDataBase() {

      try {

         // stmt = c.createStatement();

         String sql = "DELETE from COMPANY where ID=2;";
         stmt.executeUpdate(sql);
         c.commit();

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
         // stmt.close();
         // c.close();
      } catch (Exception e) {
         errorDataBase(e);
      }
      System.out.println("Operation done successfully");
   }

   public static void updateDataBase() {
      try {

         // stmt = c.createStatement();

         String sql = "UPDATE COMPANY set SALARY = 25000.00 where ID=1;";
         stmt.executeUpdate(sql);
         c.commit();

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
         // stmt.close();
         // c.close();
      } catch (Exception e) {
         errorDataBase(e);
      }
      System.out.println("Operation done successfully");
   }

   public static void showDataBase() {
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
         // stmt.close();
         // c.close();
      } catch (Exception e) {
         errorDataBase(e);
      }
      System.out.println("Operation done successfully");
   }

   public static void insertDataBase() {
      try {

         // stmt = c.createStatement();
         String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
               + "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
         stmt.executeUpdate(sql);

         sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " + "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );";
         stmt.executeUpdate(sql);

         sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " + "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );";
         stmt.executeUpdate(sql);

         sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " + "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );";
         stmt.executeUpdate(sql);

         // stmt.close();
         c.commit();
         // c.close();
      } catch (Exception e) {
         errorDataBase(e);
      }
      System.out.println("Records created successfully");
   }

   public static void createDataBase() {

      try {
         // stmt = c.createStatement();
         String sql = "CREATE TABLE COMPANY " + "(ID INT PRIMARY KEY     NOT NULL,"
               + " NAME           TEXT    NOT NULL, " + " AGE            INT     NOT NULL, "
               + " ADDRESS        CHAR(50), " + " SALARY         REAL)";
         stmt.executeUpdate(sql);
         // stmt.close();
         // c.close();

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