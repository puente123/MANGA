package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;

/*import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;*/


import store.Customer;
import store.Exposure;
import store.Plant;
import store.Store;
import store.Tool;

//MainWin extends JFrame

    

public class MainWin extends JFrame{

    private Store store;
    private JLabel display;
    private View view;
    private String output;



    public MainWin(String storeName) {
        super(storeName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);

        //Menu
        //Create Menu Bar
        JMenuBar menuBar = new JMenuBar();

        //Link Menu Bar Together
        JMenu file = new JMenu ("File");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem saveAs = new JMenuItem("Save As");
        JMenuItem quit = new JMenuItem("Quit");

        JMenu options = new JMenu("Options");
        JMenuItem addCustomer = new JMenuItem("Add a New Customer");
        JMenuItem addTool = new JMenuItem("Add a New Tool");
        JMenuItem addPlant = new JMenuItem("Add a New Plant");
        JMenuItem addOrder = new JMenuItem("Create a New Order");
        JMenuItem viewCustomers = new JMenuItem("View Customers");
        JMenuItem viewOrders = new JMenuItem("View Orders");
        JMenuItem viewProducts = new JMenuItem("View Products");

        JMenu help = new JMenu("Help");
        JMenuItem about= new JMenuItem("About");


        quit.addActionListener(event -> onQuitClick());
        save.addActionListener(event -> onSaveClick());
        saveAs.addActionListener(event -> onSaveAsClick());

        addCustomer.addActionListener(event -> onInsertCustomerClick());
        addOrder.addActionListener(event -> onInsertOrderClick());
        addTool.addActionListener(event -> onInsertToolClick());
        addPlant.addActionListener(event -> onInsertPlantClick());
        addOrder.addActionListener(event -> onInsertOrderClick());
        viewCustomers.addActionListener(event -> onViewCustomerClick());
        viewOrders.addActionListener(event -> onViewOrdersClick());
        viewProducts.addActionListener(event -> onViewProductsClick());

        //help.addActionListener(even -> OnHelpClick());
        about.addActionListener(event -> onAboutClick());

        file.add(save);
        file.add(saveAs);
        file.add(quit);
        
        options.add(addCustomer);
        options.add(addTool);
        options.add(addPlant);
        options.add(addOrder);
        options.add(viewCustomers);
        options.add(viewOrders);
        options.add(viewProducts);
        

        help.add(about);

        menuBar.add(file);
        menuBar.add(options);
        menuBar.add(help);

        setJMenuBar(menuBar);


        //display;



    

        this.store = new Store(storeName);


        


        this.display = new JLabel();
        //display.add();



        this.view = View.CUSTOMERS;

        
        setVisible(true);

    }

    protected void onNewClick(){
        
    }

    protected void onOpenClick(){



    }

    protected void onSaveClick(){

    }
    
    protected void onSaveAsClick(){

    }

    protected void onQuitClick(){
        System.exit(0);
    }

    protected void onInsertCustomerClick(){

        //JLabel label = new JLabel("Inserting New Customer");

        //name of customer
        JLabel name = new JLabel("<HTML><br/>Name</HTML>");
        JTextField nameInput = new JTextField(20);

        JLabel email = new JLabel("<HTML><br/>Email</HTML>");
        JTextField emailInput = new JTextField(20);

        Object[] objects = {
            name, nameInput,
            email, emailInput,
        };

        int button = JOptionPane.showConfirmDialog(
            this,
            objects,
            "Creating New Customer Account",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE
            );
        

        if(button == JOptionPane.OK_OPTION){

            String nameString = nameInput.getText(); // Get text from the text field
            String emailString = emailInput.getText();
            //JOptionPane.showMessageDialog(frame, "You entered: " + userInput);
            store.addCustomer(new Customer(nameString, emailString));
            view = View.CUSTOMERS;
            output = "Success!";

            JOptionPane.showMessageDialog(this, output + "\n" + getView());

        }

        /*JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameString = nameInput.getText(); // Get text from the text field
                String emailString = emailInput.getText();
                //JOptionPane.showMessageDialog(frame, "You entered: " + userInput);
                store.addCustomer(new Customer(nameString, emailString));
            }
        });*/
        

        //String name = getString("Please input the Customer's Name:");
        //String email = getString("Please input the Customer's Email:");

        //store.addCustomer(new Customer(nameString, emailString));
        //Report success via output, and set the view to list customers.
        //TODO add string output field output = "Success!";
        //view = View.CUSTOMERS;

    }

    

