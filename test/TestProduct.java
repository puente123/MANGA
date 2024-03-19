package test;

import store.Product;
import store.Tool;
//import store.Plant;


public class TestProduct{
	
	public static void main(String[] args){

		
		
		int count = 0;

		Product p1 = new Tool("Eggs", 1020);
		Product p2 = new Tool("Cheese", 1575);


		//Test the first and second stock number
		if(p1.getStockNumber() != 0){
			System.err.println("ERROR: Product 1 stock number is not 0 it is: " + p1.getStockNumber());
			count++;
		}
		else if(p2.getStockNumber() != 1){
			System.err.println("ERROR: Product 2 stock number is not 1 it is: " + p2.getStockNumber());
			count++;
		}
		

		//Test Get Price
		if(p1.getPrice() != 1020){
			System.err.println("ERROR: Product 1 price not 10 but " + p1.getPrice());
			count++;
		}
		else if(p2.getPrice() != 1575){
			System.err.println("ERROR: Product 2 price not 15 but " + p2.getPrice());
			count++;
		}
		

		//Test Products toString method
		String outputString = "Eggs                           $   10.20";
		if(!p1.toString().equals(outputString)){
			System.err.println("ERROR: Wrong String Printed: " + p1.toString());
			count++;
		}

		//Test negative price
		try{
			Product p3 = new Tool("Ham", -40);
			System.err.println("ERROR: NO Exeption was thrown for a negative price");
			count++;
		}
		catch(IllegalArgumentException e){
			//Catches Exception from Product Class
		}
		catch (Exception e){
			System.err.println("ERROR: Exception besides Illegal Arguement thrown: " + e.getMessage());
			count++;
		}

		System.exit(count);

	}
}
