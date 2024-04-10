package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class Product{
     	private static int nextStockNumber = 0;
     	private int stockNumber;
     	private String name;
     	private int price;

    	public Product(String name, int price){
			
			if(price < 0){
				throw new IllegalArgumentException("Invalid price of " + name + ": " + price);
			}          
			else{
				
				this.stockNumber = nextStockNumber++;
			
				this.name = name;
				this.price = price;
			}
	
     	}

		public Product(BufferedReader br) throws IOException{
			this.nextStockNumber = Integer.parseInt(br.readLine());
			this.stockNumber = Integer.parseInt(br.readLine());
			this.name = br.readLine();
			this.price = Integer.parseInt(br.readLine());
		}
		public void save(BufferedWriter bw) throws IOException{
			bw.write(nextStockNumber + "\n");
			bw.write(stockNumber + "\n");
			bw.write(name + "\n");
			bw.write(price + "\n");
		}


		//constructor for DB
		public Product(Connection connection) throws SQLException{
			//TODO NOT FINISHED
		}

		//saving to db method
		public void saveToDB(String tablePrefix, Connection connection) throws SQLException{

			// Table Initialization, String productTable = "CREATE TABLE IF NOT EXISTS " + tablePrefix + "products (id INT PRIMARY KEY, name VARCHAR(255), price INT, stockNumber INT, nextStockNumber INT, isPlant ENUM('true', 'false'), exposure VARCHAR(255)";

			//TODO NOT FINISHED
			String query = "INSERT INTO " + tablePrefix + "products (name, price, stockNumber, nextStockNumber) VALUES (?, ?, ?, ?)";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setString(1, name);
				preparedStatement.setInt(2, price);
				preparedStatement.setInt(3, stockNumber);
				preparedStatement.setInt(4, nextStockNumber);
				preparedStatement.executeUpdate();
			}
		}

     
     	public int getStockNumber(){
			return stockNumber;
     	}

		public int getPrice(){
			return price;
		}

		public String getName(){
			return name;
		}
		
		@Override
		public String toString(){

			int dollars = price/100;
			int cents = price % 100;

			return String.format("%-40s $%5d.%02d", name, dollars, cents);
		}     
}
