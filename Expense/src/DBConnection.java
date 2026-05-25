import java.sql.*;

public class DBConnection {

    public static Connection getConnection() {

        try {

            String url =
                "jdbc:mysql://localhost:3306/expense_db";

            String user = "root";
            String password = "bhuvana1@m&09!#";

            Connection con =
                DriverManager.getConnection(
                    url,user,password
                );

            System.out.println("Database Connected Successfully");

            return con;

        } catch(Exception e) {

            System.out.println(e.getMessage());
            return null;
        }
    }
}