package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

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
