import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ExpenseDAO dao = new ExpenseDAO();
        ExpenseService service = new ExpenseService();

        while(true) {

            System.out.println("\n1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Delete Expense");
            System.out.println("4. Search Category");
            System.out.println("5. Search Date");
            System.out.println("6. Monthly Report");
            System.out.println("7. Category Report");
            System.out.println("8. Update Expense");
            System.out.println("9. Export CSV");
            System.out.println("10. Exit");

            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice) {

                case 1:

                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Category: ");
                    String category = sc.nextLine();

                    System.out.print("Amount: ");
                    double amount = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Date: ");
                    String date = sc.nextLine();

                    dao.addExpense(
                        new Expense(
                           id,category,amount,date
                        )
                    );
                    break;

                case 2:
                    dao.viewExpenses();
                    break;

                case 3:
                    System.out.print("Enter ID: ");
                    dao.deleteExpense(sc.nextInt());
                    break;
                
                case 4:
                    sc.nextLine();
                    System.out.print("Category: ");
                    dao.searchByCategory(sc.nextLine());
                    break;
                
                case 5:
                    sc.nextLine();
                    System.out.print("Date: ");
                    dao.searchByDate(sc.nextLine());
                    break;

                case 6:
                    sc.nextLine();
                    System.out.print("Month (MM): ");
                    dao.monthlyReport(sc.nextLine());
                    break;

                case 7:
                    service.categoryReport();
                    break;
                case 8:
                   System.out.print("Enter ID: ");
                   int id1 = sc.nextInt();
                   System.out.print("New Amount: ");
                   double amt = sc.nextDouble();
                   service.updateExpense(id1, amt);
                   break;
                case 9:
                   CSVExporter csv = new CSVExporter();
                   csv.exportToCSV();
                   break;
                case 10:
                    sc.close();
                    System.exit(0);
            }
        }
    }
}
