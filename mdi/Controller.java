package mdi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;


import store.Store;
//import store.View;
import store.Customer;
import store.Tool;
import store.Plant;
import store.Product;
import store.Exposure;

public class Controller {
    private Store store;
    private View view;
    private Menu mainMenu;
    private String output;
    private boolean isRunning;
    private Scanner in;
    private String filename;

    public Controller(String storeName){

        this.store = new Store(storeName);
        this.view = View.CUSTOMERS;
        this.in = new Scanner(System.in);
        this.output = "";
        this.isRunning = true;
        this.filename = "Untitled";

        this.mainMenu = new Menu();
        //Set up mainMenu ()-> things
        mainMenu.addMenuItem(new MenuItem("Place an Order", () -> placeOrder()));
        mainMenu.addMenuItem(new MenuItem("Add New Customer", () -> newCustomer()));
        mainMenu.addMenuItem(new MenuItem("Add New Tool", () -> newTool()));
        mainMenu.addMenuItem(new MenuItem("Add New Plant", () -> newPlant()));
        mainMenu.addMenuItem(new MenuItem("Switch View", () -> switchView()));
        mainMenu.addMenuItem(new MenuItem("Open", ()-> open()));
        mainMenu.addMenuItem(new MenuItem("Save", () -> save()));
        mainMenu.addMenuItem(new MenuItem("Save as", () -> saveAs()));
        mainMenu.addMenuItem(new MenuItem("Exit", () -> exit()));
    }

    public void mdi(){
        
        Integer i = null;
        while(isRunning == true){
            /*Have user selectFromMenu
             *clear ouput
             *execute their selection via the Menu.run method
             *Be sure to wrap the loop body in a try / catch so that you don't abort
             */
            
            try{
                //Select an item from the menu
                i = selectFromMenu();
                output = "";
                if(i == null){
                    continue;
                }
                if(i == -1){
                    testData();
                }
                else{
                    mainMenu.run(i);
                }
            }
            catch(Exception e){
                e.printStackTrace();
                getString("Press Enter to continue");
                print("#### Error: " + e.getMessage());
            }
            

        }
    }

    public void exit(){
        isRunning = false;
    }

    public void placeOrder(){

        System.out.println("\nPlacing a New Order\n");


        //Prints customer list to terminal
        String list = store.getCustomerList();
        
        int customerNumber = getInt(list + "\nPick a Customer");


        //Turns customer to index

        int orderNumber = store.newOrder(customerNumber);


        while(true) {

            int product = getInt("\n" + store.getProductList() + "\nSelect Product (Type -1 to complete order)? ");
            if(product < 0){ 
                break;
            }

            int quantity = getInt("How many would you like? (Type -1 to select a different product)? ");
            if(quantity < 0) {
                continue;
            }

            store.addToOrder(orderNumber, product, quantity);
        }

        print("Created Order " + orderNumber);

        view = View.ORDERS;

    }

    public void newCustomer(){

        String name = getString("Please input the Customer's Name:");
        String email = getString("Please input the Customer's Email:");

        store.addCustomer(new Customer(name, email));
        //Report success via output, and set the view to list customers.
        output = "Success!";
        view = View.CUSTOMERS;

    }

    public void newTool(){
    
        String name = getString("Please input the Tools Name:");
        int toolPrice = getInt("Please input the Tool's Price:");


        store.addProduct(new Tool(name, toolPrice));
        //Report success via output, and set the view to list products.
        output = "Success!";
        view = View.PRODUCTS;
    }

    public void newPlant(){


        String name = getString("Please input the Plant's Name:");
        String exposure = getString("Please input the Plant's Exposure:");
        Exposure plantExposure = Exposure.valueOf(exposure.toUpperCase());
        int plantPrice = getInt("Please input the Plant's Price:");

        store.addProduct(new Plant(name, plantPrice, plantExposure));
        //Report success via output, and set the view to list products.
        output = "Success!";
        view = View.PRODUCTS;
    }

    public void switchView() {

        System.out.println("Available Views: ");
        for (View v : View.values()) {
            System.out.println(v.name());
        }
    
        String input = getString("Enter the view to switch to: ");
    
        try {
            view = View.valueOf(input.toUpperCase());
            System.out.println("Switched to view: " + view);
        } 
        catch(IllegalArgumentException e){
            System.out.println("Invalid view entered!");
        }

    }


