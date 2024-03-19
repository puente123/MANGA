package store;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Order{

    private static int nextOrderNumber = 1;
    private int orderNumber;
    private ArrayList<Item> items;
    private Customer customer;

    public Order(Customer customer){
        this.customer = customer;
        this.orderNumber = nextOrderNumber++;
        this.items = new ArrayList<>();
    }

    public Order(BufferedReader br) throws IOException{

        this.nextOrderNumber = Integer.parseInt(br.readLine());
        this.orderNumber = Integer.parseInt(br.readLine());

        int size = Integer.parseInt(br.readLine());
        for(int i=size; i>0; i--){
            addItem(new Item(br));
        }

        this.customer = new Customer(br);

    }

    public void save(BufferedWriter bw) throws IOException{
        bw.write(nextOrderNumber + "\n");
        bw.write(orderNumber + "\n");

        bw.write(items.size() + "\n");
        for(Item current : items){
            current.save(bw);
        }

        customer.save(bw);
    }

    public void addItem(Item item){
        items.add(item);
    }

    public int getPrice(){

        int total = 0;
        for(int i=0; i<items.size(); i++){
            Item item = items.get(i);

            total = total + item.getPrice();
        }
        return total;
    }


    //might change
    @Override
    public String toString(){
        StringBuilder reciept = new StringBuilder();

        reciept.append(String.format("Order #%d for %s\n", orderNumber, customer.toString()));

        /*for(int i=0; i<items.size(); i++){
            reciept.append(String.format("%s\n", items.toString()));
        }*/

        for(Item item : items) {
            reciept.append("\n  " + item);
        }

        reciept.append(String.format("\nOrder Total $%5d.%02d", getPrice()/100, getPrice()%100));
        
        String finalReciept = reciept.toString();
        return finalReciept;

    }
}