package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSchemaManager {

    public static void createTables(String tablePrefix, Connection connection) {
        try (Statement statement = connection.createStatement()) {

            // Prefix for tables based on store name
            //String tablePrefix = storeName.toLowerCase() + "_";

            // Create unique tables for this store
            //String customerTable = "CREATE TABLE IF NOT EXISTS " + tablePrefix + "customers (id INT PRIMARY KEY, name VARCHAR(255), email VARCHAR(255))";
            String customerTable = "CREATE TABLE IF NOT EXISTS " + tablePrefix + "customers (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), email VARCHAR(255))";

            //String productTable = "CREATE TABLE IF NOT EXISTS " + tablePrefix + "products (id INT PRIMARY KEY, name VARCHAR(255), price DECIMAL(10,2))";
            //String orderTable = "CREATE TABLE IF NOT EXISTS " + tablePrefix + "orders (id INT PRIMARY KEY, name VARCHAR(255), price DECIMAL(10,2))";
            // Add more tables as needed

            // Execute the SQL statements
            statement.executeUpdate(customerTable);
            //statement.executeUpdate(productTable);
            //statement.executeUpdate(orderTable);
            // Execute more statements for other tables

            //System.out.println("Tables customer created for store: " + storeName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}