    public void open(){
        filename = getString("Enter the filename you would like to open:");
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            this.store = new Store(br);
        }
        catch(Exception e){
            System.err.println("Failed to read: " +  e);
        }
    }

    public void saveAs(){
        String newFilename = getString("Enter the Filename to Save to:");

        if(newFilename.isEmpty()){
            return;
        }
        filename = newFilename;
        save();
    }

    public void save(){
        
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename))){
            store.save(bw);
        }
        catch(Exception e){
            System.err.println("Failed to save: " + e);
        }
    }



    public String getView() {

        String result = "INVALID VIEW";

        if(view == View.CUSTOMERS){
             result = store.getCustomerList();
        }
        if(view == View.PRODUCTS){
            result = store.getProductList();
        }
        if(view == View.ORDERS){    
            result = store.getOrderList();
        }

        return result;
    }

    public String clearScreen = "\n".repeat(255);

    public Integer selectFromMenu(){
        
        /*This clears the screen and prints the store name, "Main Menu" or other suitable title
as you prefer, the main menu, data based on the view (customers, products, or orders), and the output.
Then it getInt from the user as the main menu selection. (It's fine to have off-menu selections such as
"load test data" if you prefer.) */

        System.out.println(clearScreen + store.getName() + "\nMain Menu\n\n" + mainMenu + "\n" + getView() + "\n" + output + "\n");

        output = "";

        return getInt("Option: ");

        //FINISH SELECT FROM MENU

    }

    public void print(String s){
        //appends s to output
        output = output + s + '\n';
        
    }



    public String getString(String prompt){

        String input = null;

        while(true){
            try{
                System.out.println(prompt);
                input = in.nextLine();
                input = input.trim();
                break; 
            }
            catch(Exception e){
                System.err.println("Invalid input!");
            }
        }
        
        return input;
        
    }

    
    public Integer getInt(String prompt){

        Integer number = null;
        
        while(true){
            try{
                String result = getString(prompt);
                number = Integer.parseInt(result);
                break;//breaks out of while loop
            }
            catch(Exception e){
                System.err.println("Invalid input!");
            }
        }
        
        return number;
    }

    /*private Double getDouble(String prompt){

        Double number = null;

        while(true){
            try{
                String result = getString(prompt);
                number = Double.valueOf(result);
                break;
            }
            catch(Exception e){
                System.err.println("Invalid input!");
            }
        }
        
        return number;
    }*/


    public void testData() {
            Customer c1 = new Customer("Prof Rice", "george.rice@uta.edu");
            Customer c2 = new Customer("President Joe Biden", "president@whitehouse.gov");
            Customer c3 = new Customer("The Late Queen Elizabeth II", "queen@royal.gov.uk");
            Customer c4 = new Customer("Mark Zuckerberg", "mark.zuckerberg@facebook.com");
            store.addCustomer(c1);
            store.addCustomer(c2);
            store.addCustomer(c3);
            store.addCustomer(c4);

            Product p1 = new Plant("Cactus Cereus Peruvianus", 4990, Exposure.SUN);
            Product p2 = new Plant("'White Princess' Philodendron",5500, Exposure.PARTSUN);
            Product p3 = new Tool("Bypass Pruners", 2299);
            Product p4 = new Tool("Large Gardener's Cart", 34900);
            store.addProduct(p1);
            store.addProduct(p2);
            store.addProduct(p3);
            store.addProduct(p4);
            
            int order = store.newOrder(0);
            store.addToOrder(order, 0, 4);
            store.addToOrder(order, 1, 3);
            store.addToOrder(order, 2, 2);
            store.addToOrder(order, 3, 1);
/*
            Item i1 = new Item(p1, 4);
            Item i2 = new Item(p2, 3);
            Item i3 = new Item(p3, 2);
            Item i4 = new Item(p4, 1);

            Order o1 = new Order(c1);
            o1.addItem(i1);
            o1.addItem(i2);
            o1.addItem(i3);
            o1.addItem(i4);
            store.addOrder(o1);
*/
    }


}
