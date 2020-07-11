
import java.sql.*;

/**
 * @author KeMo
 */

public class myConnection {

    public static Connection getConnection() {

        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:sqlite:data.db");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return con;
    }
}
