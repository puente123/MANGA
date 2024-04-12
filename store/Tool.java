package store;

import java.io.BufferedReader;
import java.io.IOException;

import java.sql.*;

public class Tool extends Product{

	public Tool(String name, int price){
		super(name, price); //format is suppsed to match constructer of product
	}

	public Tool(BufferedReader br) throws IOException{
		super(br);
	}

	//constructor for DB
	public Tool(ResultSet result, Connection connection) throws SQLException{
		super(result, connection);
	}


}
