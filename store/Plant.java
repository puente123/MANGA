package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Plant extends Product{
	
	private Exposure exposure;

	public Plant(String species, int price, Exposure exposure){
		
		//Invoke super first	
		super("Plant: " + species, price);
		this.exposure = exposure;
		
	}

	public Plant(BufferedReader br) throws IOException{
		super(br);

		this.exposure = Exposure.valueOf(br.readLine());//TODO DOUBLE CHECKS THIS
	}
	public void save(BufferedWriter bw) throws IOException{
		super.save(bw);

		bw.write(exposure.name() + "\n");
	}
	
	public Exposure getExposure(){
		return exposure;
	}
}
