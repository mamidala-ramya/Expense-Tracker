import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CSVExporter {

    Connection con = DBConnection.getConnection();

    public void exportToCSV() {
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM expenses");

            FileWriter writer = new FileWriter("expenses.csv");

            writer.append("ID,Category,Amount,Date\n");

            while (rs.next()) {
                writer.append(rs.getInt("id") + ",");
                writer.append(rs.getString("category") + ",");
                writer.append(rs.getDouble("amount") + ",");
                writer.append(rs.getString("date") + "\n");
            }

            writer.flush();
            writer.close();

            System.out.println("CSV Exported Successfully");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