    protected void onInsertToolClick(){

        JLabel name = new JLabel("<HTML><br/>Tool Name</HTML>");
        JTextField nameInput = new JTextField(20);

        JLabel toolPrice = new JLabel("<HTML><br/>Price</HTML>");
        JTextField priceInput = new JTextField(20);

        Object[] objects = {
            name, nameInput,
            toolPrice, priceInput
        };

        int button = JOptionPane.showConfirmDialog(
            this,
            objects,
            "Creating New Tool",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE
            );
        

        if(button == JOptionPane.OK_OPTION){

            String nameString = nameInput.getText(); // Get text from the text field
            String priceString = priceInput.getText();
            int price = Integer.parseInt(priceString);
            //JOptionPane.showMessageDialog(frame, "You entered: " + userInput);
            store.addProduct(new Tool(nameString, price));
            view = View.PRODUCTS;
            output = "Success!";

            //todo maybe add view.toString() before getView
            JOptionPane.showMessageDialog(this, "Success!\n" + getView());

        }


        //Chat Version
        /*if (button == JOptionPane.OK_OPTION) {
            String nameString = nameInput.getText();
            String priceString = priceInput.getText();
            
            try {
                int price = Integer.parseInt(priceString);
                store.addProduct(new Tool(nameString, price));
                view = View.PRODUCTS;
                output = "Success!";
                JOptionPane.showMessageDialog(this, "Success!\n" + getView());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid price. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }*/
    
    }

    protected void onInsertPlantClick(){

        JLabel name = new JLabel("<HTML><br/>Plant Name</HTML>");
        JTextField nameInput = new JTextField(20);

        JLabel exposure= new JLabel("<HTML><br/>Exposure</HTML>");
        JTextField exposureInput = new JTextField(20);

        JLabel plantPrice = new JLabel("<HTML><br/>Price</HTML>");
        JTextField priceInput = new JTextField(20);

        Object[] objects = {
            name, nameInput,
            exposure, exposureInput,
            plantPrice, priceInput
        };

        int button = JOptionPane.showConfirmDialog(
            this,
            objects,
            "Creating New Plant",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE
            );
        

        if(button == JOptionPane.OK_OPTION){

            //Remember to edit all string completely before passing
            String nameString = nameInput.getText(); // Get text from the text field
            String exposureString = exposureInput.getText().toUpperCase();
            String priceString = priceInput.getText();
            Exposure plantExposure = Exposure.valueOf(exposureString);
            int price = Integer.parseInt(priceString);
            //JOptionPane.showMessageDialog(frame, "You entered: " + userInput);
            store.addProduct(new Plant(nameString, price, plantExposure));
            store.addProduct(new Plant("Acorn", 45, Exposure.valueOf("SUN")));
            view = View.PRODUCTS;
            output = "Success!";

            //todo maybe add view.toString() before getView
            JOptionPane.showMessageDialog(this, "Success!\n" + getView());

        }
    
    }
    
    private static int orderNumber;
    protected void onInsertOrderClick(){

        JPopupMenu popup = new JPopupMenu("Placing a New Order");

        //creating buttons and text fields for popup
        JButton exit = new JButton("Finish Order");
        JButton cancel = new JButton("Cancel");
        JButton insert = new JButton("Add to Order");
        JButton okay = new JButton("Select Customer"); //might not need okay button

        String customerList = store.getCustomerList();
        JTextArea customers = new JTextArea(customerList);
        JLabel name = new JLabel("<HTML><br/>Customer</HTML>");
        JTextField nameInput = new JTextField(20);

        String productList = store.getProductList();
        JTextArea products = new JTextArea(productList);
        JLabel product= new JLabel("<HTML><br/>Product</HTML>");
        JTextField productInput = new JTextField(20);
    
        JLabel quantity = new JLabel("<HTML><br/>Quantity</HTML>");
        JTextField quantityInput = new JTextField(20);

        JPanel panel = new JPanel(new GridLayout(0, 2));

        
        //Setting up popop
        popup.add(customers);
        popup.add(name);
        popup.add(nameInput);
        popup.add(okay);
        popup.add(exit);


        popup.setVisible(true);
        
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // This code will be executed when the button is clicked
                popup.setVisible(false);
            }
        });

        

        okay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // This code will be executed when the button is clicked
                String customerString = nameInput.getText();
                int customerNumber = Integer.parseInt(customerString);
                //Turns customer to index
                orderNumber = store.newOrder(customerNumber);
            }
        });
        
        insert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // This code will be executed when the button is clicked
                
                String productString = productInput.getText();
                int productInt = Integer.parseInt(productString);
                String quantityString = quantityInput.getText();
                int quantityInt = Integer.parseInt(quantityString);
                store.addToOrder(orderNumber, productInt, quantityInt);
            }
        });

        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // This code will be executed when the button is clicked
                popup.setVisible(false);
                view = View.PRODUCTS;
                output = "Success!";

                //todo maybe add view.toString() before getView
                JOptionPane.showMessageDialog(MainWin.this, "Success!\n" + getView());
            }
        });

        /*System.out.println("\nPlacing a New Order\n");
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
        view = View.ORDERS;*/

    }

    protected void onViewCustomerClick(){
        view = View.CUSTOMERS;
        JOptionPane.showMessageDialog(this, getView(), "Customers", JOptionPane.PLAIN_MESSAGE);
    }

    protected void onViewProductsClick(){
        view = View.PRODUCTS;
        JOptionPane.showMessageDialog(this, getView(), "Products", JOptionPane.PLAIN_MESSAGE);
    }

    protected void onViewOrdersClick(){
        view = View.CUSTOMERS;
        JOptionPane.showMessageDialog(this, getView(), "Orders", JOptionPane.PLAIN_MESSAGE);
    }

    protected void onHelpClick(){

    }

    protected void onAboutClick(){

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
}
