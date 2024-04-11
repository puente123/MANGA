package store;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Order{

    private static int nextOrderNumber = 1;
    private int orderNumber;
    private ArrayList<Item> items;
    private Customer customer;

    public Order(Customer customer){
        this.customer = customer;
        this.orderNumber = nextOrderNumber++;
        this.items = new ArrayList<>();
    }

    public Order(BufferedReader br) throws IOException{

        this.items = new ArrayList<>();
        this.nextOrderNumber = Integer.parseInt(br.readLine());
        this.orderNumber = Integer.parseInt(br.readLine());

        int size = Integer.parseInt(br.readLine());
        for(int i=size; i>0; i--){
            //addItem(new Item(br));
            items.add(new Item(br));
        }

        this.customer = new Customer(br);

    }

    public void save(BufferedWriter bw) throws IOException{
        bw.write(nextOrderNumber + "\n");
        bw.write(orderNumber + "\n");

        bw.write(items.size() + "\n");
        for(Item current : items){
            current.save(bw);
        }

        customer.save(bw);
    }


    //constructor for DB
    public Order(Connection connection) throws SQLException{
        //TODO NOT FINISHED
    }

    //saving to db method
    public void saveToDB(String tablePrefix, Connection connection) throws SQLException{
        //TODO NOT FINISHED

        //Maybe change default value//This code gets customer ID
        Integer customerId = null;
        String query = "SELECT id FROM " + tablePrefix + "customers WHERE name = ? AND email = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getEmail());
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                customerId = rs.getInt("id");
            } else {
                // Customer not found, handle this case as needed
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle potential exceptions better in your application
        }


        //String orderTable = "CREATE TABLE IF NOT EXISTS " + tablePrefix + "orders (id INT PRIMARY KEY AUTO_INCREMENT, orderNumber INT, nextOrderNumber INT, FOREIGN KEY (customer_id) REFERENCES " + tablePrefix + "customers(id))";
        //String itemTable = "CREATE TABLE IF NOT EXISTS " + tablePrefix + "order_items( id INT PRIMARY KEY AUTO_INCREMENT, order_id INT, product_id INT, quantity INT, FOREIGN KEY (order_id) REFERENCES " + tablePrefix + "orders(id), FOREIGN KEY (product_id) REFERENCES " + tablePrefix + "products(id))";

        query = "INSERT INTO " + tablePrefix + "orders (orderNumber, nextOrderNumber, ) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, orderNumber);
            preparedStatement.setInt(2, nextOrderNumber);
            preparedStatement.setInt(3, customerId);
            preparedStatement.executeUpdate();


            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            int orderId = -1;
            if (generatedKeys.next()) {
                orderId = generatedKeys.getInt(1);
            } else {
                // Handle no generated key
                System.out.println("Generated Key not found: Order.java");
            }

        // Save items
        for (Item current : items) {
            current.saveToDB(tablePrefix, connection, orderId);
        }

		}




        /*bw.write(items.size() + "\n");
        for(Item current : items){
            current.saveToDB(tablePrefix, connection);
        }*/


    }

    public void addItem(Item item){
        items.add(item);
    }

    public int getPrice(){

        int total = 0;
        for(int i=0; i<items.size(); i++){
            Item item = items.get(i);

            total = total + item.getPrice();
        }
        return total;
    }


    //might change
    @Override
    public String toString(){
        StringBuilder reciept = new StringBuilder();

        reciept.append(String.format("Order #%d for %s\n", orderNumber, customer.toString()));

        /*for(int i=0; i<items.size(); i++){
            reciept.append(String.format("%s\n", items.toString()));
        }*/

        for(Item item : items) {
            reciept.append("\n  " + item);
        }

        reciept.append(String.format("\nOrder Total $%5d.%02d", getPrice()/100, getPrice()%100));
        
        String finalReciept = reciept.toString();
        return finalReciept;

    }
}