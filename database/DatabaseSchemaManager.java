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
            
            String customerTable = "CREATE TABLE IF NOT EXISTS " + tablePrefix + "customers (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), email VARCHAR(255))";

            String productTable = "CREATE TABLE IF NOT EXISTS " + tablePrefix + "products (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), price INT, stockNumber INT, nextStockNumber INT, isPlant ENUM('true', 'false'), exposure VARCHAR(255))";


            //TODO create if else for tool and plant
            
            String orderTable = "CREATE TABLE IF NOT EXISTS " + tablePrefix + "orders (id INT PRIMARY KEY AUTO_INCREMENT, orderNumber INT, nextOrderNumber INT, customer_id INT, FOREIGN KEY (customer_id) REFERENCES " + tablePrefix + "customers(id))";
        
            String itemTable = "CREATE TABLE IF NOT EXISTS " + tablePrefix + "order_items( id INT PRIMARY KEY AUTO_INCREMENT, order_id INT, product_id INT, quantity INT, FOREIGN KEY (order_id) REFERENCES " + tablePrefix + "orders(id), FOREIGN KEY (product_id) REFERENCES " + tablePrefix + "products(id))";

            // Add more tables as needed

            // Execute the SQL statements
            statement.executeUpdate(customerTable);
            statement.executeUpdate(productTable);
            //statement.executeUpdate(orderTable);
            statement.executeUpdate(orderTable);
            statement.executeUpdate(itemTable);
            // Execute more statements for other tables

            //System.out.println("Tables customer created for store: " + storeName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}