package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Item{
	
	private Product product;
	private int quantity;

	public Item(Product product, int quantity){
		this.product = product;
		this.quantity = quantity;	
	}

	public Item(BufferedReader br) throws IOException{

		String subclass = br.readLine();

		if(subclass.equals("store.Plant")){
			this.product = new Plant(br);
		} 
		else if(subclass.equals("store.Tool")){
			this.product = new Tool(br);
		}
		else{
			throw new IOException("Wrong Product subclass: " + subclass);
		}

		this.quantity = Integer.parseInt(br.readLine());
	}

	public void save(BufferedWriter bw) throws IOException{
		bw.write(product.getClass().getName() + "\n");
		product.save(bw);
		bw.write(quantity + "\n");
	}

	//constructor for DB
	public Item(Connection connection) throws SQLException{
		//TODO NOT FINISHED
	}

	//saving to db method
	public void saveToDB(String tablePrefix, Connection connection) throws SQLException{
		//TODO NOT FINISHED
		//get order id
		//get product id
		product.getName();
		product.getPrice();

		//Maybe change default value//This code gets product ID
        Integer productId = null;
        String query = "SELECT id FROM " + tablePrefix + "products WHERE name = ? AND price = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, product.getName());
            pstmt.setInt(2, product.getPrice());
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                productId = rs.getInt("id");
            } else {
                // Customer not found, handle this case as needed
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle potential exceptions better in your application
        }

		//String orderTable = "CREATE TABLE IF NOT EXISTS " + tablePrefix + "orders (id INT PRIMARY KEY AUTO_INCREMENT, orderNumber INT, nextOrderNumber INT, customer_id INT,  FOREIGN KEY (customer_id) REFERENCES " + tablePrefix + "customers(id))";
        
		//String itemTable = "CREATE TABLE IF NOT EXISTS " + tablePrefix + "order_items( id INT PRIMARY KEY AUTO_INCREMENT, order_id INT, product_id INT, quantity INT, FOREIGN KEY (order_id) REFERENCES " + tablePrefix + "orders(id), FOREIGN KEY (product_id) REFERENCES " + tablePrefix + "products(id))";


		query = "INSERT INTO " + tablePrefix + "order_items (order_id, product_id, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, productId);
			preparedStatement.setInt(3, quantity);
			
            preparedStatement.executeUpdate();
		}


	}

	public int getPrice(){
		return (product.getPrice()*quantity);
	}


	@Override
	public String toString(){

		int dollars = getPrice() / 100;
		int cents = getPrice() % 100;

		return String.format("%3d %-40s $%5d.%02d", quantity, product, dollars, cents);

	}


}
