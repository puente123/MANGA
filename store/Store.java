package store;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Store {
    private String name;
    private ArrayList<Customer> customers;
    private ArrayList<Product> products;
    private ArrayList<Order> orders;

    public Store(String name){
        this.name = name;
        this.customers = new ArrayList<>();
        this.products= new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    //TODO Implement reading from file constructor
    public Store(BufferedReader br) throws IOException{

        this.customers = new ArrayList<>();
        this.products= new ArrayList<>();
        this.orders = new ArrayList<>();

        this.name = br.readLine();

        Integer size1 = Integer.parseInt(br.readLine()); 
        for(int i = size1; i>0; i--){
            
            addCustomer(new Customer(br));
        }

        Integer size2 = Integer.parseInt(br.readLine()); 
        for(int i = size2; i>0; i--){
            
            String subclass = br.readLine();
            if(subclass.equals("store.Plant")){
                //products.add(new Plant(br));
                addProduct(new Plant(br));
            } 
            else if(subclass.equals("store.Tool")){
                //products.add(new Tool(br));
                addProduct(new Tool(br));
            }

        }

    }

    public void save(BufferedWriter bw) throws IOException{

        bw.write(name + '\n');

        bw.write(customers.size() + "\n");
        for(Customer current : customers){
            current.save(bw);
        }

        bw.write(products.size() + "\n");
        for(Product current : products){
            bw.write(current.getClass().getName() + '\n');
            current.save(bw);
        }

        bw.write(orders.size() + "\n");
        for(Order current : orders){
            current.save(bw);
        }

    }


    public String getName(){
        return name;
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public String getCustomerList() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<customers.size(); ++i) 
            sb.append(String.format("%3d] %s\n", i, customers.get(i)));
        return sb.toString();
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public String getProductList() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<products.size(); ++i) 
            sb.append(String.format("%3d] %s\n", i, products.get(i)));
        return sb.toString();
    }

    public int newOrder(int customerIndex){
        
        Order newOrder = new Order(customers.get(customerIndex)); // customers.get(customerIndex) returns customer at index A[i] // This is sent as a parameter to order and creates a new order
        orders.add(newOrder);
        return orders.indexOf(newOrder);
    }

    public void addToOrder(int orderIndex, int productIndex, int quantity){
        Item newItem = new Item(products.get(productIndex), quantity);
        Order addOrder = orders.get(orderIndex);
        addOrder.addItem(newItem);
    }

    public String getOrderList(){
        StringBuilder list = new StringBuilder();

        for(Order order : orders){
            list.append(order.toString()+"\n");
        }

        return list.toString();
    }

}
