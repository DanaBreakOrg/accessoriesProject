package org.example;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import static org.example.Logging.y;

public class Order {

    final static Logger logger = Logger.getLogger(Order.class.getName());
    private Customer customer;
    public List<Product> products;

    String date2;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    Calendar date1;



    public Order(Customer customer, List<Product> invoice, String date2) {
        //super();
        this.customer = customer;
        //this.products = invoice;
        this.date2 = date2;
        //this.status = status;

         this.products = new ArrayList<>(invoice);
        //Collections.copy(this.products, invoice);

    }

    /*public void makeOrder(Customer customer, List<Product> invoice, String date2) {

        this.customer = customer;
        this.date2 = date2;

        this.products = new ArrayList<>(invoice);
        //Collections.copy(this.products, invoice);


    }*/


    public Customer getCustomer() {
        return customer;
    }


    public List<Product> getProducts() {
        return products;
    }


}