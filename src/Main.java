import java.sql.*;
import java.util.Scanner;

//Java database connectivity with siva
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Scanner sc = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/REGISTER";
        String userName = "root";
        String password = "root";

        // Create a query to fetch data from  to mysql
        String query = getQuery(sc);

        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement statement = connection.createStatement();


        System.out.println("Enter the process need to be done:" +
                "\n1. Fetch name of the students: ExampleCommand: (select nameOfStudent from student;)" +
                "\n2. Insert values into student: ExampleCommand: (insert into student values (10, 'Krish');" +
                "\n3. Insert values into student using prepared statement: ExCommand: (insert into student values (?, ?); "
        );
        int process = sc.nextInt();
        if (process == 1) {
            ResultSet resultSet = statement.executeQuery(query);
            //create a method to print output select * from table
            printFetchDataAsOutput(resultSet);

        } else if (process == 2) {
            int noOfRowsAffected = statement.executeUpdate(query);
            System.out.println(noOfRowsAffected + " number of row/s affected!");
        } else if (process == 3) {
            preparedStatementQueryForInsertingValues(sc, query, connection);

        }


        statement.close();
        connection.close();
    }

    private static void preparedStatementQueryForInsertingValues(Scanner sc, String query, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        System.out.println("Enter the total number of rows to be added: ");
        int n = sc.nextInt();
        int numberOfRowsAffected = 0;
        for (int i = 0; i < n; i++) {
            System.out.println("Enter roll_number:");
            preparedStatement.setInt(1, sc.nextInt());
            System.out.println("enter student name:");
            sc.nextLine();
            preparedStatement.setString(2, sc.nextLine());
            numberOfRowsAffected = preparedStatement.executeUpdate();
            numberOfRowsAffected++;
        }
        System.out.println(numberOfRowsAffected + " number of row/s affected!");
    }

    private static void printFetchDataAsOutput(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String name = resultSet.getString(1);
            System.out.println(name);
        }
    }

    private static String getQuery(Scanner sc) {
        System.out.println("Enter the Query Statement:");
        String query = sc.nextLine();
        return query;
    }
}