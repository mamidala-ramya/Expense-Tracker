import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ExpenseGUI extends JFrame {

    JTextField categoryField, amountField, dateField;
    JButton addButton;

    public ExpenseGUI() {

        setTitle("Expense Tracker");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4,2));

        add(new JLabel("Category"));
        categoryField = new JTextField();
        add(categoryField);

        add(new JLabel("Amount"));
        amountField = new JTextField();
        add(amountField);

        add(new JLabel("Date"));
        dateField = new JTextField();
        add(dateField);

        addButton = new JButton("Add Expense");
        add(addButton);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String category = categoryField.getText();
                double amount = Double.parseDouble(amountField.getText());
                String date = dateField.getText();

                Expense expense = new Expense(category, amount, date);
                ExpenseDAO dao = new ExpenseDAO();

                dao.addExpense(expense);

                JOptionPane.showMessageDialog(null,
                        "Expense Added Successfully");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new ExpenseGUI();
    }
}
