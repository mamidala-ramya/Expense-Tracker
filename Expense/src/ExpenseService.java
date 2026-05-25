import java.sql.*;

public class ExpenseService {
    public void addExpense(Expense expense) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO expenses(category,amount,date) VALUES(?,?,?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, expense.getCategory());
            ps.setDouble(2, expense.getAmount());
            ps.setString(3, expense.getDate());

            ps.executeUpdate();

            System.out.println("Expense Added Successfully");

        } catch(Exception e) {
            System.out.println(e);
        }
    }
   public void viewExpenses() {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM expenses";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("category") + " | " +
                        rs.getDouble("amount") + " | " +
                        rs.getString("date")
                );
            }

        } catch(Exception e) {
            System.out.println(e);
        }
    }
   public void deleteExpense(int id) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "DELETE FROM expenses WHERE id=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if(rows > 0)
                System.out.println("Deleted Successfully");
            else
                System.out.println("Expense not found");

        } catch(Exception e) {
            System.out.println(e);
        }
    }
    public void searchByCategory(String category) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM expenses WHERE category=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, category);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("category") + " | " +
                        rs.getDouble("amount") + " | " +
                        rs.getString("date")
                );
            }

        } catch(Exception e) {
            System.out.println(e);
        }
    }
    public void searchByDate(String date) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM expenses WHERE date=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, date);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("category") + " | " +
                        rs.getDouble("amount") + " | " +
                        rs.getString("date")
                );
            }

        } catch(Exception e) {
            System.out.println(e);
        }
    }
    public void monthlyReport(String month) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT SUM(amount) AS total FROM expenses WHERE date LIKE ?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, "%-" + month + "-%");

            ResultSet rs = ps.executeQuery();

            if(rs.next())
                System.out.println("Monthly Total: " + rs.getDouble("total"));

        } catch(Exception e) {
            System.out.println(e);
        }
    }
    public void categoryReport() {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT category,SUM(amount) AS total FROM expenses GROUP BY category";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                System.out.println( rs.getString("category") + " : " + rs.getDouble("total")
                );
            }

        } catch(Exception e) {
            System.out.println(e);
        }
    }
    public void updateExpense(int id,double amount) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "UPDATE expenses SET amount=? WHERE id=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setDouble(1, amount);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if(rows > 0)
                System.out.println("Updated Successfully");
            else
                System.out.println("Expense not found");

        } catch(Exception e) {
            System.out.println(e);
        }
    }
    public void totalExpense() {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT SUM(amount) AS total FROM expenses";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            if(rs.next())
                System.out.println("Total Expense: " + rs.getDouble("total"));

        } catch(Exception e) {
            System.out.println(e);
        }
    }
}


    

