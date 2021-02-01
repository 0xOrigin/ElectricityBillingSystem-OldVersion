package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbConnection {
    
    public static Connection connect(){
        
        Connection conn = null; 
        
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:EPS.db");
            
            try(PreparedStatement pss = conn.prepareStatement("PRAGMA foreign_keys = ON;");){
                pss.execute();
            } catch (SQLException e){
            System.out.println(e.toString());
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.toString());
        }

        return conn;
    }
}
