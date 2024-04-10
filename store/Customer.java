package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Customer{
	private String name;
	private String email;

	public Customer(String name, String email){
		
		//checks if email has an @
		int atIndex = email.indexOf('@');
		
		if(atIndex != -1){
			//checks if email has . after @
			int periodIndex = email.indexOf('.', atIndex);
			if(periodIndex != -1){
				this.name = name;
				this.email = email;
			}
			else{
				
				throw new IllegalArgumentException("Invalid email address missing . after @: " + email);
			}
		}
		else{
			throw new IllegalArgumentException("Invalid email address missing @: " + email);
		}
			
	}

	public Customer(BufferedReader br) throws IOException{
		this.name = br.readLine();
		this.email = br.readLine();
	}
	public void save(BufferedWriter bw) throws IOException{
		bw.write(name + "\n");
		bw.write(email + "\n");
	}

	//constructor for DB
	public Customer(Connection connection) throws SQLException{
		//TODO NOT FINISHED
	}

	//saving to db method
	public void saveToDB(String tablePrefix, Connection connection) throws SQLException{
		//TODO NOT FINISHED
		String query = "INSERT INTO " +tablePrefix + "customers (name, email) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
		}

	}

	@Override
	public String toString(){
		return name + "(" + email + ")";
	}
}

