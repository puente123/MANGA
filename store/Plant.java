package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.*;


public class Plant extends Product{
	
	private Exposure exposure;

	public Plant(String species, int price, Exposure exposure){
		
		//Invoke super first	
		super("Plant: " + species, price);
		this.exposure = exposure;
		
	}

	public Plant(BufferedReader br) throws IOException{
		super(br);

		this.exposure = Exposure.valueOf(br.readLine());
	}
	public void save(BufferedWriter bw) throws IOException{
		super.save(bw);

		bw.write(exposure.name() + "\n");
	}

	//constructor for DB
    public Plant(ResultSet result, Connection connection) throws SQLException{
    
		super(result, connection);
		this.exposure = Exposure.valueOf(result.getString("exposure"));

    }

    //saving to db method
    public void saveToDB(String tablePrefix, Connection connection) throws SQLException{
        
		// Table Initialization, String productTable = "CREATE TABLE IF NOT EXISTS " + tablePrefix + "products (id INT PRIMARY KEY, name VARCHAR(255), price INT, stockNumber INT, nextStockNumber INT, isPlant ENUM('true', 'false'), exposure VARCHAR(255)";

		super.saveToDB(tablePrefix, connection);

		
		/*update DemoTable
   -> set Name='Robert'
   -> order by Id DESC limit 1; */


		//String query = "UPDATE " + tablePrefix + "products SET (isPlant, exposure) VALUES (?, ?) ORDER BY id DESC limit 1";
		String query = "UPDATE " + tablePrefix + "products SET isPlant = ?, exposure = ? ORDER BY id DESC limit 1";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setBoolean(1, true);
			preparedStatement.setString(2, (exposure.name()));
			
			preparedStatement.executeUpdate();
		}

    }
	
	
	public Exposure getExposure(){
		return exposure;
	}
}
