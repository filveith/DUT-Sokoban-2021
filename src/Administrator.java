import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Connection;
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
}