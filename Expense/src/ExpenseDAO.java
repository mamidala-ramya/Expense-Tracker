import java.sql.*;

public class ExpenseDAO {

    Connection con = DBConnection.getConnection();

    public void addExpense(Expense e) {

    try {

        if(e.getAmount() <= 0) {
            System.out.println("Amount must be greater than 0");
            return;
        }

        if(e.getCategory().trim().isEmpty()) {
            System.out.println("Category cannot be empty");
            return;
        }

        if(!e.getDate().matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println("Date format must be yyyy-mm-dd");
            return;
        }

       String query = "INSERT INTO expenses(id,category,amount,date) VALUES(?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1,e.getId());
        ps.setString(2,e.getCategory());
        ps.setDouble(3,e.getAmount());
        ps.setString(4,e.getDate());

        ps.executeUpdate();

        System.out.println("Expense Added Successfully");

    } catch(SQLIntegrityConstraintViolationException ex) {

        System.out.println("Duplicate ID not allowed");

    } catch(Exception ex) {

        System.out.println(ex.getMessage());
    }
    }

    public void viewExpenses() {

        try {

            String query = "SELECT * FROM expenses";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);

            while(rs.next()) {

                System.out.println(
                    rs.getInt("id") + " | " +
                    rs.getString("category") + " | " +
                    rs.getDouble("amount") + " | " +
                    rs.getString("date")
                );
            }

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteExpense(int id) {

        try {

            String query = "DELETE FROM expenses WHERE id=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1,id);

            int rows =
                ps.executeUpdate();

            if(rows > 0)
                System.out.println("Deleted Successfully");
            else
                System.out.println("Expense not found");

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void searchByCategory(String category) {

        try {

            String query = "SELECT * FROM expenses WHERE category=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1,category);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                System.out.println(
                    rs.getInt("id")+" | "+
                    rs.getString("category")+" | "+
                    rs.getDouble("amount")+" | "+
                    rs.getString("date")
                );
            }

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void searchByDate(String date) {

        try {

            String query = "SELECT * FROM expenses WHERE date=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1,date);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                System.out.println(
                    rs.getInt("id")+" | "+
                    rs.getString("category")+" | "+
                    rs.getDouble("amount")+" | "+
                    rs.getString("date")
                );
            }

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void monthlyReport(String month) {

        try {

            String query = "SELECT SUM(amount) FROM expenses WHERE date LIKE ?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1,"%"+month+"%");

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                System.out.println(
                    "Monthly Total: " +
                    rs.getDouble(1)
                );
            }

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}