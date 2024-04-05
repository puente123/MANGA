package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

            JOptionPane.showMessageDialog(this, "Success!\n" + getView());

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

            String nameString = nameInput.getText(); // Get text from the text field
            String exposureString = exposureInput.getText();
            Exposure plantExposure = Exposure.valueOf(exposureString.toUpperCase());
            int price = Integer.parseInt(plantPrice.getText());
            //JOptionPane.showMessageDialog(frame, "You entered: " + userInput);
            store.addProduct(new Plant(nameString, price, plantExposure));
            view = View.PRODUCTS;
            output = "Success!";

            //todo maybe add view.toString() before getView
            JOptionPane.showMessageDialog(this, output + "\n" + getView());

        }
    
    

    }

    protected void onInsertOrderClick(){



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
