package gui;


import javax.swing.*;

import database.DatabaseConnection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import store.Customer;
import store.Exposure;
import store.Plant;
import store.Store;
import store.Tool;


public class MainWin extends JFrame{

    private Store store;
    //private JLabel display;
    private View view;
    private String output;
    private String filename;


    public MainWin(String storeName) {
        super(storeName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);

        //Menu
        //Create Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JPanel mainPanel = new JPanel();
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JPanel buttonPanel2= new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setPreferredSize(new Dimension(600, 300));
        mainPanel.add(buttonPanel);
        mainPanel.add(buttonPanel2);
    

        //Link Menu Bar Together
        JMenu file = new JMenu ("File");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem saveAs = new JMenuItem("Save As");
        JMenuItem saveToDB = new JMenuItem("Save To DB");
        JMenuItem quit = new JMenuItem("Quit");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem openFromDB = new JMenuItem("Open From DB");

        /*JMenu options = new JMenu("Options");
        JMenuItem addCustomer = new JMenuItem("Add a New Customer");
        JMenuItem addTool = new JMenuItem("Add a New Tool");
        JMenuItem addPlant = new JMenuItem("Add a New Plant");
        JMenuItem addOrder = new JMenuItem("Create a New Order");
        JMenuItem viewCustomers = new JMenuItem("View Customers");
        JMenuItem viewOrders = new JMenuItem("View Orders");
        JMenuItem viewProducts = new JMenuItem("View Products");*/


        JButton addCustomerButton = new JButton("Add a New Customer");
        JButton addToolButton = new JButton("Add a New Tool");
        JButton addPlantButton = new JButton("Add a New Plant");
        JButton addOrderButton = new JButton("Create a New Order");
        JButton viewCustomersButton = new JButton("View Customers");
        JButton viewOrdersButton = new JButton("View Orders");
        JButton viewProductsButton = new JButton("View Products");

        JMenu help = new JMenu("Help");
        JMenuItem about= new JMenuItem("About");


        Dimension buttonSize = new Dimension(150, 50);
        addCustomerButton.setPreferredSize(buttonSize);
        addToolButton.setPreferredSize(buttonSize);
        addPlantButton.setPreferredSize(buttonSize);
        addOrderButton.setPreferredSize(buttonSize);
        viewCustomersButton.setPreferredSize(buttonSize);
        viewOrdersButton.setPreferredSize(buttonSize);
        viewProductsButton.setPreferredSize(buttonSize);


        //first row
        addCustomerButton.setBackground(Color.ORANGE);
        addToolButton.setBackground(Color.ORANGE);
        addPlantButton.setBackground(Color.ORANGE);
        addOrderButton.setBackground(Color.ORANGE);
        // Set foreground (text) color of buttons
        addCustomerButton.setForeground(Color.BLACK);
        addToolButton.setForeground(Color.BLACK);
        addPlantButton.setForeground(Color.BLACK);
        addOrderButton.setForeground(Color.BLACK);

        //second row
        viewCustomersButton.setBackground(Color.ORANGE);
        viewOrdersButton.setBackground(Color.ORANGE);
        viewProductsButton.setBackground(Color.ORANGE);

        // Set foreground (text) color of buttons
        viewCustomersButton.setForeground(Color.BLACK);
        viewOrdersButton.setForeground(Color.BLACK);
        viewProductsButton.setForeground(Color.BLACK);


        quit.addActionListener(event -> onQuitClick());
        save.addActionListener(event -> onSaveClick());
        saveAs.addActionListener(event -> onSaveAsClick());
        saveToDB.addActionListener(event -> onSaveToDBClick());
        open.addActionListener(event -> onOpenClick());
        openFromDB.addActionListener(event -> onOpenFromDBClick());

        /*addCustomer.addActionListener(event -> onInsertCustomerClick());
        addOrder.addActionListener(event -> onInsertOrderClick());
        addTool.addActionListener(event -> onInsertToolClick());
        addPlant.addActionListener(event -> onInsertPlantClick());
        addOrder.addActionListener(event -> onInsertOrderClick());
        viewCustomers.addActionListener(event -> onViewCustomerClick());
        viewOrders.addActionListener(event -> onViewOrdersClick());
        viewProducts.addActionListener(event -> onViewProductsClick());*/


        addCustomerButton.addActionListener(event -> onInsertCustomerClick());
        addOrderButton.addActionListener(event -> onInsertOrderClick());
        addToolButton.addActionListener(event -> onInsertToolClick());
        addPlantButton.addActionListener(event -> onInsertPlantClick());
        viewCustomersButton.addActionListener(event -> onViewCustomerClick());
        viewOrdersButton.addActionListener(event -> onViewOrdersClick());
        viewProductsButton.addActionListener(event -> onViewProductsClick());
    

        //help.addActionListener(even -> OnHelpClick());
        about.addActionListener(event -> onAboutClick());

        file.add(open);
        file.add(openFromDB);
        file.add(save);
        file.add(saveAs);
        file.add(saveToDB);
        file.add(quit);

        buttonPanel.add(addCustomerButton);
        buttonPanel.add(addToolButton);
        buttonPanel.add(addPlantButton);
        buttonPanel.add(addOrderButton);
        buttonPanel2.add(viewCustomersButton);
        buttonPanel2.add(viewOrdersButton);
        buttonPanel2.add(viewProductsButton);


        getContentPane().setBackground(Color.gray);
        buttonPanel.setBackground(Color.gray);
        buttonPanel2.setBackground(Color.gray);
        mainPanel.setForeground(Color.gray);
        
        String displayName = storeName + "'s Store";
        JLabel storeLabel = new JLabel(displayName, SwingConstants.CENTER);
        Font cursiveFont = new Font("Comic Sans MS", Font.BOLD, 40);
        storeLabel.setFont(cursiveFont);
        storeLabel.setForeground(Color.black);
        getContentPane().add(storeLabel, BorderLayout.CENTER);

        // Add panel to the frame
        //getContentPane().add(buttonPanel, BorderLayout.CENTER);
        //getContentPane().add(buttonPanel2, BorderLayout.CENTER);
        getContentPane().add(mainPanel, BorderLayout.SOUTH);

        /*options.add(addCustomer);
        options.add(addTool);
        options.add(addPlant);
        options.add(addOrder);
        options.add(viewCustomers);
        options.add(viewOrders);
        options.add(viewProducts);*/
        
        help.add(about);


        menuBar.add(file);
        //menuBar.add(options);
        menuBar.add(help);
        setJMenuBar(menuBar);


        this.store = new Store(storeName);
        this.filename = "untitled";
        //this.display = new JLabel();
        this.view = View.CUSTOMERS;

        setLocationRelativeTo(null);
        setVisible(true);

    }

