package gui;

public enum View { 
    CUSTOMERS("Customers"), PRODUCTS("Products"), ORDERS("Orders");


private String description;

private View(String description){
    this.description = description;
}

@Override
public String toString(){

    return description;

}

}
package gui;

public enum View { 
    CUSTOMERS("Customers"), PRODUCTS("Products"), ORDERS("Orders");


private String description;

private View(String description){
    this.description = description;
}

@Override
public String toString(){

    return description;

}

}