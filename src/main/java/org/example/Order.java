package org.example;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;



public class Order {

    static final Logger logger = Logger.getLogger(Order.class.getName());
    private Customer customer;
    protected List<Product> products;

    String date2;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    Calendar date1;



    public Order(Customer customer, List<Product> invoice, String date2) {
        this.customer = customer;
        this.date2 = date2;

         this.products = new ArrayList<>(invoice);

    }




    public Customer getCustomer() {
        return customer;
    }


    public List<Product> getProducts() {
        return products;
    }


}