    protected void onNewClick(){
        
    }

    protected void onSaveToDBClick(){

        try {
            Connection connection = DatabaseConnection.getConnection();
            store.saveToDB(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to save to database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    protected void onOpenFromDBClick(){

        //TODO currently database doesnt save store name it assumes the prefix of the database tables is the store name, which might not always be the case
        //also databaseName may not be able to have spaces
        String databaseName = JOptionPane.showInputDialog(null, "Enter the Database to Open:");

        try(Connection connection = DatabaseConnection.getConnection()){
            this.store = new Store(databaseName, connection);
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to open database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }

    }

    protected void onOpenClick(){

        filename = JOptionPane.showInputDialog(null, "Enter the filename you would like to open:");
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            this.store = new Store(br);
        }
        catch(Exception e){
            System.err.println("Failed to read: " +  e);
        }

    }

    protected void onSaveClick(){

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename))){
            store.save(bw);
        }
        catch(Exception e){
            System.err.println("Failed to save: " + e);
        }

    }

    
    protected void onSaveAsClick(){
    
        String newFilename = JOptionPane.showInputDialog(null, "Enter the Filename to Save to:");

        if(newFilename.isEmpty()){
            return;
        }
        filename = newFilename;
        onSaveClick();
    }


    protected void onQuitClick(){
        System.exit(0);
    }


    protected void onInsertCustomerClick(){

        //name of customer
        JLabel name = new JLabel("<HTML><br/>Name</HTML>");
        JTextField nameInput = new JTextField(20);

        JLabel email = new JLabel("<HTML><br/>Email</HTML>");
        JTextField emailInput = new JTextField(20);

        Object[] objects = {
            name, nameInput,
            email, emailInput,
        };

        int button = JOptionPane.showConfirmDialog(null, objects,"Creating New Customer Account", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        

        if(button == JOptionPane.OK_OPTION){

            String nameString = nameInput.getText(); // Get text from the text field
            String emailString = emailInput.getText();
            //JOptionPane.showMessageDialog(frame, "You entered: " + userInput);
            store.addCustomer(new Customer(nameString, emailString));
            view = View.CUSTOMERS;
            output = "Success!";

            JOptionPane.showMessageDialog(null, output + "\n" + getView());

        }
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
            null,
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
            JOptionPane.showMessageDialog(null, "Success!\n" + getView());

        }
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
            null,
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

            
            JOptionPane.showMessageDialog(null, "Success!\n" + getView());

        }
    
    }

    private static int orderNumber; 
    protected void onInsertOrderClick() {
        String customerList = store.getCustomerList();
        String customerString = JOptionPane.showInputDialog(null, customerList + "\nPick a Customer");
    
        if (customerString != null) {
            try {
                int customerNumber = Integer.parseInt(customerString);
                orderNumber = store.newOrder(customerNumber);
    
                // List to store product and quantity pairs
                List<Map.Entry<Integer, Integer>> productQuantityList = new ArrayList<>();
    
                boolean continueAddingProducts = true;
                while (continueAddingProducts) {
                    // Generate current order display
                    StringBuilder currentOrderBuilder = new StringBuilder();
                    currentOrderBuilder.append("Current Order:\n");

                    for (Map.Entry<Integer, Integer> entry : productQuantityList) {

                        int productNumber = entry.getKey(); // Get the product number (assuming it's an int)
                        int quantity = entry.getValue();    // Get the quantity

                        // Assuming you have a method to get the item name based on its index
                        String productName = store.getProductName(productNumber);
                        currentOrderBuilder.append(productName).append(": ").append(quantity).append("\n");
                    }

                    
    
                    String productList = store.getProductList();
                    String currentOrder = currentOrderBuilder.toString();
                    String[] options = {"Add Product", "Finish Order", "Cancel Order"};
                    int choice = JOptionPane.showOptionDialog(
                            null,
                            currentOrder,
                            "Select Product",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            options,
                            options[0]
                    );
    
                    if (choice == 0) {
                        // User chose "Add Product"
                        String productString = JOptionPane.showInputDialog(null, productList + "Enter Product ID");
                        if (productString != null) {
                            try {
                                int productInt = Integer.parseInt(productString);
                                String quantityString = JOptionPane.showInputDialog(null, "Enter Quantity for Product " + productInt);
                                if (quantityString != null) {
                                    try {
                                        int quantityInt = Integer.parseInt(quantityString);
                                        productQuantityList.add(new AbstractMap.SimpleEntry<>(productInt, quantityInt));
                                    } catch (NumberFormatException ex) {
                                        JOptionPane.showMessageDialog(null, "Invalid quantity. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                } else {
                                    // User cancelled quantity input
                                    continueAddingProducts = false;
                                }
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Invalid product. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            // User cancelled product input
                            continueAddingProducts = false;
                        }
                    } else if (choice == 1) {
                        // User chose "Finish Order"
                        continueAddingProducts = false;
                    } else if (choice == 2) {
                        // User chose "Cancel Order"
                        JOptionPane.showMessageDialog(null, "Order cancelled.");
                        return; // Exit the method
                    } else {
                        // User closed the dialog
                        continueAddingProducts = false;
                    }
                }
    
                // Adding products to order
                for (Map.Entry<Integer, Integer> entry : productQuantityList) {
                    store.addToOrder(orderNumber, entry.getKey(), entry.getValue());
                }
    
                view = View.ORDERS;
                output = "Success!";
                JOptionPane.showMessageDialog(null, "Order created successfully!\n" + getView());
    
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter numbers.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
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
        view = View.ORDERS;
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
