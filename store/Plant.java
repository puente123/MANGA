package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

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
    public Plant(Connection connection) throws SQLException{
        //TODO NOT FINISHED
		super(connection);

    }

    //saving to db method
    public void saveToDB(Connection connection) throws SQLException{
        //TODO NOT FINISHED
    }
	
	
	public Exposure getExposure(){
		return exposure;
	}
}
