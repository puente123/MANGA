package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

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
			this.nextStockNumber = Integer.parseInt(br.readLine());//TODO check static reading from file
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
     
     	public int getStockNumber(){
			return stockNumber;
     	}

		public int getPrice(){
			return price;
		}
		
		@Override
		public String toString(){

			int dollars = price/100;
			int cents = price % 100;

			return String.format("%-40s $%5d.%02d", name, dollars, cents);
		}     
}
